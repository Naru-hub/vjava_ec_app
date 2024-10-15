package com.example.vjava_ec.repository.admin;
/**
 * AdminMapperインターフェースは、データベースとやり取りするための
 * 管理者関連の操作を定義します。
 * MyBatisを使用してSQLクエリを実行します。
 */

import org.apache.ibatis.annotations.Mapper;

import com.example.vjava_ec.entity.Admin;

@Mapper
public interface AdminMapper {

    /**
     * 指定されたメールアドレスを使用してデータベースから管理者を検索します。
     * 
     * @param email ユーザーを検索するためのメールアドレス
     * @return メールアドレスに対応する {@link Admin} オブジェクト
     *         該当するユーザーが見つからない場合はnullを返します。
     */
    Admin selectAdminByEmail(String email);

}
