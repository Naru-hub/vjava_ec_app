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
     * 
     * @param email ユーザーを検索するためのメールアドレス
     * @return メールアドレスに対応する {@link User} オブジェクト
     *         該当するユーザーが見つからない場合はnullを返す
     */
    User selectUserByEmail(String email);
    
    /**
     * 会員情報の更新処理
     * 
     * @param user Userエンティティ
     */
    void updateUser(User user);
    
    /**
     * 会員の論理削除処理
     * 
     * @param user　Userエンティティ
     */
    void deleteUser(User user);

}
