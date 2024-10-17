package com.example.vjava_ec.repository.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.vjava_ec.entity.Item;

@Mapper
public interface ItemMapper {
	// 削除フラグがfalseの商品のみ取得
	 @Select("SELECT * FROM items WHERE is_deleted = false") 
	    List<Item> findAll();

}
