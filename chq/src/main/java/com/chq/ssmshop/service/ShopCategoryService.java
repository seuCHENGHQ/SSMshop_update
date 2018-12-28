package com.chq.ssmshop.service;

import java.util.List;

import com.chq.ssmshop.entity.ShopCategory;

public interface ShopCategoryService {
	List<ShopCategory> queryAllShopCategory();
	
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
