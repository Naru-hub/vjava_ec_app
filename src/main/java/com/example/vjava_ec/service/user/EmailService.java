package com.example.vjava_ec.service.user;

import com.example.vjava_ec.entity.Order;
import com.example.vjava_ec.entity.User;

/**
 * EmailServiceのinterfaceクラス
 */
public interface EmailService {
	
	/**
	 * 購入確認メールを送る
	 */
	void sendThanksEmail(Order order,User user);
	
	/**
	 * メールアドレス認証のメールを送る
	 */
	int sendConfirmEmail(String mailAddress);
	
	/**
	 * パスワード再設定のメールを送る
	 */
	String sendResetPasswordEmail(String mailAddress);
}
