package com.chq.ssmshop.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chq.ssmshop.BaseTest;
import com.chq.ssmshop.entity.Product;
import com.chq.ssmshop.entity.ProductCategory;
import com.chq.ssmshop.entity.Shop;

public class ProductDaoTest extends BaseTest {
	@Autowired
	private ProductDao productDao;

//	@Test
	public void insertProductTest() {
		Shop shop = new Shop();
		shop.setShopId(9);

		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(2);

		Product product = new Product();
		product.setProductName("insert test");
		product.setProductDesc("insertTest");
		product.setNormalPrice(10);
		product.setPromotionPrice(8);
		product.setPriority(0);
		product.setCreateTime(new Date());
		product.setLastEditTime(new Date());
		product.setEnableStatus(0);
		product.setProductCategory(productCategory);
		product.setShop(shop);

		int effectedRow = productDao.insertProduct(product);
		assertEquals(1, effectedRow);
	}

//	@Test
	public void selectProductByProductIdTest() {
		Product product = productDao.getProductByProductId(1);
		assertEquals(1, product.getProductId().intValue());
	}

//	@Test
	public void selectProductListByShopIdTest() {
		List<Product> productList = productDao.getProductListByShopId(9);
		assertEquals(2, productList.size());
	}

//	@Test
	public void updateProductTest() {
		Shop shop = new Shop();
		shop.setShopId(9);

		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(5);

		Product product = new Product();
		product.setProductId(2);
		product.setProductName("update test");
		product.setEnableStatus(1);
		product.setImgAddr("update test");
		product.setProductCategory(productCategory);
		product.setLastEditTime(new Date());
		int effectedRow = productDao.updateProductByProductId(product);
		assertEquals(1, effectedRow);
	}

//	@Test
	public void deleteProductTest() {
		int effectedRow = productDao.deleteProductByProductId(2);
		assertEquals(1, effectedRow);
	}
	
	@Test
	public void updateProductCategoryToNullTest() {
		int effected = productDao.updateProductCategoryToNull(3);
		assertEquals(2, effected);
	}
}
