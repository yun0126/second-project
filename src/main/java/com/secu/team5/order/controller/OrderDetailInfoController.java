package com.secu.team5.order.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.secu.team5.order.service.OrderDetailInfoService;
import com.secu.team5.order.vo.OrderDetailInfoVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderDetailInfoController {
	
	private final OrderDetailInfoService odiService;
	
	// 주문 상세목록 뷰
	@GetMapping("/order-details/{oiNum}")
	List<OrderDetailInfoVO> getOrderDetails(@PathVariable int oiNum){
		return  odiService.selectODI(oiNum);
	}
	

	// 주문정보 1개 
	// 기존 url /order-details-odi/{odiNum}
	@GetMapping("/order-infos/order-details/{odiNum}")
	OrderDetailInfoVO getOdi(@PathVariable int odiNum){
		return odiService.getOdi(odiNum);
	}
	
	// 내 주문 상세내역 조회
	@GetMapping("/order-details")
	List<OrderDetailInfoVO> selectOrderDetailsbyUINUM(OrderDetailInfoVO odi, Principal principal) {
		return odiService.selectODIbyUINUM(odi, principal);
	}
	
	//그래프 그리기를 하기위한 정보 조회
	//기존 url /order-detailsAll
	@GetMapping("/order-infos/order-details")
	List<OrderDetailInfoVO> seledOrderDetailALL(OrderDetailInfoVO odi){
		return odiService.seledOrderDetailALL(odi);
	}
	
	//내 주문 상세내역 페이징
	@GetMapping("/order-details/helper")
	PageInfo<OrderDetailInfoVO> selectOrderDetailsbyUINUMwithHepler(OrderDetailInfoVO odi, Principal principal) {
		return odiService.selectODIbyUINUMwithHelper(odi, principal);
	}
}
