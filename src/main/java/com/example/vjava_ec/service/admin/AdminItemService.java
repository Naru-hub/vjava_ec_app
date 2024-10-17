package com.example.vjava_ec.service.admin;

import java.util.List;

import com.example.vjava_ec.dto.admin.adminItemDTO;

public interface AdminItemService {
	/**
	 * 商品一覧情報を検索
	 */
	List<adminItemDTO> findAllItem();
}
