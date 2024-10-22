package com.example.vjava_ec.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.vjava_ec.service.user.TestUserService;

import lombok.RequiredArgsConstructor;

/**
 * ホームコントローラ
 * ユーザーのログイン状態の確認を行う
 */
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class HomeController {
	
    //ユーザー情報を確認するサービスクラス
    private final TestUserService testUserService;
	
    /**
     * ホーム画面を表示
     * @param model
     * @return user/home
     */
	@GetMapping("")
	public String showHome(Model model) {
        // ユーザーがログイン中か確認
        model.addAttribute("islogin",testUserService.IdentifyUser());
        System.out.println(testUserService.IdentifyUser());
		return "user/home";
	}
}
