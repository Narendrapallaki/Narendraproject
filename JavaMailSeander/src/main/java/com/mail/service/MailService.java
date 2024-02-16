
package com.mail.service;


import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.mail.exceptionHandler.CustomeException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	public TemplateEngine engine;

	public void mailSender(MultipartFile[] file) throws CustomeException  {
		
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setTo("narendrapallaki2018@gmail.com");
			mimeMessageHelper.setSubject("Approve m leave");

			Context context = new Context();

			String name = "Narendra";
			context.setVariable("user", name);
	     
			String process = engine.process("home-template.html", context);
			mimeMessageHelper.setText(process, true);
			 
			// this is for text formate mail sender
			//FileSystemResource fsr=new FileSystemResource(new File(path));	
			//mimeMessageHelper.addAttachment(fsr.getFilename(), fsr);
			
			//This is for File formate mail sender
			
			for (MultipartFile mf : file) {// this line of code is for sending multiple file at a time 
				
			//	List<?>list=new ArrayList<>();
				 ByteArrayResource iss = new ByteArrayResource(mf.getBytes());
		          
		          mimeMessageHelper.addAttachment(mf.getOriginalFilename(), iss);
		          
			}
	
			
			javaMailSender.send(mimeMessage);


		}
	   catch (MessagingException | IOException e) {
          // Log the exception or handle it as needed
          throw new CustomeException("Error sending email with attachment", e);
      }
	}
}
