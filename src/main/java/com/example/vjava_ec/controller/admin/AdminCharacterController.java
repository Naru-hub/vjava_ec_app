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
	public String showCharacterDetail(@PathVariable Integer id, Model model) {
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
				String imagePath = this.IMAGE_UPLOAD_DIR_PATH
						+ adminImageService.uploadImage(form.getFile(), "character");
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

	/**
	 * キャラクタの情報編集画面を表示
	 * @param id
	 * @param model
	 * @param attributes
	 * @return admin/character/edit キャラクタ編集画面
	 */
	@GetMapping("/edit/{id}")
	public String showEditCharacterDetail(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		// IDに対応するキャラクタを取得
		Character target = adminCharacterService.findByIdCharacter(id);

		// 編集対象のキャラクタ情報が取得できた場合
		if (target != null) {
			// Formに変換、編集画面に遷移
			AdminCharacterForm form = AdminCharacterHelper.convertCharacterForm(target);
			// モデルに格納
			model.addAttribute("adminCharacterForm", form);

			// 編集画面へ遷移
			return "admin/character/edit";
		} else {
			// 対象データがない場合はフラッシュメッセージを表示
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			// 詳細画面へリダイレクト
			return "redirect:/admin/character/" + id;
		}
	}
	
	/**
	 * キャラクタ情報を編集する
	 * @param form
	 * @param bindingResult
	 * @param attributes
	 * @param model
	 * @return admin/character/{id} キャラクタ詳細画面
	 */
	@PostMapping("/update")
	public String updateCharacterDetail(@Validated AdminCharacterForm form,
			BindingResult bindingResult,
			RedirectAttributes attributes, Model model) {
		// === バリデーションチェック ===
		// バリデーションエラーがある場合
		if (bindingResult.hasErrors()) {
			// 編集画面へ遷移
			return "admin/character/edit";
		}

		// 元のキャラクタ情報の取得
		Character existingCharacter = adminCharacterService.findByIdCharacter(form.getId());

		// キャラクタ情報が見つからない場合
		if (existingCharacter == null) {
			// 対象データがない場合はフラッシュメッセージを表示
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			// 詳細画面へリダイレクト
			return "redirect:/admin/character/" + form.getId();
		}

		// 編集するイメージファイル名とイメージパス
		String newImageFilename = null;
		String newImagePath = null;

		try {
			// 新しい画像ファイルの処理
			if (form.getFile() != null && !form.getFile().isEmpty()) {
				try {
					// 新しい画像のファイル名を取得
					newImageFilename = adminImageService.uploadImage(form.getFile(), "character");
					// ファイルパスを相対パスでセット
					newImagePath = this.IMAGE_UPLOAD_DIR_PATH + newImageFilename;
					form.setImagePath(newImagePath);

				} catch (IOException e) {
					// 画像のアップロードに失敗した場合の処理
					bindingResult.reject("errorMessage", "画像のアップロードに失敗しました。");
					// 編集画面へ遷移
					return "admin/character/edit";
				}
			} else {
				// 画像ファイルが選択されていない場合は既存の画像パスをそのまま使用
				if (existingCharacter != null && existingCharacter.getImagePath() != null) {
					form.setImagePath(existingCharacter.getImagePath());
				}
			}

			// エンティティへの変換
			Character character = AdminCharacterHelper.convertCharacter(form);
			// 更新処理
			adminCharacterService.updateCharacter(character);

			// 古い画像ファイルの削除（更新処理が成功した場合のみ）
			if (existingCharacter != null && existingCharacter.getImagePath() != null) {
				// 相対パスからファイル名を取得
				String oldImageFilename = existingCharacter.getImagePath().replace(this.IMAGE_UPLOAD_DIR_PATH, "");
				if (newImageFilename != null && !oldImageFilename.equals(newImageFilename)) {
					adminImageService.deleteImage(oldImageFilename, "item");
				}
			}

			// 画像の保存処理が終わるまで待機
			try {
				Thread.sleep(3300);
			} catch (Exception e) {
			}

			// フラッシュメッセージ
			attributes.addFlashAttribute("message", "キャラクタ情報が更新されました");
			// 詳細画面へリダイレクト
			return "redirect:/admin/character/" + character.getId();

		} catch (Exception e) {
			// 更新処理でエラーが発生した場合の処理
			if (newImageFilename != null) {
				// 新しい画像ファイルを削除
				adminImageService.deleteImage(newImageFilename, "character");
			}

			bindingResult.reject("errorMesage", "キャラクタ情報の更新に失敗しました。");
			// 編集画面へ遷移
			return "admin/character/edit";
		}
	}
	
	/**
	 * 指定されたIDのキャラクタを削除(論理削除)
	 * 
	 * キャラクタの削除ステータスを有効にする
	 * 
	 * @param id
	 * @param attributes
	 * @return 　admin/character/list キャラクタ一覧画面
	 */
	@PostMapping("/delete/{id}")
	public String updateCharacterDeleteStatus(@PathVariable Integer id, RedirectAttributes attributes) {
		// 対象のキャラクタ情報の取得
		Character targetCharacter = adminCharacterService.findByIdCharacter(id);

		// キャラクタ情報が存在するか
		if (targetCharacter != null) {
			// 削除フラグをTRUEにする
			targetCharacter.setDeleted(true);

			// キャラクタ情報を削除
			adminCharacterService.deleteCharacter(targetCharacter);
			// フラッシュメッセージの設定
			attributes.addFlashAttribute("message", "キャラクタが削除されました");
		} else {
			// 商品が見つからなかった場合の処理
			attributes.addFlashAttribute("errorMessage", "キャラクタが見つかりませんでした");
		}

		// 一覧画面へリダイレクト
		return "redirect:/admin/character/list";
	}
}
