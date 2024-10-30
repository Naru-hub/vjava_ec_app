package com.example.vjava_ec.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.vjava_ec.entity.User;
import com.example.vjava_ec.service.admin.AdminUserService;

import lombok.RequiredArgsConstructor;

/**
 * 会員コントローラ
 */
@Controller
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class AdminUserController {
	// DI
	private final AdminUserService adminUserService;
	
	/**
	 * 会員(すべて)の情報を取得し、会員一覧画面を表示
	 * 
	 * @param model モデルオブジェクト、会員情報一覧をビューに渡す
	 * @return String 会員一覧画面のビュー名（"admin/user/list"）
	 */
    @GetMapping("/list")
    public String showUserList(Model model) {
    	// すべての会員一覧情報を取得
    	List<User> userList = adminUserService.findAllUser();
    	// モデルに会員一覧情報をセット
    	model.addAttribute("userList", userList);
    	// 会員一覧画面へ遷移
        return "admin/user/list";
    }

    /**
	 * 会員の詳細情報を取得し、会員詳細画面を表示
	 * @param id
	 * @param model モデルオブジェクト、会員の詳細情報をビューに渡す
	 * @param attributes
	 * @return String 会員詳細画面のビュー名（"admin/user/detail"）
	 */
    @GetMapping("/{id}")
    public String showUserDetail(@PathVariable Integer id, Model model) {
    	// 会員IDに対応する会員詳細情報を取得
    	User user = adminUserService.findByIdUser(id);
    	// モデルに格納
        model.addAttribute("user", user);
        // 会員詳細画面へ遷移
        return "admin/user/detail";
    }

    /**
	 * 会員情報編集画面を表示
	 * @param id
	 * @param model
	 * @param attributes
	 * @return String 会員編集画面のビュー名（"admin/user/edit"）
	 */
    @GetMapping("/edit/{id}")
    public String showEditUserDetail(@PathVariable Integer id, Model model) {
    	// 会員IDに対応する会員詳細情報を取得
    	User editUser = adminUserService.findByIdUser(id);
    	// モデルに格納
        model.addAttribute("user", editUser);
        // 会員詳細画面へ遷移
        return "admin/user/edit";
    }

    /**
	 * 会員情報を編集する
	 * @param form
	 * @return String 会員詳細画面のビュー名（"admin/user/{id}"）
	 */
    @PostMapping("/statusUpdate/{id}")
    public String updateUserDetail(@PathVariable Integer id) {
    	// 会員IDに対応する会員詳細情報を取得
    	User user = adminUserService.changeUserStatus(id);
    	// 対象データがあるか
    	if (user != null) {
    		// ステータスの切り替え
    		user.setDeleted(!user.isDeleted());
    		// データベースに保存
            adminUserService.save(user); 
    	}
    	// 会員一覧画面へリダイレクト
        return "redirect:/admin/user/" + id;
    }
    
}