package com.example.vjava_ec.service.admin.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.vjava_ec.entity.User;
import com.example.vjava_ec.repository.admin.AdminUserMapper;
import com.example.vjava_ec.service.admin.AdminUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {
	//DI
	private final AdminUserMapper adminUserMapper;
	
	/**
	 * 全会員一覧情報取得メソッド
	 * 
	 * @return List<User> 全会員一覧情報
	 */
	@Override
	public List<User> findAllUser() {
		return adminUserMapper.selectAll();
	}
	
	/**
	 * 会員詳細情報取得メソッド
	 * 
	 * @param id 会員のid
	 * @return 会員詳細情報
	 */
	@Override
	public User findByIdUser(Integer id) {
		return adminUserMapper.selectById(id);
	}

	/**
	 * 会員削除メソッド(論理削除)
	 * 
	 * @param user 会員情報
	 */
	@Override
	public User changeUserStatus(Integer id) {
		User user = findByIdUser(id);
	    user.setDeleted(!user.isDeleted()); //現在のisDeleted値を反転
	    adminUserMapper.update(user);
		return user;
	}

	/**
	 * 会員保存メソッド
	 * 
	 * @param user 会員情報
	 */
	@Override
	public void save(User user) {
		
	}

}