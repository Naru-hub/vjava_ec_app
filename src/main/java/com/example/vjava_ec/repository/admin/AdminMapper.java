package com.example.vjava_ec.repository.admin;

import org.apache.ibatis.annotations.Mapper;

import com.example.vjava_ec.entity.Admin;

/*
 * 管理者：マッパーインターフェイス
 * セキュリティに使用
 */
@Mapper
public interface AdminMapper {

    /**
     * 指定されたメールアドレスを使用してデータベースから管理者を検索
     */
    Admin selectAdminByEmail(String email);

}
