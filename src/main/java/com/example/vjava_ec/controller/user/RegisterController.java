package com.example.vjava_ec.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.vjava_ec.form.user.SignupUserForm;
import com.example.vjava_ec.service.user.impl.TestUserServiceImpl;

import lombok.RequiredArgsConstructor;

/**
 * 新規登録コントローラ
 */
@Controller
@RequestMapping("/user/register")
@RequiredArgsConstructor
public class RegisterController {
	
	// ユーザー登録を処理するサービスクラス
	private final TestUserServiceImpl testUserService;
	
	
    /**
     * 新規登録フォームを表示
     * 
     * @param model 
     * @return /register
     */
	@GetMapping
	public String showRegister(Model model) {
		model.addAttribute("signupUserForm", new SignupUserForm());
		return "user/register";
		
	}
	
    /**
     * 新規登録を実行
     * 
     * @param signupUserForm 
     * @param result 
     * @param model 
     * @return 成功時は成功ページにリダイレクト、失敗時は/register
     */
	@PostMapping
	public String newRegister(@Validated @ModelAttribute("signupUserForm") SignupUserForm signupUserForm, 
							   BindingResult result, 
							   RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			return "user/register";
		}
		try {
			testUserService.NewRegisterUser(signupUserForm);
			// フラッシュメッセージを追加
			 redirectAttributes.addFlashAttribute("successMessage", "登録が成功しました。"); 
		} catch(Exception e) {
			 result.rejectValue("email", "error.user", e.getMessage());
			 return "user/register";
		}
		// ホーム画面へのリダイレクト
		return "redirect:/user";
	}
}


