package com.secu.team5.board.mapper;

import java.util.List;

import com.github.pagehelper.Page;
import com.secu.team5.board.vo.BoardFileInfoVO;
import com.secu.team5.board.vo.BoardInfoVO;

public interface BoardInfoMapper {
	
	List<BoardInfoVO> selectBoardInfos(BoardInfoVO board);
	
	int selectBoardInfoCnt(BoardInfoVO board);
	
	Page<BoardInfoVO> selectBoardInfosWithHelper(BoardInfoVO board);
	
	BoardInfoVO selectBoardInfo(int biNum);
	
	int insertBoardInfo(BoardInfoVO board); 
	
	int insertBoardInfo(int biNum, List<BoardFileInfoVO> boardFiles); 
	
	int updateBoardInfo(BoardInfoVO board);
	
	int updateViewsCnt(BoardInfoVO board);
	
	int deleteBoardInfo(int biNum);
}
