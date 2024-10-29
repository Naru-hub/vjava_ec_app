package com.example.vjava_ec.service.admin.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.vjava_ec.service.admin.AdminImageService;

/**
 * 管理者：画像処理の実装クラス
 */
@Service
public class AdminImageServiceImpl implements AdminImageService {

	// 商品画像保存先フォルダのパス
	private final String itemImageUploadFolder;
	//キャラクタ画像保存先フォルダのパス
	private final String characterImageUploadFolder;

	// コンストラクタで保存先のファイルパスを指定
	public AdminImageServiceImpl(
			@Value("${images.item.path}") String itemImageUploadFolder,
			@Value("${images.character.path}") String characterImageUploadFolder) {
		this.itemImageUploadFolder = itemImageUploadFolder;
		this.characterImageUploadFolder = characterImageUploadFolder;
	}

	/**
	 * 画像アップロード・保存処理
	 * 
	 * @param file アップロードするファイル
	 * @param type 画像の種類を指定（"item"など）
	 * @return アップロードされたファイル名。ファイルがアップロードされなかった場合はnullを返す
	 * @throws IOException ファイルの書き込み中に入出力例外が発生する場合
	 */
	@Override
	public String uploadImage(MultipartFile file, String type) throws IOException {
		// アップロードする画像が商品かキャラクタかを判定
		String uploadFolder = type.equals("item") ? itemImageUploadFolder : characterImageUploadFolder;

		// ファイルがnullまたは空でない場合に保存処理を実行
		if (file != null && !file.isEmpty()) {
			File dir = new File(uploadFolder);
			// ディレクトリが存在しない場合は作成
			if (!dir.exists()) {
				dir.mkdirs();
			}

			// タイムスタンプを用いた一意なファイル名を生成
			String originalFilename = file.getOriginalFilename();
			String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String uniqueFilename = timestamp + "_" + originalFilename;

			// 指定されたパスにファイルを保存
			Path path = Paths.get(dir.getAbsolutePath() + File.separator + uniqueFilename);
			Files.write(path, file.getBytes());

			// ファイル名を返す
			return uniqueFilename;
		}
		// ファイルがnull・空だった場合はnullを返す
		return null;
	}

	/**
	 * 画像ファイルの削除処理
	 * 
	 * @param filename 削除する画像ファイル名
	 * @param type 画像の種類を指定（"item"など）
	 */
	@Override
	public void deleteImage(String filename, String type) {
		// アップロードする画像がItemかを判定
		String uploadFolder = type.equals("item") ? itemImageUploadFolder : characterImageUploadFolder;

		// ファイル名がnullまたは空でない場合に削除処理を実行
		if (filename != null && !filename.isEmpty()) {
			// 削除対象のファイルを取得
			File file = new File(uploadFolder + filename);
			// ファイルが存在する場合に削除
			if (file.exists()) {
				file.delete();
			}
		}
	}
}
