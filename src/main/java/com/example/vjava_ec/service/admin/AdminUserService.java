package com.example.vjava_ec.service.admin;

import java.util.List;

import com.example.vjava_ec.entity.User;

/**
 * 管理者:会員のサービスクラスのインターフェース定義
 */
public interface AdminUserService {
	
	/**
	 * 会員一覧情報を検索
	 */
	List<User> findAllUser();
	
	/**
	 * 会員詳細情報を検索
	 */
	User findByIdUser(Integer id);
	
	/**
	 * 会員ステータスを変更
	 */
	void changeUserStatus(User user);

}
