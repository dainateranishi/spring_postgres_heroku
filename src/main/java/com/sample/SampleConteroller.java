package com.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample")
public class SampleConteroller {
	
	@GetMapping("/")
	public String getIndex() {
		
		return "sample/helloWorld";
	}

}
