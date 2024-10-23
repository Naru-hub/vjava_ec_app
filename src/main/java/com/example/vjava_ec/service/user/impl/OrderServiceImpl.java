package com.example.vjava_ec.service.user.impl;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vjava_ec.entity.Order;
import com.example.vjava_ec.repository.user.OrderMapper;
import com.example.vjava_ec.repository.user.UserMapper;
import com.example.vjava_ec.service.user.OrderService;

import lombok.RequiredArgsConstructor;

/**
 * OrderServiceインターフェースの実装クラス
 * 
 */
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
	
	// DI
	private final OrderMapper orderMapper;
	private final UserMapper userMapper;
	
	/**
	 * ログインしている会員の注文履歴Listを取得
	 * @return List<Order> 注文履歴List
	 */
	@Override
	public List<Order> selectOrderList() {
		// ログインしているユーザのId特定
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		int userId = userMapper.selectUserByEmail(email).getId();
		// 注文履歴の取得
		List<Order> list = orderMapper.selectOrderListByUserId(userId);
		return list;
	}
	 /**
	  * IDから特定の注文履歴を取得
	  * @param id OrderId
	  * @return Order Orderエンティティ
	  */
	@Override
	public Order selectOrderById(int id) {
		Order order = orderMapper.selectOrderByOrderId(id);
		return order;
	}
	
	

}
