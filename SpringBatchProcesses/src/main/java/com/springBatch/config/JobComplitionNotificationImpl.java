package com.springBatch.config;


import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class JobComplitionNotificationImpl implements JobExecutionListener {

	
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("job started.........");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		
		
		log.info("job ended............");
	}

}
