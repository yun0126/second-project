package com.secu.team5.qna.mapper;

import java.util.List;

import com.secu.team5.qna.vo.BoardQnAFileInfoVO;

//주석적어야할듯..
public interface BoardQnAFileInfoMapper {
	List<BoardQnAFileInfoVO> selectBoardFileInfos(int bqiNum);

	BoardQnAFileInfoVO selectBoardFileWithBiNum(int bqiNum);

	int insertBoardFileInfo(BoardQnAFileInfoVO board);

	int updateBoardFileInfo(BoardQnAFileInfoVO board);

	int deleteBoardFileInfo(int biNum);
}
