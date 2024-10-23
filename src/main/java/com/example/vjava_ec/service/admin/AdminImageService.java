package com.example.vjava_ec.service.admin;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * 管理者：画像のサービスクラスのインターフェース定義
 */
public interface AdminImageService {
	/**
	 * 画像ファイルの保存処理
	 */
	String uploadImage(MultipartFile file, String type) throws IOException;

	/**
	 * 画像ファイルの削除処理
	 */
	void deleteImage(String filename, String type);
}
