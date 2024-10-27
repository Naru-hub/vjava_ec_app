package com.example.vjava_ec.controller.admin;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.vjava_ec.entity.Character;
import com.example.vjava_ec.form.admin.AdminCharacterForm;
import com.example.vjava_ec.helper.admin.AdminCharacterHelper;
import com.example.vjava_ec.service.admin.AdminCharacterService;
import com.example.vjava_ec.service.admin.AdminImageService;

import lombok.RequiredArgsConstructor;

/**
 * キャラクタコントローラ
 */
@Controller
@RequestMapping("/admin/character")
@RequiredArgsConstructor
public class AdminCharacterController {

	// 画像ファイル格納ディレクトリパス
	private final String IMAGE_UPLOAD_DIR_PATH = "/images/character/";

	//DI
	private final AdminCharacterService adminCharacterService;
	private final AdminImageService adminImageService;

	/**
	 * キャラクタの一覧表示
	 * @param model
	 * @return admin/character/list キャラクタ一覧画面
	 */
	@GetMapping("/list")
	public String showCharacterList(Model model) {
		// キャラクタ一覧情報を取得し、モデルに追加
		model.addAttribute("characterList", adminCharacterService.findAll());
		// 一覧画面へ遷移
		return "admin/character/list";
	}

	/**
	 * キャラクタの詳細表示
	 * @param id
	 * @param model
	 * @return	admin/character/detail キャラクタ詳細画面
	 */
	@GetMapping("/{id}")
	public String showCharacterDetail(@PathVariable("id") Integer id, Model model) {
		// キャラクタ詳細情報を取得し、モデルに追加
		model.addAttribute("character", adminCharacterService.findByIdCharacter(id));
		// 詳細画面へ遷移
		return "admin/character/detail";
	}

	/**
	 * キャラクタの新規登録画面を表示
	 * @param form
	 * @param model
	 * @return admin/character/new キャラクタ新規登録画面
	 */
	@GetMapping("/form")
	public String showCharacterRegister(@ModelAttribute AdminCharacterForm form, Model model) {
		// 新規登録画面へ遷移
		return "admin/character/new";
	}
	
	/**
	 * キャラクタを新規登録する
	 * @param form
	 * @param bindingResult
	 * @param attributes
	 * @param model
	 * @return admin/character/{id} キャラクタ詳細画面
	 */
	@PostMapping("/create")
	public String newCharacterRegister(@Validated AdminCharacterForm form,
			BindingResult bindingResult,
			RedirectAttributes attributes,
			Model model) {
		// === バリデーションチェック ===
		// ファイルが選択されていない場合
		if (form.getFile() == null || form.getFile().isEmpty()) {
			bindingResult.rejectValue("file", "error.file", "ファイルを選択してください");
		}

		// バリデーションエラーがある場合
		if (bindingResult.hasErrors()) {
			// 新規登録画面へ遷移
			return "admin/character/new";
		}

		// 画像ファイルの処理
		try {
			// ファイルがnullでなく、空でなければ、処理を実行
			if (form.getFile() != null && !form.getFile().isEmpty()) {
				// 画像の格納パスを生成、フォームにセット
				String imagePath = this.IMAGE_UPLOAD_DIR_PATH + adminImageService.uploadImage(form.getFile(), "character");
				form.setImagePath(imagePath);
			}
		} catch (IOException e) {
			// エラーハンドリング
			bindingResult.reject("errorMessage", "画像のアップロードに失敗しました。");
			// 新規登録画面へ遷移
			return "admin/character/new";
		}

		// エンティティへの変換(キャラクタオブジェクトの作成)
		Character character = AdminCharacterHelper.convertCharacter(form);
		System.out.println(character); // 後で削除
		// 登録実行(データベースへ保存)
		adminCharacterService.createCharacter(character);

		// 画像の保存処理が終わるまで待機
		try {
			Thread.sleep(3300);
		} catch (Exception e) {
		}

		// 保存した商品のIDを取得
		Integer savedCharacterId = character.getId();

		// フラッシュメッセージ
		attributes.addFlashAttribute("message", "キャラクタが登録されました");
		// 詳細画面へリダイレクト
		return "redirect:/admin/character/" + savedCharacterId;
	}
}
