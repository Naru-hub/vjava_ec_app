package com.example.vjava_ec.service.admin.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vjava_ec.entity.User;
import com.example.vjava_ec.repository.admin.AdminUserMapper;
import com.example.vjava_ec.service.admin.AdminUserService;

import lombok.RequiredArgsConstructor;

/**
 * 管理者：会員の実装クラス
 */
@Service
@Transactional
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
	 * 会員ステータスを変更(論理削除)
	 * 
	 * @param user
	 */
	@Override
	public void changeUserStatus(User user) {
		// 削除フラグをTRUEにする
		user.setDeleted(true);
		adminUserMapper.update(user);
	}

}
