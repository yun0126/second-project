package com.secu.team5.order.mapper;

import java.util.List;

import com.github.pagehelper.Page;
import com.secu.team5.order.vo.OrderDetailInfoVO;

public interface OrderDetailInfoMapper {
	//주문내용 넣기
	int insertOrderDetailInfo(OrderDetailInfoVO odi);
	//주문내용 조회
	List<OrderDetailInfoVO> selectOrderDetailInfo(int oiNum);
	//리뷰쓸때 주문내용조회
	OrderDetailInfoVO selectOdi(int odiNum);
	//주문 상세내역 조회(리뷰 작성용)
	List<OrderDetailInfoVO> selectOrderDetailsbyUINUM(OrderDetailInfoVO odi);
	//모든 주문내역 조회
	List<OrderDetailInfoVO> seledOrderDetailALL(OrderDetailInfoVO odi);
	// 페이지 헬퍼를 이용한 주문 상세내역
	Page<OrderDetailInfoVO> selectOrderDetailsbyUINUMwithHelper(OrderDetailInfoVO odi);
	
	
}
