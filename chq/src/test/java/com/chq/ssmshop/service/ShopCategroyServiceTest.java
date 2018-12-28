package com.chq.ssmshop.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chq.ssmshop.BaseTest;
import com.chq.ssmshop.entity.ShopCategory;

public class ShopCategroyServiceTest extends BaseTest {
	
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	@Test
	public void queryAllShopCategoryTest() {
		List<ShopCategory> shopCategoryList = shopCategoryService.queryAllShopCategory();
		assertEquals(3, shopCategoryList.size());
	}
}
