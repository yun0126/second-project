package com.secu.team5.board.mapper;

import java.util.List;

import com.secu.team5.board.vo.BoardFileInfoVO;

public interface BoardFileInfoMapper {
	List<BoardFileInfoVO> selectBoardFileInfos (int biNum);
	BoardFileInfoVO selectBoardFileWithBiNum(int biNum);
	int insertBoardFileInfo(BoardFileInfoVO board);
	int updateBoardFileInfo(BoardFileInfoVO board);
	int deleteBoardFileInfo(int biNum);
}
