package com.secu.team5.order.mapper;

import java.util.List;

import com.secu.team5.order.vo.OrderInfoVO;

public interface OrderInfoMapper {
	//주문내용 넣기
	int insertOrderInfo(OrderInfoVO order);
	//고유한 주문번호를 통해 주문조회
	OrderInfoVO selectOrderInfoWithUUID(String oiMerchantUid);
	//한유저의 전체 주문목록들 조회
	List<OrderInfoVO> selectOrderInfoWithUINUM(int uiNum);
	
	
}
