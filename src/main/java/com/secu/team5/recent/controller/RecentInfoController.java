package com.secu.team5.recent.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.secu.team5.recent.service.RecentInfoService;
import com.secu.team5.recent.vo.RecentInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RecentInfoController {

	private final RecentInfoService riService;
	//최근본상품에 추가
	//기존 url /add-recent
	@PostMapping("/recent-infos")
	public int addRecentInfo(@RequestBody RecentInfoVO recent, Principal principal) {
		log.info("recent=>{}",recent);
		return riService.addRecentInfo(recent, principal);
	}
	//최근본상품 조회 10개까지 조회
	//기존 url /view-recent
	@GetMapping("/recent-infos")
	public List<RecentInfoVO> show10RecentProds (RecentInfoVO recent, Principal principal) {
		return riService.show10recentprods(recent, principal);
	}
}
