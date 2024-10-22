package com.example.vjava_ec.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.vjava_ec.entity.Item;
import com.example.vjava_ec.service.user.TestItemService;
import com.example.vjava_ec.service.user.TestUserService;

import lombok.RequiredArgsConstructor;

/**
 * 商品コントローラ
 */
@Controller("userItem")
@RequestMapping("/user/item")
@RequiredArgsConstructor
public class ItemController {
	//商品情報を操作するサービスクラス
    private final TestItemService testItemService;
    //ユーザー情報を操作するサービスクラス
    private final TestUserService testUserService;

    /**
     * 商品一覧を表示するメソッド
     * @param model 
     * @return user/itemList
     */
    @GetMapping("/list") 
    public String showItemList(Model model) {
        // 商品一覧を取得
        List<Item> items = testItemService.getAllItems();
        // モデルに商品情報を追加
         model.addAttribute("items", items);
        // ユーザーがログイン中か確認する
        model.addAttribute("islogin",testUserService.IdentifyUser());
        System.out.println(testUserService.IdentifyUser());
        return "user/itemList"; 
    }

}
