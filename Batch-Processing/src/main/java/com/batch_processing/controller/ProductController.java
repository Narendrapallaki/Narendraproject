package com.batch_processing.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.batch_processing.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@Autowired
	private ProductService productService;

	Map<String, Object> response = new HashMap<>();

	public static final String TEMP_STORAGE = "C:\\Users\\Sreenivas Bandaru\\Downloads\\workspace-spring-tool-suite-4-4.21.0.RELEASE\\BatchData\\";

	@PostMapping("/saveBatchData")
	public ResponseEntity<Map<String, Object>> saveProductData(@RequestParam("file") MultipartFile multiPartFile) {
		try {
			String originalFileName = multiPartFile.getOriginalFilename();
			File fileToImport = new File(TEMP_STORAGE + originalFileName);
			System.out.println("======================================  " + fileToImport);

			multiPartFile.transferTo(fileToImport);

			JobParameters jobParameters = new JobParametersBuilder()
					.addString("fullPathFileName", TEMP_STORAGE + originalFileName)
					.addLong("startAt", System.currentTimeMillis()).toJobParameters();

			JobExecution execution = jobLauncher.run(job, jobParameters);

			
			  if(execution.getExitStatus().getExitCode().equals(ExitStatus.COMPLETED)){
			  //delete the file from the TEMP_STORAGE
			  Files.deleteIfExists(Paths.get(TEMP_STORAGE + originalFileName)); }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
