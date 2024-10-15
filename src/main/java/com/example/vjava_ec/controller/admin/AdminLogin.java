package com.example.vjava_ec.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

/**
 * ログインコントローラ
 */
@Controller
@RequestMapping("/admin/login")
@RequiredArgsConstructor
public class AdminLogin {
	
	 /**
     * 管理者ログイン画面を表示するためのメソッド。
     * 
     * <p>GETリクエストを受け取り、"admin/login" テンプレートを返します。
     * このテンプレートは、管理者のログインページを表示します。</p>
     * 
     * @return ログイン画面のビュー名（"admin/login"）
     */
	@GetMapping
	public String showLogin() {
		return "admin/login";
	}

}
