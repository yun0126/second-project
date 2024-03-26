package com.secu.team5.address.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.secu.team5.address.service.AddressInfoService;
import com.secu.team5.address.vo.AddressInfoVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AddressInfoController {
	
	
	@Autowired
	private AddressInfoService aiService;
	
	//마이페이지에서 사용하는 세션을이용한 주소 조회
	@GetMapping("/addr-infos")
	public AddressInfoVO getAddr(Principal principal) {
		return aiService.getAddr(principal);
	}
	
	//관리자 페이지 주소 조회
	//기존주소 viewAddr/{uiNum} 참고
	@GetMapping("/addr-infos/{uiNum}")
	public AddressInfoVO selectAddressInfo(@PathVariable int uiNum) {
		return aiService.selectAddressInfo(uiNum);
	}
	
	//관리자페이지 주소등록
	//기존주소 /register/{uiNum}
	@PostMapping("/addr-infos/{uiNum}")
    public int register(@RequestBody AddressInfoVO addr) {
        return aiService.register(addr);
    }
	

	
	
	//마이페이지 주소 수정 & 주문페이지 주소등록
	@PatchMapping("/addr-infos")
	public int updateAddr(@RequestBody AddressInfoVO addr, Principal principal) {
		log.info("addr =>{}", addr);
		return aiService.updateAddr(addr, principal);
	}
	
}
