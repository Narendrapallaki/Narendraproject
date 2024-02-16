package com.springBatch.Controller;

import java.io.File;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class SpringBatchController {
	
	
	
	@Autowired
	public Job job;
	@Autowired
	public JobLauncher jobLauncher;
	
	private final String temp_path="C:/Spring-batch/";
	
	@PostMapping("/launch")
	public ResponseEntity<String>response(@RequestParam("file")MultipartFile multipartFile) throws Exception, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException
	{
		
		
		 String originalFilename = multipartFile.getOriginalFilename();
		 
		 File file=new File(temp_path+originalFilename);
		 multipartFile.transferTo(file);
		
		
		     JobParameters jobParameters = new JobParametersBuilder()
		    		 
		    		
		    		 .addString("filePath", temp_path+originalFilename)
		    		 .addLong("start-At", System.currentTimeMillis())
		     .toJobParameters();
		
		jobLauncher.run(job, jobParameters);
		
		
		return ResponseEntity.ok("successes");
	}

}
