package com.secu.team5.order.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.secu.team5.coupon.service.CouponInfoService;
import com.secu.team5.coupon.vo.CouponInfoVO;
import com.secu.team5.order.mapper.OrderInfoMapper;
import com.secu.team5.order.vo.OrderDetailInfoVO;
import com.secu.team5.order.vo.OrderInfoVO;
import com.secu.team5.shoppingcart.service.ShoppingCartService;
import com.secu.team5.shoppingcart.vo.ShoppingCartVO;
import com.secu.team5.user.mapper.UserInfoMapper;
import com.secu.team5.user.service.UserInfoService;
import com.secu.team5.user.vo.UserInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderInfoService {

	// OI TABLE
	private final OrderInfoMapper oiMapper;
	// ODI TABLE
	private final OrderDetailInfoService odiService;
	// SHOPPING CART TABLE
	private final ShoppingCartService scService;
	// USER TABLE
	private final UserInfoMapper uiMapper;
	private final UserInfoService uiService;
	// COUPON TABLE
	private final CouponInfoService ciService;

	// 주문내역에 추가
	public int insertOI(OrderInfoVO oi, Principal principal) {
		// 세션에서 uiNum 찾아서 적용
		UserInfoVO user = uiMapper.selectUserInfoById(principal.getName());
		oi.setUiNum(user.getUiNum());

		int result = 0;
		// 주문내역 추가
		result += oiMapper.insertOrderInfo(oi);

		List<OrderDetailInfoVO> odis = oi.getOrderDetails();
		for (OrderDetailInfoVO odi : odis) {
			// 주문 디테일에 추가
			odi.setOiNum(oi.getOiNum());
			result += odiService.insertODI(odi, principal);
		}

		log.info("Before user=>{}", user);
		result += uiService.gradeUpdate(user);
		log.info("After user=>{}", user);

		CouponInfoVO coupon = new CouponInfoVO();
		log.info("Before coupon=>{}", coupon);
		result += ciService.giveGradeCoupon(coupon, principal);
		log.info("After coupon=>{}", coupon);

		ShoppingCartVO shopVO = new ShoppingCartVO();
		//단일 상품 구매가아니라면 장바구니를 통해 결제를 들어왔으므로 장바구니를 삭제하지 않는다.
		if (!oi.getMessage().equals("single")) {
			// 장바구니 삭제
			result += scService.deleteCartForBuy(shopVO, principal);
		}

		return result;
	}

	// 주문내역 조회
	public OrderInfoVO selectOI(String oiMerchantUid) {
		OrderInfoVO oi = oiMapper.selectOrderInfoWithUUID(oiMerchantUid);
		oi.setOrderDetails(odiService.selectODI(oi.getOiNum()));

		return oi;
	}

	// 주문내역들 조회
	public List<OrderInfoVO> selectOrderInfos(Principal principal) {
		UserInfoVO user = uiMapper.selectUserInfoById(principal.getName());

		return oiMapper.selectOrderInfoWithUINUM(user.getUiNum());
	}
}
