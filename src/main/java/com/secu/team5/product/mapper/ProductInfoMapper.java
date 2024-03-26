package com.secu.team5.product.mapper;

import java.util.List;

import com.secu.team5.product.vo.ProductFileInfoVO;
import com.secu.team5.product.vo.ProductInfoVO;



public interface ProductInfoMapper {
	//상품정보 가져오는것
	List<ProductInfoVO> selectProductInfos(ProductInfoVO Product);
	//상품정보 한개 조회
	ProductInfoVO selectProductInfo(int piNum);
	
	//상품정보 수정
	int updateProductInfo(ProductInfoVO Product);

	int insertProductInfo(ProductInfoVO Product);
	//파일이랑 같이 데이터를 넣는것
	int insertProductInfo(int piNum, List<ProductFileInfoVO> productFiles);
	
	//삭제
	int deleteProductInfo(int piNum);
}
