package com.secu.team5.shoppingcart.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.secu.team5.product.mapper.ProductFileInfoMapper;
import com.secu.team5.shoppingcart.mapper.ShoppingCartMapper;
import com.secu.team5.shoppingcart.vo.ShoppingCartVO;
import com.secu.team5.user.mapper.UserInfoMapper;
import com.secu.team5.user.vo.UserInfoVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShoppingCartService{
	
	private final ShoppingCartMapper shoppingCartMapper;
	private final UserInfoMapper userInfoMapper;
	private final ProductFileInfoMapper productFileInfoMapper;
	
	//장바구니 넣기 접속해있는사람 정보 가져옴
	public int insertCart(ShoppingCartVO shoppingCartVO, Principal principal) {
		UserInfoVO user = userInfoMapper.selectUserInfoById(principal.getName());
		shoppingCartVO.setUiNum(user.getUiNum());
		return shoppingCartMapper.insertCart(shoppingCartVO);
	}

	//장바구니 삭제
	public int deleteCart(ShoppingCartVO shoppingCartVO) {
	
		return shoppingCartMapper.deleteCart(shoppingCartVO);
	}
	
	//구매하기로 넘어가기위한 장바구니 초기화 
	public int deleteCartForBuy(ShoppingCartVO shoppingCartVO, Principal principal) {
		UserInfoVO user = userInfoMapper.selectUserInfoById(principal.getName());
		shoppingCartVO.setUiNum(user.getUiNum());
		return shoppingCartMapper.deleteCartForBuy(shoppingCartVO);
	}

	//장바구니 조회
	public List<ShoppingCartVO> selectCartWithUiNum(ShoppingCartVO shoppingCartVO, Principal principal) {
		//세션에서 일단 확인해보기
		try {
			principal.getName();
		}catch (Exception e) {
			return null;
		}
		
		
		UserInfoVO user = userInfoMapper.selectUserInfoById(principal.getName());
		//로그인 한 유저가 없는경우 내용없음 
		if(user == null) {
			return null;
		}
		shoppingCartVO.setUiNum(user.getUiNum());
		List<ShoppingCartVO> carts = shoppingCartMapper.selectCartWithUiNum(shoppingCartVO);
		for(ShoppingCartVO cart : carts) {
			cart.setProductFiles(productFileInfoMapper.selectProductFileInfo(cart.getPiNum()));
		}
		return carts;
	}
	
	
}
