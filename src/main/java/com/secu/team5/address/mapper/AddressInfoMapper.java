package com.secu.team5.address.mapper;

import com.secu.team5.address.vo.AddressInfoVO;


public interface AddressInfoMapper {
	//조회
    AddressInfoVO selectAddressInfo(int uiNum);
    //추가
	int insertAddressInfo(AddressInfoVO addr);
	//수정
	int updateAddressInfo(AddressInfoVO addr);
}