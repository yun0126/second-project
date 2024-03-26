package com.secu.team5.address.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressInfoVO {
    private int aiNum;
    private int uiNum;
    private String aiAddress;
    private String aiType;
    private String aiApartment;
    private String aiJibunAddress;
    private String aiJibunAddressEnglish;
    private String aiRoadAddress;
    private String aiRoadAddressEnglish;
    private String aiZoneCode;
    private String aiBuildingName;
    private String aiExtraAddress;
    private String aiDetailAddress;
    //특정메세지를 출력하기위한 부분
    private String Message;
}