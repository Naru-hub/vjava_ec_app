package com.example.vjava_ec.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.vjava_ec.entity.Item;
import com.example.vjava_ec.service.user.ItemService;
import com.example.vjava_ec.service.user.UserService;

import lombok.RequiredArgsConstructor;

/**
 * 商品コントローラ
 */
@Controller("userItem")
@RequestMapping("/user/item")
@RequiredArgsConstructor
public class ItemController {
	
	//DI
    private final ItemService itemService;
    private final UserService userService;

    /**
     * 商品一覧を表示
     * @param model 
     * @return user/itemList 商品一覧画面
     */
    @GetMapping("/list") 
    public String showItemList(Model model) {
        // 商品一覧を取得
        List<Item> items = itemService.getAllItems();
        // モデルに商品情報を追加
         model.addAttribute("items", items);
        // ユーザーがログイン中か確認する
        model.addAttribute("islogin",userService.IdentifyUser());
        return "user/item/list"; 
    }
    
    /**
     * 商品詳細画面を表示
     * @param id
     * @param model
     * @return user/item/detail 商品詳細画面
     */
    @GetMapping("/{id}")
    public String showItemDetail(@PathVariable Integer id,Model model) {
    	// IDから特定のItemエンティティを取得
    	Item item = itemService.selectItemById(id);
    	// モデルに商品情報を追加
    	model.addAttribute("item", item);
    	return "user/item/detail";
    }
}
