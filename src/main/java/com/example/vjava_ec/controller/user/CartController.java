package com.example.vjava_ec.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.vjava_ec.entity.Cart;
import com.example.vjava_ec.entity.CartItem;
import com.example.vjava_ec.helper.user.CartHelper;
import com.example.vjava_ec.service.user.CartService;
import com.example.vjava_ec.service.user.ItemService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * カートコントローラ
 */
@Controller
@RequestMapping("/user/cart")
@RequiredArgsConstructor
public class CartController {
	
	/** DI */
	private final CartService cartService;
	private final ItemService itemService;
	
	/**
	 * カートに商品を追加
	 * @param cartItem
	 * @param session
	 * @return "redirect:/cart" カート画面を表示
	 */
	@PostMapping("/add")
	public String addCartItem(@RequestParam("itemId") int itemId,@RequestParam("amount") int amount, HttpSession session) {
		CartItem cartItem = CartHelper.convertCart(itemService.selectItemById(itemId), amount);
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		cartService.addCartItem(cart, cartItem);
		return "redirect:/user/cart/show";
	}
	
	/**
	 * カート画面の表示
	 * @param session
	 * @param model
	 * @return user/cart/cart カート画面
	 */
    @GetMapping("/show")
    public String showCart(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        model.addAttribute("cart", cart);
        model.addAttribute("displayTotalPrice", cartService.getDisplayTotalPrice(cart));
        return "user/cart/cart";
    }
    
    /**
     * カートアイテムの数量を変更
     * @param id
     * @param amount
     * @param session
     * @param model
     * @return redirect:/user/cart/show カート画面に戻す
     */
    @PostMapping("/edit/{id}")
    public String updateCartItemAmount(@PathVariable Integer id,@RequestParam("amount") Integer amount,HttpSession session, Model model) {
    	Cart cart = (Cart) session.getAttribute("cart");
    	cartService.editCartItemAmount(cart, id, amount);
    	return "redirect:/user/cart/show";
    }
   
    /**カートアイテムを個別の削除
     * 
     * @param id
     * @param session
     * @param model
     * @return redirect:/user/cart/show カート画面に戻す
     */
    @PostMapping("/delete/{id}")
    public String deleteCartItem(@PathVariable Integer id,HttpSession session) {
    	Cart cart = (Cart) session.getAttribute("cart");
    	cartService.deleteCartItemByItemId(cart, id);
    	return "redirect:/user/cart/show";
    }
    
    /**
     * カートを空にする
     * @param session
     * @param model
     * @return redirect:/user/cart/show カート画面に戻す
     */
    @PostMapping("/delete/all")
    public String allDeleteCartItems(HttpSession session, Model model) {
    	Cart cart = (Cart) session.getAttribute("cart");
    	cartService.deleteAllCartItem(cart);
    	return "redirect:/user/cart/show";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
