package com.sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample")
public class SampleConteroller {

	@Autowired
	private SampleRepository repository;

	@GetMapping("/")
	public String getIndex(Model model) {

		List<User> userList = repository.findAll();

		model.addAttribute("userList", userList);

		return "sample/helloWorld";
	}

}
