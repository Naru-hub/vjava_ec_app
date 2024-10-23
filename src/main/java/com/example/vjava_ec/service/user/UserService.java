package com.example.vjava_ec.service.user;

import com.example.vjava_ec.entity.User;
import com.example.vjava_ec.form.user.SignupUserForm;

/**
 * UserServiceのInterfaceクラス
 */
public interface UserService {
	
	/**
	 * メールアドレスからユーザを探す
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
	 */
	void updateUser(User user);
	
	/**
	 * 会員の論理削除
	 */
	void deleteUser(User user);
	
    /**
     * 新規ユーザーを登録
     */
    void NewRegisterUser(SignupUserForm signupUserForm) throws Exception;
    
    /**
     * ユーザーのログイン状態を確認
     */
    boolean IdentifyUser();
}
