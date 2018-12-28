package com.chq.ssmshop.service;


import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chq.ssmshop.BaseTest;
import com.chq.ssmshop.dto.ImageHolder;
import com.chq.ssmshop.dto.ShopExecution;
import com.chq.ssmshop.entity.Area;
import com.chq.ssmshop.entity.PersonInfo;
import com.chq.ssmshop.entity.Shop;
import com.chq.ssmshop.entity.ShopCategory;
import com.chq.ssmshop.util.PathUtil;

public class ShopServiceTest extends BaseTest {
	@Autowired
	private ShopService shopService;
	
	@Test
	public void queryShopTest() {
		ShopExecution se = shopService.queryShop(9);
		assertEquals(se.getCount(), 1);
	}
	
//	@Test
	public void queryAllShopTest() {
		ShopExecution se = shopService.queryAllShop();
		assertEquals(se.getShopList().size(), 2);
	}
	
//	@Test
	public void registerShopTest() {
		Shop shop = new Shop();
		ImageHolder imageHolder = new ImageHolder();
		
		String imagePath = PathUtil.getImageBasePath()+"test/xuejie.jpg";
		File image = new File(imagePath);
		imageHolder.setFileName("xuejie.jpg");
		try {
			imageHolder.setImage(new FileInputStream(image));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		shop.setShopName("testshop");
		Area area = new Area();
		area.setAreaId(1);
		shop.setArea(area);
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1);
		shop.setOwner(owner);
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(1);
		shop.setShopCategory(shopCategory);
		shop.setShopDesc("shopDescTest");
		ShopExecution se = shopService.registerShop(shop,imageHolder);
		assertEquals(se.getCount(), 1);
	}
	
//	@Test
	public void updateShopTest() {
		ImageHolder imageHolder = new ImageHolder();
//		String imagePath = PathUtil.getImageBasePath()+"test/chiji.png";
		String imagePath = PathUtil.getImageBasePath()+"test/xuejie.jpg";
		File image = new File(imagePath);
//		imageHolder.setFileName("chiji.png");
		imageHolder.setFileName("xuejie.jpg");
		try {
			imageHolder.setImage(new FileInputStream(image));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Shop shop = new Shop();
		shop.setShopName("testshop");
		shop.setShopId(9);
		Area area = new Area();
		area.setAreaId(1);
		shop.setArea(area);
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1);
		shop.setOwner(owner);
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(1);
		shop.setShopCategory(shopCategory);
		shop.setShopDesc("shopDescTestUpdate");
		ShopExecution se = shopService.updateShop(shop, imageHolder);
		assertEquals(se.getCount(), 1);
	}

	@Test
	public void queryShopByOwnerIdTest() {
		ShopExecution se = shopService.queryShopByOwnerId(1);
		assertEquals(8, se.getShopList().size());
	}
}
