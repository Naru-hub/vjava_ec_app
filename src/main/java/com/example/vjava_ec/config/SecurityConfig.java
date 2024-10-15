package com.example.vjava_ec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomAuthenticationProvider customAuthenticationProvider;
	
	// SecurityFilterChainのBean定義
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.securityMatcher("/admin/**")
		// ★HTTPリクエストに対するセキュリティ設定
		.authorizeHttpRequests(authz -> authz
				// 「/login」へのアクセスは認証を必要としない
				.requestMatchers("/admin/login","/admin/authentication").permitAll()
				// ▽▽▽▽▽ リストA.29 ▽▽▽▽▽
				// 【管理者権限設定】url:/todos/**は管理者しかアクセスできない
				.requestMatchers("/admin/**").hasAuthority("ADMIN")
				// その他のリクエストは認証が必要
				.anyRequest().authenticated())
		// ★フォームベースのログイン設定
		.formLogin(form -> form
				// ログイン処理のURLを指定
				.loginProcessingUrl("/admin/authentication")
				// ユーザー名のname属性を指定
				.usernameParameter("email")
				// パスワードのname属性を指定
				.passwordParameter("password")
				// ログイン成功時のリダイレクト先を指定
				.defaultSuccessUrl("/admin/items")
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
		// △△△△△ リストA.5 △△△△△
		return http.build();
		
	}
	
	@Bean
	protected SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {
		http
		.securityMatcher("/user/**")
		// ★HTTPリクエストに対するセキュリティ設定
		.authorizeHttpRequests(authz -> authz
				// 「/login」へのアクセスは認証を必要としない
				.requestMatchers("/user","/user/login","/user/logout").permitAll()
				// ▽▽▽▽▽ リストA.29 ▽▽▽▽▽
				// 【管理者権限設定】url:/admin/**は管理者しかアクセスできない
				.requestMatchers("/admin/**").hasAuthority("ADMIN")
				// その他のリクエストは認証が必要
				.anyRequest().authenticated())
		// ★フォームベースのログイン設定
		.formLogin(form -> form
				// カスタムログインページのURLを指定
				.loginPage("/user/login")
				// ログイン処理のURLを指定
				.loginProcessingUrl("/user/authentication")
				// ユーザー名のname属性を指定
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
	
	@Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .authenticationProvider(customAuthenticationProvider)
            .build();
    }
}
