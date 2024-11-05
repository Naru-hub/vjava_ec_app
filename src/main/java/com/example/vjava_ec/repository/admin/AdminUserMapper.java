package com.example.vjava_ec.repository.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.vjava_ec.entity.User;

/*
 * 管理者：マッパーインターフェイス
 * 会員関連の機能に使用
 */
@Mapper
public interface AdminUserMapper {
	
	/**
	 * 全ての会員情報を取得
	 */
	List<User> selectAll();
	
	/**
   	 * 会員詳細情報を取得
   	 */
    User selectById(@Param("id") Integer id);
    
    /**
	 * 会員ステータスを編集
	 */
    void update(User user);
    
}
