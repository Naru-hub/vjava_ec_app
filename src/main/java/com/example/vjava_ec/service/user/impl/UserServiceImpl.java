package com.example.vjava_ec.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vjava_ec.entity.CustomUserDetails;
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
	 * 会員情報の更新処理とメールアドレスが変更された際の認証情報の変更処理
	 * @param user Userエンティティ
	 */
	@Override
	public void updateUser(User user) {		
		// 現在の認証情報を取得
	    Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
	    // メールアドレスに変更があった場合
	    if(!user.getEmail().equals(currentAuth.getName())) {
	    	 // 現在のユーザー情報を取得
	    	CustomUserDetails currentUserDetails = (CustomUserDetails) currentAuth.getPrincipal();
	    	// 権限リスト
			List<GrantedAuthority> authorities = new ArrayList<>();
			// 列挙型からロールを取得
			authorities.add(new SimpleGrantedAuthority("USER"));
	    	// 新しいユーザー名で CustomUserDetails を作成
	        CustomUserDetails newUserDetails = new CustomUserDetails(
	            user.getEmail(),  
	            currentUserDetails.getPassword(), 
	            authorities
	        );

	        // 新しい認証トークンを作成
	        Authentication newAuth = new UsernamePasswordAuthenticationToken(
	            newUserDetails,
	            newUserDetails.getPassword(),
	            newUserDetails.getAuthorities()
	        );

	        // 新しい認証情報をSecurityContextにセット
	        SecurityContextHolder.getContext().setAuthentication(newAuth);
	    }
		userMapper.updateUser(user);		
	}
	
	/**
	 * 会員の論理削除
	 * @param user Userエンティティ
	 */
	@Override
	public void deleteUser(User user) {
		userMapper.deleteUser(user);
	}

}
