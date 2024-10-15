package com.example.vjava_ec.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

/**
 * ホームコントローラ
 */
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class Home {
	
	@GetMapping("")
	public String home() {
		return "user/home";
	}
}
