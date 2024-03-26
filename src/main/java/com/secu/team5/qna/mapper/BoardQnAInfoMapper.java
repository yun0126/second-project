package com.secu.team5.qna.mapper;

import java.util.List;

import com.github.pagehelper.Page;
import com.secu.team5.qna.vo.BoardQnAInfoVO;

//주석적어야할듯..
public interface BoardQnAInfoMapper {
	
	//마이페이지에서 문의사항 리스트 부분
	List<BoardQnAInfoVO> selectBoardInfos(BoardQnAInfoVO board);
	
	//리스트 페이징 관련
	Page<BoardQnAInfoVO> selectBoardInfosWithHelper(BoardQnAInfoVO board);
	
	// 상세 정보 조회 부분
	BoardQnAInfoVO selectBoardInfo(int bqiNum);
	
	// 게시글 등록 부분
	int insertBoardInfo(BoardQnAInfoVO board); 
	
	// 게시글 업데이트 부분
	int updateBoardInfo(BoardQnAInfoVO board);
	
	// 게시글 삭제 부분
	int deleteBoardInfo(int bqiNum);
}
