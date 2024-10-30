package com.example.vjava_ec.repository.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.vjava_ec.dto.admin.AdminOrderDetailDTO;
import com.example.vjava_ec.dto.admin.AdminOrderHistoryDTO;

/*
 * 管理者：マッパーインターフェイス
 * 注文履歴関連の機能に使用
 */
@Mapper
public interface AdminOrderMapper {
	/**
	 * 全ての注文履歴情報を取得
	 */
	List<AdminOrderHistoryDTO> selectAll();
	
	/**
   	 * 注文履歴情報を取得
   	 */
   	AdminOrderDetailDTO selectById(@Param("Id") Integer id);
   	
   	/**
	 * 注文履歴情報を編集
	 */
	void update(AdminOrderDetailDTO orderDetail);
	
	/**
	 * 注文IDで検索
	 */
	List<AdminOrderHistoryDTO> findByOrderId(Integer orderId);
	
	/**
	 * 注文IDで検索
	 */
	List<AdminOrderHistoryDTO> findByUserName(String userName);
}
