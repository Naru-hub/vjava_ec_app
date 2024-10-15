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
 * 管理者用の {@link UserDetailsService} の実装クラス。
 * 
 * <p>このクラスは、データベースから管理者情報を取得し、Spring Securityの認証プロセスに必要な {@link UserDetails} を提供します。</p>
 */
@Service
@RequiredArgsConstructor
public class AdminUserDetailsServiceImpl implements UserDetailsService {
	/** DI */
	private final AdminMapper adminMapper;

	 /**
     * 指定されたメールアドレスに対応する管理者をロードします。
     * 
     * <p>このメソッドは、データベースから管理者を検索し、該当する管理者が存在する場合、
     * {@link CustomUserDetails} のインスタンスを返します。
     * 管理者が見つからない場合は {@link UsernameNotFoundException} をスローします。</p>
     * 
     * @param email 管理者のメールアドレス
     * @return メールアドレスに対応する {@link UserDetails} オブジェクト
     * @throws UsernameNotFoundException 指定されたメールアドレスに対応する管理者が存在しない場合
     */
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		
		// デバック用
		System.out.println("'Admin'UserDetailsServiceに到達");
		
		// 「認証テーブル」からデータを取得
		Admin admin = adminMapper.selectAdminByEmail(email);
		// 対象データがあれば、UserDetailsの実装クラスを返す
		if (admin != null) {
			// 対象データが存在する
			// UserDetailsの実装クラスを返す
			
			// デバック用
			System.out.println("Adminテーブルでデータ発見");
			
			return new CustomUserDetails(admin.getEmail(),admin.getPassword(),getAuthorityList(admin.getRole()));
		}else{
			// 対象データが存在しない
			throw new UsernameNotFoundException(
					email + " => 指定しているメールアドレスは存在しません");
		}
	}
	/**
     * 管理者の権限リストを取得します。
     * 
     * <p>指定されたロールに基づいて権限をリストとして返します。
     * ロールが ADMIN であれば、USER 権限も追加されます。</p>
     * 
     * @param role 管理者のロール（ADMIN または USER）
     * @return 管理者の権限リスト
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
