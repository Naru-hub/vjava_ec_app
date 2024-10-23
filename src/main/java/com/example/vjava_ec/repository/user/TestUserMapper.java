package com.example.vjava_ec.repository.user;

import org.apache.ibatis.annotations.Mapper;

import com.example.vjava_ec.entity.User;

/**
 * 会員：マッパーインターフェイス
 * 会員の情報を取得
 */
@Mapper
public interface TestUserMapper {

    /**
     * 指定されたメールアドレスを使用してデータベースからユーザーを検索
     */
    User selectUserByEmail(String email);
    
    /**
     * 新規会員情報をデータベースに登録
     */
    void insertUser(User user);
 
}
