package com.example.vjava_ec.repository.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.vjava_ec.entity.OrderItem;

/**
 * 注文商品マッパーインターフェース
 */
@Mapper
public interface OrderItemMapper {
	
	/**
	 * 注文商品情報の保存
	 */
	void insertItem(List<OrderItem> orderItems);
}
