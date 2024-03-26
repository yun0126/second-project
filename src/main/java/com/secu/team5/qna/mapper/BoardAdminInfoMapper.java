package com.secu.team5.qna.mapper;

import com.github.pagehelper.Page;
import com.secu.team5.qna.vo.BoardAdminInfoVO;

//주석적어야할듯..
public interface BoardAdminInfoMapper {

	// 게시판 페이징 처리 부분
	Page<BoardAdminInfoVO> selectBoardInfosWithHelper(BoardAdminInfoVO board);

	// 게시판 상세 조회 부분
	BoardAdminInfoVO selectBoardInfo(int bqiNum);

	// 게시물에 달린 답글 갯수 카운트
	int replyCountByBqiNum(int bqiNum);

	// 답글 등록
	int insertBoardInfo(BoardAdminInfoVO board);

	// 답글 수정
	int updateBoardInfo(BoardAdminInfoVO board);

	// 답글 삭제
	int deleteBoardInfo(int rpiNum);

	// 답글없는 게시글만 조회
	Page<BoardAdminInfoVO> selectBoardInfosWithHelperList(BoardAdminInfoVO board);
}
