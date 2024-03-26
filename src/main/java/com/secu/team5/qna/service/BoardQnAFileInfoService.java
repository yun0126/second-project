package com.secu.team5.qna.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.secu.team5.qna.mapper.BoardQnAFileInfoMapper;
import com.secu.team5.qna.vo.BoardQnAFileInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardQnAFileInfoService {

	private final BoardQnAFileInfoMapper bfiMapper;

	public List<BoardQnAFileInfoVO> selectBoardsFileInfos(int bfiNum) {
		return bfiMapper.selectBoardFileInfos(bfiNum);
	}
	public BoardQnAFileInfoVO selectBoardsFileInfosWithBiNum(int bqiNum) {
		return bfiMapper.selectBoardFileWithBiNum(bqiNum);
	}
	public int insertBoardFileInfo(BoardQnAFileInfoVO boardFiles) {
		return bfiMapper.insertBoardFileInfo(boardFiles);
	}

	// 간단하게 텍스트로 저장함
	public int insertBoardsFileInfos(int bqiNum, BoardQnAFileInfoVO boardFile) {
		int result = 0;
		boardFile.setBqiNum(bqiNum);
		log.info("boardFile=>{}", boardFile);
		result += bfiMapper.insertBoardFileInfo(boardFile);
		return result;
	}

	// 게시판 수정
	public int updateBoardsFileInfos(int bqiNum, List<BoardQnAFileInfoVO> boardFiles) {
		int result = 0;
		BoardQnAFileInfoVO boardFile = boardFiles.get(0);
		boardFile.setBqiNum(bqiNum);
		result += bfiMapper.updateBoardFileInfo(boardFile);
		return result;

	}

	// 게시판 삭제
	public int deleteBoardFileInfo(int bfiNum) {
		return bfiMapper.deleteBoardFileInfo(bfiNum);
	}
}
