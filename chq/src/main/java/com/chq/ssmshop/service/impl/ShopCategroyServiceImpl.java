package com.chq.ssmshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chq.ssmshop.dao.ShopCategoryDao;
import com.chq.ssmshop.entity.ShopCategory;
import com.chq.ssmshop.service.ShopCategoryService;

@Service
public class ShopCategroyServiceImpl implements ShopCategoryService {

	@Autowired
	private ShopCategoryDao shopCategoryDao;

	@Override
	public List<ShopCategory> queryAllShopCategory() {
		// TODO Auto-generated method stub
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryAllShopCategory();
		return shopCategoryList;
	}

	@Override
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
		// TODO Auto-generated method stub
		return shopCategoryDao.queryShopCategory(shopCategoryCondition);
	}

}
