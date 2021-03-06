package com.chq.ssmshop.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chq.ssmshop.BaseTest;
import com.chq.ssmshop.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest {
	
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
//	@Test
	public void queryAllShopCategoryTest() {
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryAllShopCategory();
		assertEquals(3, shopCategoryList.size());
	}
	
//	@Test
	public void queryShopCategoryTest() {
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(null);
		assertEquals(3, shopCategoryList.size());
	}
	
	@Test
	public void insertShopCategoryTest() {
		String mainPagePath = "/home/chq/java_workspace/ImageResources/mainpage";
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryName("二手市场");
		sc.setShopCategoryDesc("二手市场");
		sc.setCreateTime(new Date());
		sc.setLastEditTime(new Date());
		sc.setPriority(99);
		sc.setShopCategoryImg(mainPagePath+"2017061223272255687.png");
		System.out.println(shopCategoryDao.insertShopCategory(sc));
	}
}
