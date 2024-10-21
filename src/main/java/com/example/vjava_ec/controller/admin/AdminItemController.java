package com.example.vjava_ec.controller.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
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

import com.example.vjava_ec.dto.admin.adminItemDTO;
import com.example.vjava_ec.entity.Item;
import com.example.vjava_ec.form.admin.AdminItemForm;
import com.example.vjava_ec.helper.admin.AdminItemHelper;
import com.example.vjava_ec.service.admin.AdminCharacterService;
import com.example.vjava_ec.service.admin.AdminImageService;
import com.example.vjava_ec.service.admin.AdminItemService;

import lombok.RequiredArgsConstructor;

/**
 * 商品コントローラ
 */
@Controller
@RequestMapping("/admin/item")
@RequiredArgsConstructor
public class AdminItemController {

	// 消費税率(定数)
	@Value("${TAX_RATE}")
	private double TAX_RATE;

	// 画像ファイル格納ディレクトリパス
	private final String IMAGE_UPLOAD_DIR_PATH = "/images/item/";

	// DI
	private final AdminItemService adminItemService;
	private final AdminImageService adminImageService;
	private final AdminCharacterService adminCharacterService;

	/**
	 * 商品(すべて)の情報を取得し、商品一覧画面を表示
	 * 
	 * @param model モデルオブジェクト、商品情報一覧をビューに渡す
	 * @return String 商品一覧画面のビュー名（"admin/item/list"）
	 */
	@GetMapping("/list")
	public String showItemList(Model model) {
		// すべての商品一覧情報を取得
		List<adminItemDTO> items = adminItemService.findAllItem();

		// 各商品の税込み価格を計算し、税込み価格(priceWithTax)に設定
		for (adminItemDTO item : items) {
			// 税込み価格計算してint型にキャスト
			int priceWithTax = (int) Math.round(item.getPrice() * (1 + TAX_RATE));
			// 計算した税込み価格を設定
			item.setPriceWithTax(priceWithTax);
		}

		// モデルに商品一覧情報をセット
		model.addAttribute("itemList", items);
		// 商品一覧画面へ遷移
		return "admin/item/list";
	}

	/**
	 * 商品の詳細情報を取得し、商品詳細画面を表示
	 * @param id
	 * @param model　モデルオブジェクト、商品の詳細情報をビューに渡す
	 * @param attributes
	 * @return String 商品詳細画面のビュー名（"admin/item/{id}"）
	 */
	@GetMapping("/{id}")
	public String showItemDetail(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		// 商品IDに対応する商品詳細情報を取得
		adminItemDTO item = adminItemService.findByIdItem(id);

		// 対象データがあるか
		if (item != null) {
			// データがある場合
			// 税込み価格を計算してint型にキャスト
			int priceWithTax = (int) Math.round(item.getPrice() * (1 + TAX_RATE));
			// 計算した税込み価格を設定
			item.setPriceWithTax(priceWithTax);

			// モデルに格納
			model.addAttribute("item", item);
			// 商品詳細画面へ遷移
			return "admin/item/detail";
		} else {
			// 対象データがない場合
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			// 商品一覧画面へリダイレクト
			return "redirect:/list";
		}
	}

	/**
	 * 商品の新規登録画面を表示
	 * @param form
	 * @return String 商品新規登録画面のビュー名（"admin/item/new"）
	 */
	@GetMapping("/form")
	public String showItemRegister(@ModelAttribute AdminItemForm form, Model model) {
		// セレクトボックス用のキャラクタ一覧を取得
		model.addAttribute("characterList", adminCharacterService.findAll());
		// 商品新規登録画面へ遷移
		return "admin/item/new";
	}

	/**
	 * 商品を新規登録する
	 * @param form
	 * @param bindingResult
	 * @param attributes
	 * @return String 商品詳細画面のビュー名（"admin/item/{id}"）
	 */
	@PostMapping("/create")
	public String newItemRegister(@Validated AdminItemForm form,
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
			// セレクトボックス用のキャラクタ一覧を取得
			model.addAttribute("characterList", adminCharacterService.findAll());
			// 商品新規登録画面へ遷移
			return "admin/item/new";
		}

		// 画像ファイルの処理
		try {
			// ファイルがnullでなく、空でなければ、処理を実行
			if (form.getFile() != null && !form.getFile().isEmpty()) {
				// 画像の格納パスを生成
				String imagePath = this.IMAGE_UPLOAD_DIR_PATH + adminImageService.uploadImage(form.getFile(), "item");
				form.setImagePath(imagePath);
			}
		} catch (IOException e) {
			// エラーハンドリング
			bindingResult.reject("errorMessage", "画像のアップロードに失敗しました。");
			// 商品新規登録画面へ遷移
			return "admin/item/new";
		}

		// エンティティへの変換(商品(Item)オブジェクトの作成)
		Item item = AdminItemHelper.convertItem(form);
		// 登録実行(データベースへ保存)
		adminItemService.insertItem(item);

