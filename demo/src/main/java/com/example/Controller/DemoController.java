package com.example.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

	
	
	@GetMapping("/run")
	public String value()
	{
		return "/run";
	}
}
