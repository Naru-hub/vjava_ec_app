package com.example.vjava_ec.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

/**
 * ログインコントローラ
 */
@Controller
@RequestMapping("/user/login")
@RequiredArgsConstructor
public class Login {
	
	 /**
     * 会員ログイン画面を表示するためのメソッド。
     * 
     * <p>GETリクエストを受け取り、"user/login" テンプレートを返します。
     * このテンプレートは、会員のログインページを表示します。</p>
     * 
     * @return ログイン画面のビュー名（"user/login"）
     */
	@GetMapping
	public String showLogin() {
		return "user/login";
	}

}
