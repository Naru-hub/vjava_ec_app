package com.example.vjava_ec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;
/*
 * Spring Securityの設定をするクラス
 * 今回は会員と管理者で別のFilterChainを設定している
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	// DI
	private final CustomAuthenticationProvider customAuthenticationProvider;
	
	/**
     * 管理者用のSecurityFilterChainを設定するメソッド
     * "/admin/**"のURLに対するアクセス制御を設定している
     * 
     * @param http HttpSecurityオブジェクト
     * @return SecurityFilterChainオブジェクト
     * @throws Exception セキュリティ設定の構築時に発生する例外
     */
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.securityMatcher("/admin/**")
		// ★HTTPリクエストに対するセキュリティ設定
		.authorizeHttpRequests(authz -> authz
				// 下記のアクセスは認証を必要としない
				.requestMatchers("/admin/login","/admin/authentication").permitAll()
				// 【管理者権限設定】url:/admin/**はadmin管理者しかアクセスできない
				.requestMatchers("/admin/**").hasAuthority("ADMIN")
				// その他のリクエストは認証が必要
				.anyRequest().authenticated())
		// ★フォームベースのログイン設定
		.formLogin(form -> form
				// カスタムログインページのURLを指定
				.loginPage("/admin/login")
				// ログイン処理のURLを指定
				.loginProcessingUrl("/admin/authentication")
				// メールアドレスのname属性を指定
				.usernameParameter("email")
				// パスワードのname属性を指定
				.passwordParameter("password")
				// ログイン成功時のリダイレクト先を指定
				.defaultSuccessUrl("/admin/item/list")
				// ログイン失敗時のリダイレクト先を指定
				.failureUrl("/admin/login?error"))
		// ★ログアウト設定
		.logout(logout -> logout
				// ログアウトを処理するURLを指定
				.logoutUrl("/admin/logout")
				// ログアウト成功時のリダイレクト先を指定
				.logoutSuccessUrl("/admin/login?logout")
				// ログアウト時にセッションを無効にする
				.invalidateHttpSession(true)
				// ログアウト時にCookieを削除する
				.deleteCookies("JSESSIONID")
				);
		return http.build();
		
	}
	/**
     * ユーザー用のSecurityFilterChainを設定するメソッド。
     * このメソッドでは、"/user/**"のURLに対するアクセス制御を設定します。
     * 
     * @param http HttpSecurityオブジェクト
     * @return SecurityFilterChainオブジェクト
     * @throws Exception セキュリティ設定の構築時に発生する例外
     */
	@Bean
	protected SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {
		http
		.securityMatcher("/user/**")
		// ★HTTPリクエストに対するセキュリティ設定
		.authorizeHttpRequests(authz -> authz
				// 下記のアクセスは認証を必要としない
				.requestMatchers("/user","/user/login/**","/user/logout","/user/register/**", "/user/item/list").permitAll()
				// 【会員権限設定】url:/user/**は会員しかアクセスできない
				.requestMatchers("/user/**").hasAuthority("USER")
				// その他のリクエストは認証が必要
				.anyRequest().authenticated())
		// ★フォームベースのログイン設定
		.formLogin(form -> form
				// カスタムログインページのURLを指定
				.loginPage("/user/login")
				// ログイン処理のURLを指定
				.loginProcessingUrl("/user/authentication")
				// メールアドレスのname属性を指定
				.usernameParameter("email")
				// パスワードのname属性を指定
				.passwordParameter("password")
				// ログイン成功時のリダイレクト先を指定
				.defaultSuccessUrl("/user")
				// ログイン失敗時のリダイレクト先を指定
				.failureUrl("/user/login?error"))
		// ★ログアウト設定
		.logout(logout -> logout
				// ログアウトを処理するURLを指定
				.logoutUrl("/user/logout")
				// ログアウト成功時のリダイレクト先を指定
				.logoutSuccessUrl("/user/login?logout")
				// ログアウト時にセッションを無効にする
				.invalidateHttpSession(true)
				// ログアウト時にCookieを削除する
				.deleteCookies("JSESSIONID")
				);
		return http.build();
		
	}
	/**
     * AuthenticationManagerを設定するメソッド
     * カスタム認証プロバイダを利用した認証マネージャーを構築
     * 
     * @param http HttpSecurityオブジェクト
     * @return AuthenticationManagerオブジェクト
     * @throws Exception 認証マネージャーの構築時に発生する例外
     */
	@Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .authenticationProvider(customAuthenticationProvider)
            .build();
    }
}
