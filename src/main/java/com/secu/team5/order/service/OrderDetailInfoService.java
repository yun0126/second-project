package com.secu.team5.order.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.secu.team5.order.mapper.OrderDetailInfoMapper;
import com.secu.team5.order.vo.OrderDetailInfoVO;
import com.secu.team5.product.mapper.ProductFileInfoMapper;
import com.secu.team5.user.mapper.UserInfoMapper;
import com.secu.team5.user.vo.UserInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderDetailInfoService {
	
	private final OrderDetailInfoMapper orderDetailInfoMapper;
	private final ProductFileInfoMapper productFileInfoMapper;
	private final UserInfoMapper userInfoMapper;
	
	
	//주문내역 추가
	public int insertODI(OrderDetailInfoVO odi, Principal principal) {
		UserInfoVO user = userInfoMapper.selectUserInfoById(principal.getName());
		odi.setUiNum(user.getUiNum());
		
		return orderDetailInfoMapper.insertOrderDetailInfo(odi);
	}
	
	//주문내역 조회
	public List<OrderDetailInfoVO> selectODI(int oiNum){
		List<OrderDetailInfoVO> odis = orderDetailInfoMapper.selectOrderDetailInfo(oiNum);
		for(OrderDetailInfoVO odi : odis) {
			odi.setProductFiles(productFileInfoMapper.selectProductFileInfo(odi.getPiNum())); 
		}
		return odis; 
	}
	
	//리뷰용 주문내역 조회
	public OrderDetailInfoVO getOdi(int odiNum) {
		OrderDetailInfoVO odi = orderDetailInfoMapper.selectOdi(odiNum); 
		odi.setProductFiles(productFileInfoMapper.selectProductFileInfo(odi.getPiNum()));
		return odi;
	}
	
	//주문상세내역 조회
	public List<OrderDetailInfoVO> selectODIbyUINUM(OrderDetailInfoVO odi, Principal principal) {
		UserInfoVO user = userInfoMapper.selectUserInfoById(principal.getName());
		odi.setUiNum(user.getUiNum());
		return orderDetailInfoMapper.selectOrderDetailsbyUINUM(odi);
	}
	
	//그래프 그리기를위한 데이터 조회
	public List<OrderDetailInfoVO> seledOrderDetailALL(OrderDetailInfoVO odi){
		return orderDetailInfoMapper.seledOrderDetailALL(odi);
	}
	
	public PageInfo<OrderDetailInfoVO> selectODIbyUINUMwithHelper(OrderDetailInfoVO odi, Principal principal) {
		UserInfoVO user = userInfoMapper.selectUserInfoById(principal.getName());
		odi.setUiNum(user.getUiNum());
		PageHelper.startPage(odi.getPage(), odi.getPageSize());
		log.info("odi=>{}",odi);
		
		PageInfo<OrderDetailInfoVO> pageInfo = new PageInfo<>(orderDetailInfoMapper.selectOrderDetailsbyUINUMwithHelper(odi), 10);

		return pageInfo;
	}
}
