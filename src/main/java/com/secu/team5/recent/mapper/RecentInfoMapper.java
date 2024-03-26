package com.secu.team5.recent.mapper;

import java.util.List;

import com.secu.team5.recent.vo.RecentInfoVO;

public interface RecentInfoMapper {
	//최근 본 상품조회 10개까지
	List<RecentInfoVO> selectRecent10prods (RecentInfoVO recent);
	//최근 본 상품 추가
	int insertRecentInfo(RecentInfoVO recent);
}
