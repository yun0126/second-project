package com.secu.team5.address.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secu.team5.address.mapper.AddressInfoMapper;
import com.secu.team5.address.vo.AddressInfoVO;
import com.secu.team5.user.mapper.UserInfoMapper;
import com.secu.team5.user.vo.UserInfoVO;

@Service
public class AddressInfoService {

	@Autowired
	private AddressInfoMapper aiMapper;

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	//관리자페이지 주소조회
	public AddressInfoVO selectAddressInfo(int uiNum) {
		AddressInfoVO addressInfo = aiMapper.selectAddressInfo(uiNum);

		// 주소 정보가 없는 경우에 대한 처리
		if (addressInfo == null) {
			addressInfo = new AddressInfoVO();
			addressInfo.setMessage("주소 정보가 없습니다. 주소를 등록해주세요.");
		}

		return addressInfo;
	}
	//관리자페이지 주소등록
	public int register(AddressInfoVO addr) {
		AddressInfoVO addressInfo = aiMapper.selectAddressInfo(addr.getUiNum());
		
		// 주소 정보가 없는 경우에 대한 처리
		if (addressInfo != null) {
			return aiMapper.updateAddressInfo(addr);
		}
		return aiMapper.insertAddressInfo(addr);
	}
	
	
	//마이페이지에서 세션을통회 주소 조회
	public AddressInfoVO getAddr(Principal principal) {
		UserInfoVO user = userInfoMapper.selectUserInfoById(principal.getName());
		
		AddressInfoVO addrVO = aiMapper.selectAddressInfo(user.getUiNum());
		if (addrVO == null) {
			addrVO = new AddressInfoVO();
			addrVO.setMessage("주소 정보가 없습니다. 주소를 등록해주세요.");
		}
		
		return addrVO;
	}
	
	//마이페이지 주소 등록
	public int registerMyPage(AddressInfoVO addr, Principal principal) {
		UserInfoVO user = userInfoMapper.selectUserInfoById(principal.getName());
		addr.setUiNum(user.getUiNum());
		
		return aiMapper.insertAddressInfo(addr);
	}
	
	//주소등록 & 수정 
	public int updateAddr(AddressInfoVO addr, Principal principal) {
		UserInfoVO user = userInfoMapper.selectUserInfoById(principal.getName());
		AddressInfoVO addressInfo = aiMapper.selectAddressInfo(user.getUiNum());
		int result = 0;
		// 주소 정보가 없는 경우에 대한 처리
		addr.setUiNum(user.getUiNum());
		if (addressInfo == null) {
			result += aiMapper.insertAddressInfo(addr);
			
		}else {
			result += aiMapper.updateAddressInfo(addr);
		}

		
		
		return result;
	}
}
