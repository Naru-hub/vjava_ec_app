package com.example.vjava_ec.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.vjava_ec.service.admin.impl.AdminUserDetailsServiceImpl;
import com.example.vjava_ec.service.user.impl.UserUserDetailsServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

/**
 * カスタム認証プロバイダー。
 * 
 * <p>このクラスは、管理者またはユーザーのログインに応じて適切な {@link UserDetailsService} を切り替えて
 * 認証を行います。ログインURLに基づき、管理者かユーザーかを判別し、それぞれの認証処理を実行します。</p>
 */
@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	private final AdminUserDetailsServiceImpl adminUserDetailsService;
	private final UserUserDetailsServiceImpl userUserDetailsService;
	private final PasswordEncoder passwordEmcoder;
	/**
     * 認証を行うメソッド。
     * 
     * <p>このメソッドは、認証に必要な {@link Authentication} オブジェクトを受け取り、
     * ユーザーまたは管理者の認証を行います。ログインURLに基づいて、適切な {@link UserDetailsService} を使用して
     * ユーザー情報をロードし、パスワードの照合を行います。</p>
     * 
     * @param authentication 認証リクエストの詳細を含む {@link Authentication} オブジェクト
     * @return 認証に成功した場合、認証済みの {@link UsernamePasswordAuthenticationToken} を返す
     * @throws AuthenticationException 認証に失敗した場合、またはユーザーが見つからなかった場合
     */
	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
		System.out.println("CustomAuthenticationProvider稼働");
		
		String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        // ログインURLに基づいてUserDetailsServiceを切り替え
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String loginUrl2 = request.getRequestURL().toString();
        
        System.out.println("loginUrl2:" + loginUrl2);
        

        UserDetails userDetails;
        if (loginUrl2.contains("http://localhost:8080/admin/authentication")) {
            // 管理者ログイン用のUserDetailsServiceを使用
            userDetails = adminUserDetailsService.loadUserByUsername(email);
        } else if (loginUrl2.contains("http://localhost:8080/user/authentication")) {
            // ユーザー用のUserDetailsServiceを使用
            userDetails = userUserDetailsService.loadUserByUsername(email);
        } else {
            throw new UsernameNotFoundException("Login URL is not recognized");
        }

        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found: " + email);
        }

        // パスワードの照合
        if (!passwordEmcoder.matches(password,userDetails.getPassword())) {
            throw new AuthenticationException("Invalid password") {};
        }

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }
	
	/**
     * この認証プロバイダが対応する認証トークンクラスを指定します。
     * 
     * @param authentication 認証に使用されるトークンのクラス
     * @return {@code true} if {@link UsernamePasswordAuthenticationToken} に対応
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
