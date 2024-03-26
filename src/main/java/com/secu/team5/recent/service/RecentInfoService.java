package com.secu.team5.recent.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.secu.team5.product.mapper.ProductFileInfoMapper;
import com.secu.team5.recent.mapper.RecentInfoMapper;
import com.secu.team5.recent.vo.RecentInfoVO;
import com.secu.team5.user.mapper.UserInfoMapper;
import com.secu.team5.user.vo.UserInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecentInfoService {
	
	private final RecentInfoMapper riMapper;
	private final UserInfoMapper uiMapper;
	private final ProductFileInfoMapper pfiMapper;
	
	//최근 본 상품 추가
	public int addRecentInfo(RecentInfoVO recent, Principal principal) {
		UserInfoVO user = new UserInfoVO();
		user = uiMapper.selectUserInfoById(principal.getName());
		recent.setUiNum(user.getUiNum());
		log.info("user=>{}",user);
		return riMapper.insertRecentInfo(recent);
	}
	//최근 본 상품 조회 10개
	public List<RecentInfoVO> show10recentprods(RecentInfoVO recent, Principal principal) {
		UserInfoVO user = new UserInfoVO();
		user = uiMapper.selectUserInfoById(principal.getName());
		recent.setUiNum(user.getUiNum());
		
		List<RecentInfoVO> objs = riMapper.selectRecent10prods(recent);
		for(RecentInfoVO obj : objs) {
			obj.setProductFiles(pfiMapper.selectProductFileInfo(obj.getPiNum()));
		}
		return objs;
	}

}
