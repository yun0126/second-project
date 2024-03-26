package com.secu.team5.common.vo;

import java.util.List;

import com.secu.team5.user.vo.UserInfoVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EnterVO {

	private List<UserInfoVO> users;
}
