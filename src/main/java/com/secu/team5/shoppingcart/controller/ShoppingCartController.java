package com.secu.team5.shoppingcart.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.secu.team5.shoppingcart.service.ShoppingCartService;
import com.secu.team5.shoppingcart.vo.ShoppingCartVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ShoppingCartController{

	private final ShoppingCartService shoppingCartService;
	
	//장바구니 넣기
	//기존 url /shopping-cart
	@PostMapping("/shopping-carts")
	public int insertCart(@RequestBody ShoppingCartVO shoppingCartVO, Principal principal) {
		log.info("shoppingCartVO=>{}", shoppingCartVO);
		return shoppingCartService.insertCart(shoppingCartVO, principal);
	}
	
	//장바구니 삭제
	//기존 url /shopping-cart
	@DeleteMapping("/shopping-carts")
	public int deleteCart(@RequestBody ShoppingCartVO shoppingCartVO) {
		log.info("shoppingCartVO=>{}", shoppingCartVO);
		return shoppingCartService.deleteCart(shoppingCartVO);
	}
	
	//장바구니 확인 
	@GetMapping("/shopping-carts")
	//기존 url /shopping-cart
	public List<ShoppingCartVO> selectCartWithUiNum(ShoppingCartVO shoppingCartVO, Principal principal) {
		log.info("shoppingCartVO=>{}", shoppingCartVO);
		return shoppingCartService.selectCartWithUiNum(shoppingCartVO, principal);
	}
	
	
	//구매를위해 장바구니를 정리하고 다시 넣는작업
	@DeleteMapping("/shop-cart")
	public int deleteCartForBuy(ShoppingCartVO shoppingCartVO, Principal principal) {
		return shoppingCartService.deleteCartForBuy(shoppingCartVO, principal);
	}
	
	
}
