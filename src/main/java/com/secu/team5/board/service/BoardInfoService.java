package com.secu.team5.board.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.secu.team5.board.mapper.BoardInfoMapper;
import com.secu.team5.board.vo.BoardFileInfoVO;
import com.secu.team5.board.vo.BoardInfoVO;
import com.secu.team5.common.vo.ResponsePageVO;
import com.secu.team5.user.mapper.UserInfoMapper;
import com.secu.team5.user.vo.UserInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardInfoService {
	//해당프로젝트 보드 게시판의 로직은 대부분 여기서 이루어진다 다른부분들은 연습으로 만들엇다고 생각해도될정도
	
	private final BoardInfoMapper biMapper;
	private final BoardFileInfoService bfiService;
	private final UserInfoMapper userInfoMapper;
	private final AmazonS3Client amazonS3Client;

	@Value("${upload.file-path}")
	private String filePath;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	@Value("${bucket.file-Path}")
	private String Path;
	

	// 게시물 목록 출력
	public ResponsePageVO<BoardInfoVO> selectBoardInfos(BoardInfoVO board) {
		log.info("board=>{}", board);
		board.setEnd(board.getPageSize());
		int start = (board.getPage() - 1) * board.getPageSize();
		board.setStart(start);

		ResponsePageVO<BoardInfoVO> resVO = new ResponsePageVO<>();
		resVO.setList(biMapper.selectBoardInfos(board));
		resVO.setTotalCnt(biMapper.selectBoardInfoCnt(board));
		return resVO;
	}

	// 페이징 헬퍼를 사용한 게시판 페이징
	public PageInfo<BoardInfoVO> selectBoardInfosWithHelper(BoardInfoVO board) {
		PageHelper.startPage(board.getPage(), board.getPageSize());
		PageInfo<BoardInfoVO> pageInfo = new PageInfo<>(biMapper.selectBoardInfosWithHelper(board), 10);
		return pageInfo;
	}

	// 게시물 출력 Content가 빈문자열인경우 파일의 텍스트를 가져옴
	public BoardInfoVO getBoardInfo(int biNum) {
		BoardInfoVO board = new BoardInfoVO();
		board = biMapper.selectBoardInfo(biNum);
		board.setBoardFiles(bfiService.selectBoardsFileInfos(biNum));
		log.info("board=>{}",board);
		if(board.getBiContent().equals("")) {
			
			try {
				String filePath = board.getBoardFiles().get(0).getBfiFilePath();
				//크롤링을해서 내용을 가져옴
				Document doc = Jsoup.connect(filePath).get();
				
				//파일의 문자열을 읽어들여서 contents에 담아냄 데이터베이스의 한계를 벗어나게했음
				String contents = doc.select("body").html();
				board.setBiContent(contents);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return board;
	}

	// 게시물 등록
	public int insertBoardInfo(BoardInfoVO board, Principal principal) {
		UserInfoVO user = new UserInfoVO();
		user = userInfoMapper.selectUserInfoById(principal.getName());
		int result = 0;
		log.info("user=>{}", user);
		// board의 Contents 가져와서 길이 확인 데이터베이스의 내용이 4천자까지 지원하므로 저장이안될경우에는
		// 보드컨텐츠를 비워놓고 파일로 이용하는 형식으로 사용할 생각임
		String contents = board.getBiContent();
		log.info("contents=>{}", contents);
		if (contents.length() > 4000) {
			board.setBiContent("");
			// session에서 가져온 uiNum을 보드에 대입함
			board.setUiNum(user.getUiNum());
			// 내용저장
			result = biMapper.insertBoardInfo(board);
			log.info("board=>{}", board);
			
			// 텍스트 파일로 저장하면서 보드 파일 인포에 저장
			try {
				//boardFile 객체 생성
				BoardFileInfoVO boardFile = new BoardFileInfoVO();
				String fileName = "Contents-" + UUID.randomUUID() + ".txt";
				String filePath = "https://" + bucket + "/" + fileName;
				// 내용을 텍스트 파일로씀 aws 로 보내기위해서는 InputStream 객체로 변환해줘야함
				InputStream is = new ByteArrayInputStream(contents.getBytes("UTF-8"));
				// 데이터를 보내기위한 헤더세팅
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType("text/plain");
				//파일보내기
				amazonS3Client.putObject(bucket, fileName, is, metadata);
				// 파일이 잘들어갔는지 확인
				ResponseEntity.ok(filePath);
				
				
				boardFile.setBfiName(fileName);
				boardFile.setBfiFilePath(Path + fileName);
				//sort는 이제 필요가 없으므로 그냥 1로 지정 어노테이션때문에 Final로 지정해줄수가 없으므로 그냥 하드코딩하겠음 데이터베이스를 수정하여도되나 만들어놓은걸 굳이.. 변경하면팀원들이 머리아파하기때문에 !
				boardFile.setBfiSort(1);
				result += bfiService.insertBoardsFileInfos(board.getBiNum(), boardFile);

			} catch (Exception e) {
				e.printStackTrace();
			}	
		}else {
			// session에서 가져온 uiNum을 보드에 대입함
			board.setUiNum(user.getUiNum());
			// 내용저장
			result = biMapper.insertBoardInfo(board);
			log.info("board=>{}", board);
		}

	
		return result;
	}

	@Transactional
	// 게시물 수정
	public int updateBoardInfo(BoardInfoVO board, Principal principal) {
		UserInfoVO user = new UserInfoVO();
		user = userInfoMapper.selectUserInfoById(principal.getName());
		//update의 핵심 작성한 사용자와 로그인한 사용가가 같아야 수정이 가능해야한다 
		if(board.getUiNum() != user.getUiNum()) {
			return 0;
		}
		//contents의 값을 미리 뽑아놓고 그속은비워놓는다 데이터베이스의 한계가 4천자이기때문에
		String contents = board.getBiContent();
		log.info("contents=>{}", contents);
		if(board.getBiContent().length() > 4000) {
			board.setBiContent("");
		}
		//파일텍스트파일로 저장하는 단계
		try {
			BoardFileInfoVO boardFile = board.getBoardFiles().get(0);
			log.info("boardFile=>{}", boardFile);
			if(boardFile != null) {
				String fileName = boardFile.getBfiName();
				// 내용을 텍스트 파일로씀 aws 로 보내기위해서는 InputStream 객체로 변환해줘야함
				InputStream is = new ByteArrayInputStream(contents.getBytes("utf-8"));
				// 데이터를 보내기위한 헤더세팅
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType("text/plain");
				//파일보내기
				amazonS3Client.putObject(bucket, fileName, is, metadata);
				// 파일이 잘들어갔는지 확인
				ResponseEntity.ok(filePath);
			}
		} catch (Exception e) {
			
		}
		int result = biMapper.updateBoardInfo(board);
		if(board.getBiContent() == "") {
			result += bfiService.updateBoardsFileInfos(board.getBiNum(), board.getBoardFiles());
		}
		
		log.info("board=>{}", board);
		return result;
	}

	// 게시물 조회수 증가
	public int updateBoardView(BoardInfoVO board) {
		return biMapper.updateViewsCnt(board);
	}

	// 게시물 삭제
	public int deleteBoardInfo(int biNum) {
		int result = 0;
		//원격저장소에있는 파일도 삭제한다
		BoardFileInfoVO boardFile = bfiService.selectBoardsFileInfosWithBiNum(biNum);
		if(boardFile != null) {
			amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, boardFile.getBfiName()));
			result = bfiService.deleteBoardFileInfo(biNum);
		}

		result += biMapper.deleteBoardInfo(biNum); 
		log.info("biNum=>{}", biNum);
		return result;
	}

}
