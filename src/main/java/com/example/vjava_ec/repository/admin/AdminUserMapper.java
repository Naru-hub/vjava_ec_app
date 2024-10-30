package com.example.vjava_ec.repository.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.vjava_ec.entity.User;

/*
 * 管理者：マッパーインターフェイス
 * ユーザ関連の機能に使用
 */
@Mapper
public interface AdminUserMapper {
	
	/**
	 * 全てのユーザ情報を取得
	 */
	List<User> selectAll();
	
	/**
   	 * ユーザ詳細情報を取得
   	 */
    User selectById(@Param("id") Integer id);
    
    /**
   	 * ユーザを登録
   	 */
    void insert(User user);
    
    /**
	 * ユーザを編集
	 */
    void update(User user);
    
    /**
	 * ユーザのステータス変更
	 */
	User changeUserStatus(User user);
    
}