package com.example.vjava_ec.service.user;


import com.example.vjava_ec.form.user.SignupUserForm;

public interface TestUserService {

    /**
     * 新規ユーザーを登録
     * 
     * @param signupUserForm 新規登録情報入力フォーム
     * @throws Exception 
     */
    void NewRegisterUser(SignupUserForm signupUserForm) throws Exception;
    
    /**
     * ユーザーのログイン状態を確認
     * 
     * @return ユーザーがログイン中であれば true、そうでなければ false を返す
     */
    boolean IdentifyUser();
}
