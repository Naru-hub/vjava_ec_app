package com.example.vjava_ec.helper.user;

import com.example.vjava_ec.entity.User;
import com.example.vjava_ec.form.user.UserForm;

/**
 * 会員側UserControllerのHelperクラス
 */
public class UserHelper {
	
	/**
	 * UserFormをUserエンティティに変換
	 * @param form 入力情報が入っているUserFormクラス
	 * @return User UserFormを
	 */
	public static User convertUser(UserForm form) {
		User user = new User();
		user.setId(form.getUserId());
		user.setName(form.getName());
		user.setEmail(form.getEmail());
		user.setPostcode(form.getPostcode());
		user.setAddress(form.getAddress());
		user.setTel(form.getTel());
		return user;
	}
	/**
	 * 
	 * @param user
	 * @return UserForm
	 */
	public static UserForm convertUserForm(User user) {
		UserForm form = new UserForm();
		form.setUserId(user.getId());
		form.setName(user.getName());
		form.setEmail(user.getEmail());
		form.setPostcode(user.getPostcode());
		form.setAddress(user.getAddress());
		form.setTel(user.getTel());
		return form;				
	}
	
	
}