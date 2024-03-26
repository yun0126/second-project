package com.secu.team5.product.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.secu.team5.product.mapper.ProductFileInfoMapper;
import com.secu.team5.product.vo.ProductFileInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductFileInfoService {

	private final ProductFileInfoMapper productFileInfoMapper;
	private final AmazonS3Client amazonS3Client;

	@Value("${upload.file-path}")
	private String filePath;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	@Value("${bucket.file-Path}")
	private String Path;
	
	// 상품 리스트 조회
	public List<ProductFileInfoVO> selectProductFileInfo(int piNum) {
		return productFileInfoMapper.selectProductFileInfo(piNum);
	}

	
	public int insertProductFileInfo(ProductFileInfoVO productFiles) {
		return productFileInfoMapper.insertProductFileInfo(productFiles);
	}

	//새로운 상품 추가할때
	public int insertProductFileInfo(int piNum, List<ProductFileInfoVO> productFiles) {
		int result = 0;
		for (ProductFileInfoVO productFile : productFiles) {
			//코드 줄일수있는데 보는사람을 위해서 분석할수있게 해놨어요
			MultipartFile file = productFile.getFile();
			String originName = productFile.getFile().getOriginalFilename();
			String extName = originName.substring(originName.lastIndexOf(".")); // .jpg
			String fileName = UUID.randomUUID() + extName;
			//AWS S3로 파일 보내는 주소
			String ImgPath = "https://" + bucket + "/" + fileName;
			//AWS 실제 파일 경로
			String realPath = Path + fileName;
			//데이터베이스에 파일 추가
			productFile.setPfiName(originName);
			productFile.setPfiImgPath(realPath);
			//보내기위한 데이타 셋팅
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(file.getContentType());
			metadata.setContentLength(file.getSize());			
			try {
				amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);
				//데이터 보내기
				ResponseEntity.ok(ImgPath);
			} catch (Exception e) {
				//전송실패했을경우에는 이창을띄움
				ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
			productFile.setPiNum(piNum);
			result += productFileInfoMapper.insertProductFileInfo(productFile);
		}
		return result;
	}
	
	//상품내용이 변경되었을 경우 
	public int updateProductFileInfo(int piNum, List<ProductFileInfoVO> productFiles) {
		int result = 0;
		
		log.info("piNum=>{}",piNum);
		log.info("productFiles=>{}",productFiles);
		
		
		for (ProductFileInfoVO productFile : productFiles) {
			if(productFile.getStatus().equals("DELETE")) {
				String fileName = productFile.getPfiImgPath().substring(productFile.getPfiImgPath().lastIndexOf("/")+1);
				try {
					amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
				}catch (Exception e) {
					// TODO: handle exception
				}
				result += productFileInfoMapper.deleteProductFileInfo(productFile.getPfiNum());
				continue;
			}
			MultipartFile file = productFile.getFile();
			if (file != null) {

				String originName = productFile.getFile().getOriginalFilename();
				String extName = originName.substring(originName.lastIndexOf(".")); // .jpg
				String fileName = UUID.randomUUID() + extName;
				try {
					//AWS S3로 파일 보내는 주소
					String ImgPath = "https://" + bucket + "/" + fileName;
					//AWS 실제 파일 경로
					String realPath = Path + fileName;
					//데이터베이스에 파일 추가
					productFile.setPfiName(originName);
					productFile.setPfiImgPath(realPath);
					//보내기위한 데이타 셋팅
					ObjectMetadata metadata = new ObjectMetadata();
					metadata.setContentType(file.getContentType());
					metadata.setContentLength(file.getSize());
					amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);
					//데이터 보내기
					ResponseEntity.ok(ImgPath);
				}  catch (Exception e) {
					//전송실패했을경우에는 이창을띄움
					ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
				}
			}
			productFile.setPiNum(piNum);
			
			if(productFile.getStatus().equals("UPDATE")) {
				
				result += productFileInfoMapper.updateProductFileInfo(productFile);
			}else {
				result += productFileInfoMapper.insertProductFileInfo(productFile);
			}
			
		}
		return result;
	}
	
	//상품 파일 삭제
	public int deletePfi(int piNum) {
		List<ProductFileInfoVO> productFiles = productFileInfoMapper.selectProductFileInfo(piNum);
		int result = 0;
		for(ProductFileInfoVO productFile : productFiles) {
			String fileName = productFile.getPfiImgPath().substring(productFile.getPfiImgPath().lastIndexOf("/")+1);
			amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
			result += productFileInfoMapper.deleteProductFileInfo(productFile.getPfiNum());
		}
		result += productFileInfoMapper.deleteProductFileInfo(piNum);
		return result;
	}

}
