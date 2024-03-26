package com.secu.team5.qna.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.secu.team5.qna.mapper.BoardQnAInfoMapper;
import com.secu.team5.qna.vo.BoardQnAInfoVO;
import com.secu.team5.user.mapper.UserInfoMapper;
import com.secu.team5.user.vo.UserInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardQnAInfoService {

	private final BoardQnAInfoMapper bqiMapper;
	private final UserInfoMapper userInfoMapper;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Value("${bucket.file-Path}")
	private String Path;

	// 페이징 헬퍼를 사용한 게시판 페이징
	public PageInfo<BoardQnAInfoVO> selectBoardInfosWithHelper(BoardQnAInfoVO board, Principal principal) {
		// 현재 로그인한 사용자의 정보를 가져옴
		UserInfoVO user = userInfoMapper.selectUserInfoById(principal.getName());
		// 현재 로그인한 사용자의 uiNum을 세팅
		board.setUiNum(user.getUiNum());
		log.info("board=>{}", board);
		PageHelper.startPage(board.getPage(), board.getPageSize());

		PageInfo<BoardQnAInfoVO> pageInfo = new PageInfo<>(bqiMapper.selectBoardInfosWithHelper(board), 10);
		return pageInfo;
	}

	// 게시물 출력
	public BoardQnAInfoVO getBoardInfo(int bqiNum) {
		BoardQnAInfoVO board = bqiMapper.selectBoardInfo(bqiNum);
		log.info("board=>{}", board);

		return board;
	}

	// 마이페이지 문의사항 리스트
	public List<BoardQnAInfoVO> selectBoardInfos(BoardQnAInfoVO board, Principal principal) {
		UserInfoVO user = userInfoMapper.selectUserInfoById(principal.getName());
		board.setUiNum(user.getUiNum());

		return bqiMapper.selectBoardInfos(board);
	}

	// 게시물 등록
	public int insertBoardInfo(BoardQnAInfoVO board, Principal principal) {
		UserInfoVO user = userInfoMapper.selectUserInfoById(principal.getName());
		log.info("user=>{}", user);
		// session에서 가져온 uiNum을 보드에 대입함
		board.setUiNum(user.getUiNum());
		log.info("board=>{}", board);

		return bqiMapper.insertBoardInfo(board);
	}

	@Transactional
	// 게시물 수정
	public int updateBoardInfo(BoardQnAInfoVO board, Principal principal) {
		UserInfoVO user = userInfoMapper.selectUserInfoById(principal.getName());
		// update의 핵심 작성한 사용자와 로그인한 사용가가 같아야 수정이 가능해야한다
		if (board.getUiNum() != user.getUiNum()) {
			return 0;
		}
		log.info("board=>{}", board);
		return bqiMapper.updateBoardInfo(board);
	}

	// 게시물 삭제
	public int deleteBoardInfo(int bqiNum) {
		int result = bqiMapper.deleteBoardInfo(bqiNum);

		log.info("bqiNum=>{}", bqiNum);
		return result;
	}

}
