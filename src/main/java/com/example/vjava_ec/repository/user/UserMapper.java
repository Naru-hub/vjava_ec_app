package com.example.vjava_ec.repository.user;

import org.apache.ibatis.annotations.Mapper;

import com.example.vjava_ec.entity.User;
/**
 * UserMapperインターフェースは、データベースとやり取りするための
 * ユーザー関連の操作を定義します。
 * MyBatisを使用してSQLクエリを実行します。
 */
@Mapper
public interface UserMapper {

    /**
     * 指定されたメールアドレスを使用してデータベースからユーザーを検索します。
     * 
     * @param email ユーザーを検索するためのメールアドレス
     * @return メールアドレスに対応する {@link User} オブジェクト
     *         該当するユーザーが見つからない場合はnullを返します。
     */
    User selectUserByEmail(String email);

}
