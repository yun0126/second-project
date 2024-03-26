package com.secu.team5.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.secu.team5.board.mapper.BoardMapper;
import com.secu.team5.board.vo.BoardVO;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BoardService {

private final BoardMapper boardMapper;
	
	
	public List<BoardVO> selectBoard(BoardVO board){
		return boardMapper.selectBoard(board);
	}
		
	public int selectBoardCount(BoardVO board) {
		return boardMapper.selectBoardsCount(board);
	}
	
	public PageInfo<BoardVO> selectBoardWithHelper(BoardVO board){
		if(board.getPage() != 0 || board.getPageSize() != 0) {
			PageHelper.startPage(board.getPage(), board.getPageSize());
		}else {
			PageHelper.startPage(1, 30);
		}
		PageInfo<BoardVO> pageInfo = new PageInfo<BoardVO>(boardMapper.selectBoardWithHelper(board),30);
		return pageInfo;
	}
	
}
