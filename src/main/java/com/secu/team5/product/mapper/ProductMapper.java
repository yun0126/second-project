package com.secu.team5.product.mapper;

import java.util.List;

import com.github.pagehelper.Page;
import com.secu.team5.product.vo.ProductVO;

public interface ProductMapper {
	//상품들 조회
	List<ProductVO> selectProducts(ProductVO product);
	//상품 개수 조회
	int selectProductsCount(ProductVO product);
	//검색용 페이징 헬퍼를 통해 리스트 조회
	Page<ProductVO> selectProductsWithHelper(ProductVO product);
}
