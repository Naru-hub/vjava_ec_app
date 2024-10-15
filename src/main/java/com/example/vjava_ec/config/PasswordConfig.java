package com.example.vjava_ec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * パスワードのエンコーディングに関する設定クラス。
 * 
 * <p>このクラスでは、パスワードを安全にハッシュ化するための
 * {@link PasswordEncoder} の Bean 定義を行います。</p>
 */
@Configuration
public class PasswordConfig {
	
	 /**
     * {@link PasswordEncoder} の実装として {@link BCryptPasswordEncoder} を提供します。
     * 
     * <p>{@link BCryptPasswordEncoder} は、パスワードをハッシュ化する際に強力なセキュリティを
     * 提供するエンコーディングアルゴリズムです。Spring Security でパスワードのエンコーディングや
     * 検証に使用されます。</p>
     * 
     * @return パスワードをエンコードするための {@link BCryptPasswordEncoder} インスタンス
     */
	@Bean
	public PasswordEncoder passwordEncoder() {
		// エンコンコードの設定
		return new BCryptPasswordEncoder();	
	}
}