package com.springBatch.config;


import java.io.File;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.springBatch.entity.Users;
import com.springBatch.springBatchRepo.SpringBatchRepo;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SpringBatchConfig {

	
	private SpringBatchRepo batchRepo;

	// item reader                              //org.springframework.beans.factory.annotation.Value
	@Bean
	 @StepScope                                  //@Value("#{jobParameters[fullPathFileName]}")
	public FlatFileItemReader<Users> itemReader(@org.springframework.beans.factory.annotation.Value(value = "#{jobParameters[filePath]}") String pathFile) {
		FlatFileItemReader<Users> itemReader = new FlatFileItemReader<>();

		itemReader.setResource(new FileSystemResource(new File(pathFile)));
		itemReader.setName("csvUsers");

		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
		return itemReader;
	}

	private LineMapper<Users> lineMapper() {

		DefaultLineMapper<Users> lineMapper = new DefaultLineMapper<>();

		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		
		///Arrays.asList(new Users(null, null, null, null));
		
		lineTokenizer.setNames("id", "name", "dept", "salary");
		
	//	String[] columnNames = {"id", "name", "dept", "salary"};
	//	LineMapper<Users> mapper = lineMapper(columnNames);


		BeanWrapperFieldSetMapper<Users> fieldSetMapper = new BeanWrapperFieldSetMapper<>();

		fieldSetMapper.setTargetType(Users.class);

		lineMapper.setFieldSetMapper(fieldSetMapper);
		lineMapper.setLineTokenizer(lineTokenizer);

		return lineMapper;
	}

	// item processor
	@Bean
	public UserProcessor itemProcessor() {
		return new UserProcessor();
	}
	// item writer

	@Bean
	public RepositoryItemWriter<Users> itemWriter() {

		RepositoryItemWriter<Users> itemWriter = new RepositoryItemWriter<>();

		itemWriter.setRepository(batchRepo);
		itemWriter.setMethodName("save");
		return itemWriter;
	}

	// steps
	@Bean
	public Step jobStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,FlatFileItemReader<Users> itemReader) {
		return new StepBuilder("step-builder", jobRepository).<Users, Users>chunk(5, transactionManager)
				.reader(itemReader)
				.processor(itemProcessor())
				.writer(itemWriter())
				.taskExecutor(taskExecutor())
						

				.build();

	}

	
	// job
		@Bean
		public Job jobRun(JobRepository jobRepository, PlatformTransactionManager transactionManager,JobComplitionNotificationImpl complitionNotificationImpl,FlatFileItemReader<Users> itemReader)
		{
			return new JobBuilder("import-data", jobRepository)
					.listener(complitionNotificationImpl)
					.start(jobStep(jobRepository,transactionManager, itemReader)).build();
					
					//.flow(jobStep(jobRepository,transactionManager)).end().build();
		}

		@Bean
		public TaskExecutor taskExecutor() {
			SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
			asyncTaskExecutor.setConcurrencyLimit(10);
			return asyncTaskExecutor;
		}
	
	
}
