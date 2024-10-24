package com.example.vjava_ec.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.vjava_ec.entity.Admin;
import com.example.vjava_ec.entity.CustomUserDetails;
import com.example.vjava_ec.entity.Role;
import com.example.vjava_ec.repository.admin.AdminMapper;

import lombok.RequiredArgsConstructor;

/**
 * 管理者用の {@link UserDetailsService} の実装クラス
 * 
 * データベースから管理者情報を取得し、Spring Securityの認証プロセスに必要な {@link UserDetails} を提供する
 */
@Service
@RequiredArgsConstructor
public class AdminUserDetailsServiceImpl implements UserDetailsService {
	// DI
	private final AdminMapper adminMapper;

	 /**
     * 指定されたメールアドレスに対応する管理者をロードするメソッド
     * 
     * データベースから管理者を検索し、該当する管理者が存在する場合、
     * {@link CustomUserDetails} のインスタンスを返す。
     * 管理者が見つからない場合は {@link UsernameNotFoundException} をスロー
     * 
     * @param email 管理者のメールアドレス
     * @return メールアドレスに対応する {@link UserDetails} オブジェクト
     * @throws UsernameNotFoundException 指定されたメールアドレスに対応する管理者が存在しない場合
     */
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
	
		// 「管理者テーブル」からデータを取得
		Admin admin = adminMapper.selectAdminByEmail(email);
		// 対象データがあれば、UserDetailsの実装クラスを返す
		if (admin != null) {
			
			// UserDetailsの実装クラスを返す
			return new CustomUserDetails(admin.getEmail(),admin.getPassword(),getAuthorityList(admin.getRole()));
		}else{
			// 対象データが存在しない
			throw new UsernameNotFoundException(
					email + " => 指定しているメールアドレスは存在しません");
		}
	}
	/**
     * 権限リストを取得するメソッド
     * 
     * 指定されたロールに基づいて権限をリストとして返す
     * ロールが ADMIN であれば、USER 権限も追加
     * 
     * @param role 管理者のロール（ADMIN または USER）
     * @return 管理者の権限リスト
     */
	private List<GrantedAuthority> getAuthorityList(Role role) {
		// 権限リスト
		List<GrantedAuthority> authorities = new ArrayList<>();
		// 列挙型からロールを取得
		authorities.add(new SimpleGrantedAuthority(role.name()));
		return authorities;
	}
}
