package com.mail.mailController;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mail.exceptionHandler.CustomeException;
import com.mail.service.MailService;

import jakarta.mail.MessagingException;

//@Controller
@RestController
public class MailController {
	
	
	@Autowired
	private MailService mailService;
	
	
	@GetMapping("/send")
	public String mailSend(@RequestParam("path") MultipartFile[] path) throws MessagingException, IOException, CustomeException
	{		
	//mailService.mailSender("C:/Users/Sreenivas Bandaru/Pictures/Screenshots/Screen.png");
		mailService.mailSender(path);
		return "successes";
	}
	
}
 