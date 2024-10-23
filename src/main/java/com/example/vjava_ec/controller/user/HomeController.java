package com.example.vjava_ec.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.vjava_ec.service.user.TestUserService;

import lombok.RequiredArgsConstructor;

/**
 * ホームコントローラ
 */
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class HomeController {
	
    //DI
    private final TestUserService testUserService;
	
    /**
     * ホーム画面を表示
     * @param model
     * @return user/home ホーム画面
     */
	@GetMapping("")
	public String showHome(Model model) {
        // ユーザーがログイン中か確認
        model.addAttribute("islogin",testUserService.IdentifyUser());
        System.out.println(testUserService.IdentifyUser());
		return "user/home";
	}
}
