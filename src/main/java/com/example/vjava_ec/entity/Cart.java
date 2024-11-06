package com.example.vjava_ec.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * カート：エンティティ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	/** カートアイテムリスト */
	private List<CartItem> cartItems = new ArrayList<>();
}
