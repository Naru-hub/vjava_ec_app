package com.example.vjava_ec.service.user.impl;

import java.time.LocalDateTime;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.vjava_ec.entity.User;
import com.example.vjava_ec.form.user.SignupUserForm;
import com.example.vjava_ec.repository.user.UserMapper;
import com.example.vjava_ec.service.user.TestUserService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TestUserServiceImpl implements TestUserService{
	
	//DI
	private final UserMapper userMapper;
	
    /**
     *会員新規登録
     *
     * @param signupUserForm 
     * @throws Exception 
     */
	
	@Override
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
	
	/**
	 * ログイン状態を確認
	 * ユーザーが登録したemailが合致している場合、trueを返してログアウトを表示
	 * 合致していない場合、falseを返してログインを表示
	 */
	@Override
	public boolean IdentifyUser() {
		final String email = SecurityContextHolder.getContext().getAuthentication().getName();		
		System.out.println(email);
		if(email.equals("anonymousUser") ) {
			return false;
		}
		return true;
	}

}


