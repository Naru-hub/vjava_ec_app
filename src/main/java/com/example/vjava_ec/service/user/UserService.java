package com.example.vjava_ec.service.user;

import com.example.vjava_ec.entity.User;

/**
 * UserServiceのInterfaceクラス
 * 
 */
public interface UserService {
	
	/**
	 * メールアドレスからユーザを探す
	 * @param email メールアドレス
	 */
	User selectUserByEmail(String email);
	
	/**
	 * ログイン中のユーザのメールアドレスを取得
	 */
	String getLoginUserEmail();
	
	/**
	 * ログイン中のユーザを取得
	 */
	User selectLoginUser();
	
	/**
	 * 会員情報の更新
	 * @param user Userエンティティ
	 */
	void updateUser(User user);
	
	/**
	 * 会員の論理削除
	 * @param user
	 */
	void deleteUser(User user);
	
}
