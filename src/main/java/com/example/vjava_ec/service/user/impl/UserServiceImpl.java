package com.example.vjava_ec.service.user.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vjava_ec.entity.User;
import com.example.vjava_ec.repository.user.UserMapper;
import com.example.vjava_ec.service.user.UserService;

import lombok.RequiredArgsConstructor;

/**
 * UserServiceインターフェースの実装クラス
 * Userエンティティの操作やログイン中のユーザの特定をする
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	// DI
	private final UserMapper userMapper;
	
	/**
	 * メールアドレスからユーザを探す
	 * @param email メールアドレス
	 * @return User 取得したUserエンティティ
	 */
	@Override
	public User selectUserByEmail(String email) {
		
		System.out.println(email);
		
		return userMapper.selectUserByEmail(email);
	}

	/**
	 * 認証情報からメールアドレスを取得
	 * @return String 取得したメールアドレス
	 */
	@Override
	public String getLoginUserEmail() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		if(email == null) {
			return "Not Found Email";
		}
		return email;
	}
	/**
	 * 認証情報からUserエンティティを取得
	 * @return User 取得したUserエンティティ
	 */
	@Override
	public User selectLoginUser() {
		String email = getLoginUserEmail();
		return selectUserByEmail(email);
	}
	
	/**
	 * 会員情報を更新する
	 */
	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);		
	}
	
	/**
	 * 会員の論理削除
	 */
	@Override
	public void deleteUser(User user) {
		userMapper.deleteUser(user);
	}

}
