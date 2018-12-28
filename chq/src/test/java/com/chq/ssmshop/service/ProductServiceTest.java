package com.chq.ssmshop.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chq.ssmshop.BaseTest;
import com.chq.ssmshop.dto.ImageHolder;
import com.chq.ssmshop.dto.ProductExecution;
import com.chq.ssmshop.entity.Product;
import com.chq.ssmshop.entity.ProductCategory;
import com.chq.ssmshop.entity.Shop;
import com.chq.ssmshop.enums.ProductExecutionEnum;
import com.chq.ssmshop.util.PathUtil;

public class ProductServiceTest extends BaseTest {

	@Autowired
	private ProductService productService;

//	@Test
	public void selectProductListTest() {
		ProductExecution pe = productService.selectProductListByShopId(9);
		assertEquals(2, pe.getCount());
	}

//	@Test
	public void selectProductTest() {
		ProductExecution pe = productService.selectProductById(3);
		assertEquals(3, pe.getProduct().getProductId().intValue());
	}

//	@Test
	public void insertProductTest() {
		File image = new File(PathUtil.getImageBasePath() + "test/xuejie.jpg");
		ImageHolder imageHolder = new ImageHolder();
		imageHolder.setFileName("xuejie.jpg");
		try {
			imageHolder.setImage(new FileInputStream(image));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Shop shop = new Shop();
		shop.setShopId(9);

		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(2);

		Product product = new Product();
		product.setProductName("insert test");
		product.setProductDesc("insertTest");
		product.setNormalPrice(10);
		product.setPromotionPrice(8);
		product.setProductCategory(productCategory);
		product.setShop(shop);

		ProductExecution pe = productService.insertProduct(product, imageHolder);
		assertEquals(ProductExecutionEnum.SUCCESS.getState(), pe.getState());
	}

//	@Test
	public void updateProductTest() {
		File image = new File(PathUtil.getImageBasePath() + "test/chiji.png");
		ImageHolder imageHolder = new ImageHolder();
		imageHolder.setFileName("chiji.png");
		try {
			imageHolder.setImage(new FileInputStream(image));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Product product = new Product();
		product.setProductId(4);
		
		Shop shop = new Shop();
		shop.setShopId(9);
		product.setShop(shop);
		
		ProductExecution pe = productService.updateProduct(product, imageHolder);
		assertEquals(ProductExecutionEnum.SUCCESS.getState(), pe.getState());
	}
}
