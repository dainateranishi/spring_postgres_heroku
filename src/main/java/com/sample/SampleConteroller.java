package com.sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * サンプルアプリのControllerクラス.
 * 
 * @author teranishidaina
 *
 */
@Controller
@RequestMapping("/sample")
public class SampleConteroller {

	@Autowired
	private SampleRepository repository;

	/**
	 * サンプルアプリの画面を返す.
	 * 
	 * @param model requestスコープ
	 * @return サンプルアプリ画面へのパス
	 */
	@GetMapping("/")
	public String getIndex(Model model) {

		List<User> userList = repository.findAll();

		model.addAttribute("userList", userList);

		return "sample/helloWorld";
//		return "/sample/helloWorld";
	}

}
