package com.chq.ssmshop.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chq.ssmshop.BaseTest;
import com.chq.ssmshop.dto.ProductCategoryExecution;
import com.chq.ssmshop.entity.ProductCategory;
import com.chq.ssmshop.entity.Shop;
import com.chq.ssmshop.enums.ProductCategoryExecutionEnum;

public class ProductCategoryServiceTest extends BaseTest {
	
	@Autowired
	private ProductCategoryService pcs;

//	@Test
	public void insertTest() {
		ProductCategory pc = new ProductCategory();
		Shop shop = new Shop();
		shop.setShopId(9);
		pc.setShop(shop);
		
		pc.setProductCategoryName("productcategory测试");
		ProductCategoryExecution pce = pcs.insertProductCategory(pc);
		
		assertEquals(ProductCategoryExecutionEnum.NULL_SHOP_ID.getState(), pce.getState());
	}
	
//	@Test
	public void updateTest() {
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(3);
		pc.setProductCategoryName("productcategory更新测试");
		
		ProductCategoryExecution pce = pcs.updateProductCategory(pc);
		System.out.println(pce.getStateInfo());
		assertEquals(pce.getState(),ProductCategoryExecutionEnum.SUCCESS.getState());
	}
	
//	@Test
	public void deleteTest() {
		ProductCategory pc = new ProductCategory();
		Shop shop = new Shop();
		shop.setShopId(9);
		pc.setShop(shop);
		
		pc.setProductCategoryName("productcategory测试");
		ProductCategoryExecution pce = pcs.insertProductCategory(pc);
		
		assertEquals(ProductCategoryExecutionEnum.SUCCESS.getState(), pce.getState());
		pce = pcs.deleteProductCategoryById(pce.getProductCategory().getProductCategoryId());
		assertEquals(pce.getState(),ProductCategoryExecutionEnum.SUCCESS.getState());
	}
	
//	@Test
	public void batchInsertTest() {
		List<ProductCategory> pcList = new ArrayList<>();
		ProductCategory pc = null;
		for(int i=0;i<2;i++) {
			pc = new ProductCategory();
			Shop shop = new Shop();
			shop.setShopId(9);
			pc.setShop(shop);
			
			pc.setProductCategoryName("batchInsert测试"+i);
			pcList.add(pc);
		}
		
//		pcList.get(pcList.size()-1).setProductCategoryName(null);
		ProductCategoryExecution pce = pcs.batchInsertProductCategory(pcList);
		assertEquals(ProductCategoryExecutionEnum.SUCCESS.getState(), pce.getState());
		
		for(int i=0;i<pcList.size();i++) {
			pce = pcs.deleteProductCategoryById(pcList.get(i).getProductCategoryId());
			assertEquals(ProductCategoryExecutionEnum.SUCCESS.getState(), pce.getState());
		}
	}
}
