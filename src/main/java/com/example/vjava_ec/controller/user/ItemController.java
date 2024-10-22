package com.example.vjava_ec.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.vjava_ec.entity.Item;
import com.example.vjava_ec.service.user.TestUserService;
import com.example.vjava_ec.service.user.UserItemService;

import lombok.RequiredArgsConstructor;

/**
 * 商品コントローラ
 * ユーザーのログイン状態の確認を行う
 */
@Controller("userItem")
@RequestMapping("/user/item")
@RequiredArgsConstructor
public class ItemController {
	//商品情報を確認するサービスクラス
    private final UserItemService userItemService;
    //ユーザー情報を確認するサービスクラス
    private final TestUserService testUserService;

    /**
     * 商品一覧を表示
     * @param model 
     * @return user/itemList
     */
    @GetMapping("/list") 
    public String showItemList(Model model) {
        // 商品一覧を取得
        List<Item> items = userItemService.getAllItems();
        // モデルに商品情報を追加
         model.addAttribute("items", items);
        // ユーザーがログイン中か確認する
        model.addAttribute("islogin",testUserService.IdentifyUser());
        System.out.println(testUserService.IdentifyUser());
        return "user/itemList"; 
    }

}