		// 画像の保存処理が終わるまで待機
		try {
			Thread.sleep(3300);
		} catch (Exception e) {
		}

		// 保存したアイテムのIDを取得
		Integer savedItemId = item.getId();

		// フラッシュメッセージ
		attributes.addFlashAttribute("message", "商品が登録されました");
		// 商品詳細画面へリダイレクト
		return "redirect:/admin/item/" + savedItemId;
	}

	/**
	 * 商品情報編集画面を表示
	 * @param id
	 * @param model
	 * @param attributes
	 * @return String 商品編集画面のビュー名（"admin/item/edit"）
	 */
	@GetMapping("/edit/{id}")
	public String showEditItemDetail(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		// IDに対応する商品を取得
		adminItemDTO target = adminItemService.findByIdItem(id);

		// 編集対象の商品情報が取得できた場合
		if (target != null) {
			// Formに変換、編集画面に遷移
			AdminItemForm form = AdminItemHelper.convertItemForm(target);
			// モデルに格納
			model.addAttribute("adminItemForm", form);

			// セレクトボックス用のキャラクタ一覧を取得
			model.addAttribute("characterList", adminCharacterService.findAll());
			// 商品編集画面へ遷移
			return "admin/item/edit";
		} else {
			// 対象データがない場合はフラッシュメッセージを表示
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			// 商品詳細画面へリダイレクト
			return "redirect:/admin/item/" + id;
		}
	}

	/**
	 * 商品情報を編集する
	 * @param form
	 * @param bindingResult
	 * @param attributes
	 * @return String 商品詳細画面のビュー名（"admin/item/{id}"）
	 */
	@PostMapping("/update")
	public String updateItemDetail(@Validated AdminItemForm form,
			BindingResult bindingResult,
			RedirectAttributes attributes, Model model) {
		// === バリデーションチェック ===

		// ファイルが選択されていない場合
		if (form.getFile() == null || form.getFile().isEmpty()) {
			bindingResult.rejectValue("file", "error.file", "ファイルを選択してください");
		}
		
		// バリデーションエラーがある場合
		if (bindingResult.hasErrors()) {
			// セレクトボックス用のキャラクタ一覧を取得
			model.addAttribute("characterList", adminCharacterService.findAll());
			// 商品編集画面へ遷移
			return "admin/item/edit";
		}

		// 元の商品情報の取得
		adminItemDTO existingItem = adminItemService.findByIdItem(form.getItemId());

		// 商品情報が見つからない場合
		if (existingItem == null) {
			// 対象データがない場合はフラッシュメッセージを表示
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			// 商品詳細画面へリダイレクト
			return "redirect:/admin/item/" + form.getItemId();
		}

		// 編集するイメージファイル名とイメージパス
		String newImageFilename = null;
		String newImagePath = null;

		try {
			// 新しい画像ファイルの処理
			if (form.getFile() != null && !form.getFile().isEmpty()) {
				try {
					// 新しい画像のファイル名を取得
					newImageFilename = adminImageService.uploadImage(form.getFile(), "item");
					// ファイルパスを相対パスでセットする
					newImagePath = this.IMAGE_UPLOAD_DIR_PATH + newImageFilename;
					form.setImagePath(newImagePath);

				} catch (IOException e) {
					// 画像のアップロードに失敗した場合の処理
					bindingResult.reject("errorMessage", "画像のアップロードに失敗しました。");
					// 商品編集画面へ遷移
					return "admin/item/edit";
				}
			} else {
				// 画像ファイルが選択されていない場合は既存の画像パスをそのまま使用
				if (existingItem != null && existingItem.getImagePath() != null) {
					form.setImagePath(existingItem.getImagePath());
				}
			}

			// エンティティへの変換
			Item item = AdminItemHelper.convertItem(form);
			// 更新処理
			adminItemService.updateItem(item);

			// 古い画像ファイルの削除（更新処理が成功した場合のみ）
			if (existingItem != null && existingItem.getImagePath() != null) {
				// 相対パスからファイル名を取得
				String oldImageFilename = existingItem.getImagePath().replace(this.IMAGE_UPLOAD_DIR_PATH, "");
				if (newImageFilename != null && !oldImageFilename.equals(newImageFilename)) {
					adminImageService.deleteImage(oldImageFilename, "post");
				}
			}

			// 画像の保存処理が終わるまで待機
			try {
				Thread.sleep(3300);
			} catch (Exception e) {
			}

			// フラッシュメッセージ
			attributes.addFlashAttribute("message", "商品情報が更新されました");
			// 商品詳細画面へリダイレクト
			return "redirect:/admin/item/{id}";

		} catch (Exception e) {
			// 投稿の更新処理でエラーが発生した場合の処理
			if (newImageFilename != null) {
				// 新しい画像ファイルを削除
				adminImageService.deleteImage(newImageFilename, "post");
			}

			bindingResult.reject("errorMesage", "商品情報の更新に失敗しました。");
			// 商品編集画面へ遷移
			return "admin/item/edit";
		}
	}
}
