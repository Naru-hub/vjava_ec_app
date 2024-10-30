package com.example.vjava_ec.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "user/itemList"; 
    }
    
    /**
     * 商品検索ツール
     * @param keyword 検索ワード
     * @param model
     * @return user/itemList 商品検索結果一覧画面
     */
    @GetMapping("/search") 
    public String showSearchedItemList(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
    	//検索結果を入れるリスト
    	List<Item> searchItemsList;
    	// 検索キーワードがある場合は部分一致検索を行う
    	if(keyword != null && !keyword.isEmpty()) {
    	   searchItemsList = itemService.searchItems(keyword);
    	   // 検索結果が空の場合にメッセージを表示するためのフラグを追加
    	   if(searchItemsList.isEmpty()) {
    		   	model.addAttribute("message", "関連した商品はありませんでした");
    	   }
    	} else {
    		// キーワードがない場合は全商品を取得
    		searchItemsList = itemService.getAllItems();
    	}
        // モデルに商品情報を追加
        model.addAttribute("items", searchItemsList);
        // ユーザーがログイン中か確認する
        model.addAttribute("islogin", userService.IdentifyUser());
        // 検索キーワードをモデルに追加
        model.addAttribute("keyword", keyword);
    		
        return "user/itemList";
    	}
    }

