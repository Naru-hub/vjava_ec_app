package com.example.vjava_ec.repository.user;

import org.apache.ibatis.annotations.Mapper;

import com.example.vjava_ec.entity.User;
import com.example.vjava_ec.form.user.SignupUserForm;

/**
 * 会員：マッパーインターフェイス
 * 会員の情報を取得
 */
@Mapper
public interface UserMapper {

    /**
     * 指定されたメールアドレスを使用してデータベースからユーザーを検索
     * 
     * @param email ユーザーを検索するためのメールアドレス
     * @return メールアドレスに対応する {@link User} オブジェクト
     *         該当するユーザーが見つからない場合はnullを返す
     */
    User selectUserByEmail(String email);
    
    /**
     * 新規会員情報をデータベースに挿入します。
     * 
     * @param user データベースに挿入する {@link User} オブジェクト
     */
    void insertUser(User user);

    /**
     * フォームデータを基に {@link User} エンティティを作成します。
     * 
     * @param signupUserForm フォームから送信された {@link SignupUserForm} オブジェクト
     * @return {@link User} エンティティ
     */
    User userEntity(SignupUserForm signupUserForm);
    
    /**
     * {@link User} エンティティから {@link SignupUserForm} を作成します。
     * 
     * @param user {@link User} オブジェクト
     * @return {@link SignupUserForm} オブジェクト
     */
    SignupUserForm signupUserForm(User user);
    
    
}
