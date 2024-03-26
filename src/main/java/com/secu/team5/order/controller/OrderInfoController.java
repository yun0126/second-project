package com.secu.team5.order.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.secu.team5.order.service.OrderInfoService;
import com.secu.team5.order.vo.OrderInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderInfoController {
	
	private final OrderInfoService oiService;
	//주문내역에 넣기
	@PostMapping("/order-infos")
	public int insertOrder(@RequestBody OrderInfoVO order, Principal principal) {
		log.info("order=>{}",order);
		return oiService.insertOI(order, principal);
	}
	
	//주문내역 확인 1개 
	@GetMapping("/order-infos/{oiMerchantUid}")
	public OrderInfoVO selectOrder(@PathVariable String oiMerchantUid) {
		log.info("oiMerchantUid=>{}",oiMerchantUid);
		return oiService.selectOI(oiMerchantUid);
	}
	//주문내역 확인 여러개
	@GetMapping("/order-infos")
	public List<OrderInfoVO> selectOrderInfos(Principal principal){
		return oiService.selectOrderInfos(principal);
	}
}
