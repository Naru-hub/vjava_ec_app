package com.example.vjava_ec.service.user;

import com.example.vjava_ec.form.user.SignupUserForm;

/**
 * UserServiceのインターフェースクラス
 */
public interface TestUserService {
	
    /**
     * 新規ユーザーを登録
     */
    void NewRegisterUser(SignupUserForm signupUserForm) throws Exception;
    
    /**
     * ユーザーのログイン状態を確認
     */
    boolean IdentifyUser();
}
