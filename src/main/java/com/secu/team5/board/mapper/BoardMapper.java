package com.secu.team5.board.mapper;

import java.util.List;

import com.github.pagehelper.Page;
import com.secu.team5.board.vo.BoardVO;

public interface BoardMapper {
	List<BoardVO> selectBoard(BoardVO board);
	int selectBoardsCount(BoardVO board);
	Page<BoardVO> selectBoardWithHelper(BoardVO board);
}
