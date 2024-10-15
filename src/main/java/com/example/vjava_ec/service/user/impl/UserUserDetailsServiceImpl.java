package com.example.vjava_ec.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.vjava_ec.entity.CustomUserDetails;
import com.example.vjava_ec.entity.Role;
import com.example.vjava_ec.entity.User;
import com.example.vjava_ec.repository.user.UserMapper;

import lombok.RequiredArgsConstructor;

/**
 * 認証に使用される {@link UserDetailsService} の実装クラス。
 * 
 * <p>このクラスは、データベースからユーザー情報を取得し、
 * Spring Securityの認証プロセスに必要な {@link UserDetails} を提供します。</p>
 */
@Service
@RequiredArgsConstructor
public class UserUserDetailsServiceImpl  implements UserDetailsService{
	
	// デバック用
	private final UserMapper userMapper;
	
	/**
     * 指定されたメールアドレスに対応するユーザーをロードします。
     * 
     * <p>このメソッドは、データベースからユーザーを検索し、該当するユーザーが存在する場合、
     * {@link CustomUserDetails} のインスタンスを返します。
     * ユーザーが見つからない場合は {@link UsernameNotFoundException} をスローします。</p>
     * 
     * @param email ユーザーのメールアドレス
     * @return メールアドレスに対応する {@link UserDetails} オブジェクト
     * @throws UsernameNotFoundException 指定されたメールアドレスに対応するユーザーが存在しない場合
     */
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		
		System.out.println("'User'UserDetailsServiceには到達");
		
		// 「認証テーブル」からデータを取得
		User user = userMapper.selectUserByEmail(email);
		// 対象データがあれば、UserDetailsの実装クラスを返す
		if (user != null) {
			// 対象データが存在する
			// UserDetailsの実装クラスを返す
			
			// デバック用
			System.out.println("Userテーブルでデータ発見");
			
			return new CustomUserDetails(user.getEmail(),user.getPassword(),getAuthorityList(user.getRole()));
		}else{
			// 対象データが存在しない
			throw new UsernameNotFoundException(
					email + " => 指定しているメールアドレスは存在しません");
		}
	}	
	/**
     * ユーザーの権限リストを取得します。
     * 
     * <p>指定されたロールに基づいて権限をリストとして返します。
     * ロールが ADMIN であれば、USER 権限も追加されます。</p>
     * 
     * @param role ユーザーのロール（ADMIN または USER）
     * @return ユーザーの権限リスト
     */
	private List<GrantedAuthority> getAuthorityList(Role role) {
		// 権限リスト
		List<GrantedAuthority> authorities = new ArrayList<>();
		// 列挙型からロールを取得
		authorities.add(new SimpleGrantedAuthority(role.name()));
		// ADMIN ロールの場合、USERの権限も付与
		if (role == Role.ADMIN) {
			authorities.add(
					new SimpleGrantedAuthority(Role.USER.toString()));
		}
		return authorities;
	}
}
