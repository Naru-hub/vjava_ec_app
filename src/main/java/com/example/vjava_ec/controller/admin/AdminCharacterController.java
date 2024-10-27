package com.example.vjava_ec.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.vjava_ec.entity.Character;
import com.example.vjava_ec.service.admin.AdminCharacterService;

import lombok.RequiredArgsConstructor;

/**
 * キャラクタコントローラ
 */
@Controller
@RequestMapping("/admin/character")
@RequiredArgsConstructor
public class AdminCharacterController {
	
	//DI
	private final AdminCharacterService adminCharacterService;
	
	/**
	 * キャラクタ一覧表示
	 * @param model
	 * @return admin/character/list キャラクタ一覧画面
	 */
	@GetMapping("/list")
	public String showCharacterList(Model model) {
		// キャラクタ一覧情報を取得し、モデルに追加
		model.addAttribute("characterList", adminCharacterService.findAll());
		return "admin/character/list";
	}
	
	/**
	 * キャラクタ詳細表示
	 * @param id
	 * @param model
	 * @return	admin/character/detail キャラクタ詳細画面
	 */
	@GetMapping("/{id}")
	public String showCharacterDetail(@PathVariable("id") Integer id, Model model) {
		// キャラクタ詳細情報を取得し、モデルに追加
		 model.addAttribute("character", adminCharacterService.findByIdCharacter(id));
		 return "admin/character/detail";
	}
	
	@GetMapping("/form")
	public String showCharacterRegister(Model model) {
        model.addAttribute("character", new Character());
        return "admin/character/new";  // new.html に遷移
    }
}

