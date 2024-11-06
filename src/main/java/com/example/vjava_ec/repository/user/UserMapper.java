package com.example.vjava_ec.repository.user;

import org.apache.ibatis.annotations.Mapper;

import com.example.vjava_ec.entity.User;

/**
 * 会員：マッパーインターフェイス
 * 会員の情報を取得
 */
@Mapper
public interface UserMapper {

    /**
     * 指定されたメールアドレスを使用してデータベースからユーザーを検索
     */
    User selectUserByEmail(String email);
    
    /**
     * 会員情報の更新処理
     */
    void updateUser(User user);
    
    /**
     * 会員の論理削除処理
     */
    void deleteUser(User user);
    
    /**
     * 新規会員情報をデータベースに登録
     */
    void insertUser(User user);
    
    /**
     * パスワードを更新する
     */
    void updatePassowrd(User user);
}
