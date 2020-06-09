package com.mabaya.sponsored;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController
{
	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
}
