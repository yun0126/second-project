package com.secu.team5.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.secu.team5.product.mapper.ProductMapper;
import com.secu.team5.product.vo.ProductVO;

import lombok.AllArgsConstructor;

//일단은 메소드 한개만 넣엇음 필요하면 추가 예정
@Service
@AllArgsConstructor
public class ProductService {
	
	private final ProductMapper productMapper;
	
	//일반적인 리스트
	public List<ProductVO> selectProducts(ProductVO product){
		return productMapper.selectProducts(product);
	}
	
	//상품수량체크
	public int selectProductsCount(ProductVO product) {
		return productMapper.selectProductsCount(product);
	}
	
	//페이지 헬퍼를통한 select
	public PageInfo<ProductVO> selectProductsWithHelper(ProductVO product){
		if(product.getPage() != 0 || product.getPageSize() != 0) {
			PageHelper.startPage(product.getPage(), product.getPageSize());
		}else {
			PageHelper.startPage(1, 30);
		}
		PageInfo<ProductVO> pageInfo = new PageInfo<ProductVO>(productMapper.selectProductsWithHelper(product),30);
		return pageInfo;
	}
}
