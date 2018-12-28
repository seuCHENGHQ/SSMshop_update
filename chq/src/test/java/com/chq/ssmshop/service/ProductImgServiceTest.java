package com.chq.ssmshop.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chq.ssmshop.BaseTest;
import com.chq.ssmshop.dto.ProductImageHolder;
import com.chq.ssmshop.dto.ProductImgExecution;
import com.chq.ssmshop.entity.Product;
import com.chq.ssmshop.entity.ProductImg;
import com.chq.ssmshop.entity.Shop;
import com.chq.ssmshop.enums.ProductImgExecutionEnum;
import com.chq.ssmshop.util.PathUtil;

public class ProductImgServiceTest extends BaseTest {
	@Autowired
	private ProductImgService productImgService;

//	@Test
	public void insertProductImgTest() {
		Product product = new Product();
		product.setProductId(4);
		Shop shop = new Shop();
		shop.setShopId(9);
		product.setShop(shop);
		
		File image = new File(PathUtil.getImageBasePath() + "test/xuejie.jpg");
		ProductImageHolder pih = new ProductImageHolder();
		pih.setFileName("xuejie.jpg");
		try {
			pih.setImage(new FileInputStream(image));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pih.setProduct(product);
		
		ProductImg productImg = new ProductImg();
		productImg.setProduct(product);
		productImg.setImgDesc("generate product img test");
		
		ProductImgExecution pie = productImgService.insertProductImg(productImg, pih);
		assertEquals(ProductImgExecutionEnum.SUCCESS.getState(), pie.getState());
	}
	
//	@Test
	public void deleteProductImgTest() {
		ProductImgExecution pie = productImgService.deleteProductImgByProductImgId(6);
		assertEquals(ProductImgExecutionEnum.SUCCESS.getState(), pie.getState());
	}
	
//	@Test
	public void deleteProductImgListTest() {
		ProductImgExecution pie = productImgService.deleteProductImgByProductId(4);
		assertEquals(ProductImgExecutionEnum.SUCCESS.getState(), pie.getState());
	}
}
