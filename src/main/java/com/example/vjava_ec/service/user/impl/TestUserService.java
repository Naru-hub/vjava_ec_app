package com.example.vjava_ec.service.user.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.vjava_ec.entity.User;
import com.example.vjava_ec.form.user.SignupUserForm;
import com.example.vjava_ec.repository.user.UserMapper;

@Service
public class TestUserService {
	
	@Autowired
	private UserMapper userMapper;
	
	
	/*
	 * 会員新規登録
	 */
	
	public void NewRegisterUser(SignupUserForm  signupUserForm) throws Exception{
		//メールアドレスの重複チェック
		User checkUser = userMapper.selectUserByEmail(signupUserForm.getEmail());
		if(checkUser != null) {
			 throw new Exception("そのメールアドレスは既に登録されています");
		}
		
		//パスワード一致チェック
		if(!signupUserForm.getPassword().equals(signupUserForm.getPasswordConfirm())) {
			throw new Exception("パスワードが一致しません");
		}
	
	
	//フォームからエンティティに変換
    User user = new User();
    user.setName(signupUserForm.getName());
    user.setEmail(signupUserForm.getEmail());
    user.setPassword(new BCryptPasswordEncoder().encode(signupUserForm.getPassword()));
    user.setPostcode(signupUserForm.getPostcode());
    user.setAddress(signupUserForm.getAddress());
    user.setTel(signupUserForm.getTel());
	user.setCreatedAt(LocalDateTime.now());
    user.setUpdatedAt(LocalDateTime.now()); 
    // データベースに挿入
    userMapper.insertUser(user);
	}

}
