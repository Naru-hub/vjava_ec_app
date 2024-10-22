package com.example.vjava_ec.service.user;


import com.example.vjava_ec.form.user.SignupUserForm;

public interface TestUserService {

    /**
     * 新規ユーザーを登録する
     * 
     * @param signupUserForm 新規登録情報入力フォーム
     * @throws Exception 
     */
    void NewRegisterUser(SignupUserForm signupUserForm) throws Exception;
    
    //
    boolean IdentifyUser();
}
