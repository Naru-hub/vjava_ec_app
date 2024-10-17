package com.example.vjava_ec.repository.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.vjava_ec.entity.User;

/**
 * 管理者：ユーザマッパー
 */
@Mapper
public interface AdminUserMapper {

	List<User> selectAll();

    User selectById(@Param("id") Integer id);

    void insert(User user);

    void update(User user);
    
}
