package com.secu.team5.coupon.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.secu.team5.coupon.service.CouponInfoService;
import com.secu.team5.coupon.vo.CouponInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CouponInfoController {

	private final CouponInfoService ciService;
	
	// URL 패턴 coupon-infos 로 통합 ㄱㄱ 셀렉트는 겹치니까 일단 그대로 두고	
	// 등급 쿠폰 지급
	@PostMapping("/coupon-infos")
	public int giveGradeCoupon(CouponInfoVO coupon, Principal principal) {
		return ciService.giveGradeCoupon(coupon, principal);
	}
	
	// 내 쿠폰 조회
	@GetMapping("/coupon-infos")
	public List<CouponInfoVO> getCouponListbyUiNum(Principal principal){
		return ciService.selectCouponByuiNum(0, principal);
	}
	
	// 결제에 사용된 쿠폰 액티브 수정
	@PatchMapping("/coupon-infos")
	public int updateUsedCoupon(@RequestBody CouponInfoVO coupon, Principal principal) {
		log.info("coupon=>{}",coupon);
		return ciService.updateUsedCoupon(coupon, principal);
	}
}
