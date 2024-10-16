package com.example.vjava_ec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * パスワードのエンコーディングに関する設定クラス
 * 
 * パスワードを安全にハッシュ化するための
 * {@link PasswordEncoder} の Bean 定義を行う
 */
@Configuration
public class PasswordConfig {
	
	 /**
     * {@link PasswordEncoder} の実装として {@link BCryptPasswordEncoder} を提供する
     * 
     * Spring Security でパスワードのエンコーディングや検証に使用される
     * 
     * @return パスワードをエンコードするための {@link BCryptPasswordEncoder} インスタンス
     */
	@Bean
	public PasswordEncoder passwordEncoder() {
		// エンコンコードの設定
		return new BCryptPasswordEncoder();	
	}
}