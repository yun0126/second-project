package com.secu.team5.coupon.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.secu.team5.coupon.service.CouponTypeInfoService;
import com.secu.team5.coupon.vo.CouponTypeInfoVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CouponTypeInfoController {
	
	private final CouponTypeInfoService cptiService;
	
	//쿠폰 종류 생성
	@PostMapping("/insert-type")
	public int insertCouponTypeInfo(@RequestBody CouponTypeInfoVO type) {
		return cptiService.createCouponTypeInfos(type);
	}

}
