package com.secu.team5.product.mapper;

import java.util.List;

import com.secu.team5.product.vo.ProductFileInfoVO;

public interface ProductFileInfoMapper {
	//파일 조회 기준점이 piNum
	List<ProductFileInfoVO> selectProductFileInfo(int piNum);
	//파일 넣기
	int insertProductFileInfo(ProductFileInfoVO productFile);
	//파일 수정
	int updateProductFileInfo(ProductFileInfoVO product);
	//파일 삭제
	int deleteProductFileInfo(int pfiNum);
}
