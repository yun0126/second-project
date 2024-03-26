package com.secu.team5.shoppingcart.mapper;

import java.util.List;

import com.secu.team5.shoppingcart.vo.ShoppingCartVO;

public interface ShoppingCartMapper {
	//장바구니 추가
	int insertCart(ShoppingCartVO shoppingCartVO);
	//장바구니 삭제
	int deleteCart(ShoppingCartVO shoppingCartVO);
	//장바구니 정리하고 다시 넣기위한 것
	int deleteCartForBuy(ShoppingCartVO shoppingCartVO);
	//유저에 따른 장바구니 조회
	List<ShoppingCartVO> selectCartWithUiNum(ShoppingCartVO shoppingCartVO);
	
}
