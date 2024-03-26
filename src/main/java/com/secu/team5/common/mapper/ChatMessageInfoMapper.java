package com.secu.team5.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.secu.team5.common.vo.MessageVO;

public interface ChatMessageInfoMapper {
	int insertChatMessageInfo(MessageVO messgage);
	List<MessageVO> selectChatMessageInfos(@Param("cmiSenderUiNum")int cmiSenderUiNum, @Param("cmiReceiveUiNum")int cmiReceiveUiNum);
	int updateChatMessageInfoReceivedTime(MessageVO messgage);
}
