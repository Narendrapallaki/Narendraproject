package com.batch_processing.config;

import java.io.File;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager; // Use this import
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.batch_processing.entity.Product;

@Configuration
public class BatchConfig {

	@Bean
	Job jobBean(JobRepository jobRepository, JobComplitionNotificationImpl listener, Step steps) {
		return new JobBuilder("job", jobRepository)
				.listener(listener)
				.start(steps)
				.build();
	}

	// Step
	@Bean
	public Step steps(JobRepository jobRepository, DataSourceTransactionManager transactionManager, // Change here
			FlatFileItemReader<Product> reader, ItemProcessor<Product, Product> processor, ItemWriter<Product> writer) {
		return new StepBuilder("jobStep", jobRepository)
				.<Product, Product>chunk(100, transactionManager)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/eidiko"); // Replace with your MySQL database URL
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	// reader
	@Bean
	@StepScope
	public FlatFileItemReader<Product> reader(@Value("#{jobParameters['fullPathFileName']}") String pathToFIle) {
		if (pathToFIle == null) {
			throw new IllegalArgumentException("Path to file is null");
		}
		return new FlatFileItemReaderBuilder<Product>().name("itemReader")
				.resource(new FileSystemResource(new File(pathToFIle)))
				.linesToSkip(1)
				.delimited()
				.names( "title", "description", "price" , "discount")
				.targetType(Product.class)
				.build();
	}

	// processor
	@Bean
	public ItemProcessor<Product, Product> processor() {
		return new CustomItemProcessor();
	}

	// writer
	@Bean
	public ItemWriter<Product> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Product>().sql(
				"insert into product( title, description, price, discount, discounted_price) values (  :title, :description, :price, :discount, :discounted_price)")
				.dataSource(dataSource)
				.beanMapped()
				.build();
	}
}
