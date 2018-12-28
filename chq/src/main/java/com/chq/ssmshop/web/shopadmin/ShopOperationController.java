package com.chq.ssmshop.web.shopadmin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.chq.ssmshop.dto.ImageHolder;
import com.chq.ssmshop.dto.ShopExecution;
import com.chq.ssmshop.entity.Area;
import com.chq.ssmshop.entity.PersonInfo;
import com.chq.ssmshop.entity.Shop;
import com.chq.ssmshop.entity.ShopCategory;
import com.chq.ssmshop.enums.ShopExecutionEnum;
import com.chq.ssmshop.service.AreaService;
import com.chq.ssmshop.service.ShopCategoryService;
import com.chq.ssmshop.service.ShopService;
import com.chq.ssmshop.util.CodeUtil;
import com.chq.ssmshop.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/shopadmin")
public class ShopOperationController {

	@Autowired
	private ShopService shopService;

	@Autowired
	private ShopCategoryService shopCategoryService;

	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "/getinitinfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getInitInfo() {
		Map<String, Object> modelMap = new HashMap<>();
		List<ShopCategory> shopCategoryList = shopCategoryService.queryAllShopCategory();
		List<Area> areaList = areaService.queryArea();
		modelMap.put("shopCategoryList", shopCategoryList);
		modelMap.put("areaList", areaList);
		return modelMap;
	}

	@RequestMapping("/getshopbyid/{shopId}")
	@ResponseBody
	public Map<String, Object> getShopInfoById(@PathVariable("shopId") Integer shopId) {
		Map<String, Object> modelMap = new HashMap<>();
		ShopExecution se = shopService.queryShop(shopId);
		if(se.getState()==ShopExecutionEnum.SUCCESS.getState()) {
			modelMap.put("success", true);
			modelMap.put("shop",se.getShop());
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", se.getStateInfo());
		}
		
		return modelMap;
	}

	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelAndView = new HashMap<>();

		// 先检测输入的验证码是否正确
		if (!CodeUtil.checkVerifyCode(request)) {
			modelAndView.put("success", false);
			modelAndView.put("errMsg", "验证码输入错误");
			return modelAndView;
		}

		// 第一步对JSON进行反序列化，得到Shop对象
		ObjectMapper mapper = new ObjectMapper();
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		Shop shop = null;
		try {
			// 如果shopStr==null 这里就会抛出异常，不用担心
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			modelAndView.put("success", false);
			modelAndView.put("errMsg", e.getMessage());
			return modelAndView;
		}

		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		} else {
			modelAndView.put("success", false);
			modelAndView.put("errMsg", "上传店铺图片不能为空！");
			return modelAndView;
		}

		// 第二步注册店铺
		if (shop != null && shopImg != null) {
			ImageHolder imageHolder = new ImageHolder();
			imageHolder.setFileName(shopImg.getOriginalFilename());
			try {
				imageHolder.setImage(shopImg.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * 因为账号系统还未开发完成，因此这里先预先填写一个店铺的主人，等到账户功能开发好了，这里再删除
			 */
			PersonInfo owner = new PersonInfo();
			owner.setUserId(1);
			shop.setOwner(owner);
			/*
			 * 
			 */
			ShopExecution se = shopService.registerShop(shop, imageHolder);
			if (se.getState() == ShopExecutionEnum.SUCCESS.getState()) {
				modelAndView.put("success", true);
			} else {
				modelAndView.put("success", false);
				modelAndView.put("errMsg", se.getStateInfo());
				return modelAndView;
			}
		} else {
			modelAndView.put("success", false);
			modelAndView.put("errMsg", "店铺或者店铺图片为空！");
			return modelAndView;
		}
		// 只有success==true的情况能走到这里
		// 为每个success==false的情况都添加return语句是为了防止各种错误情况互相覆盖errMsg
		return modelAndView;
	}

	@RequestMapping(value = "/updateshop", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		// 先检测输入的验证码是否正确
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码输入错误");
			return modelMap;
		}

		ObjectMapper mapper = new ObjectMapper();
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}

		// 获取上传的店铺图片
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		}
		
		if(shopImg==null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空！");
			return modelMap;
		}

		// 封装图片，准备向数据库提交
		ImageHolder imageHolder = new ImageHolder();
		imageHolder.setFileName(shopImg.getOriginalFilename());
		try {
			imageHolder.setImage(shopImg.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}

		if (shop != null && shopImg != null) {

			ShopExecution se = shopService.updateShop(shop, imageHolder);
			if (se.getState() == ShopExecutionEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", se.getStateInfo());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "shop或者shopImg为空");
			return modelMap;
		}

		// 将来要实现的功能：如果上传的的店铺图片为null的话，就只更新信息，不更换店铺图片

		return modelMap;
	}

	@RequestMapping(value="/getshoplistbyowner/{ownerId}" ,method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getShopListByOwnerId(@PathVariable("ownerId") Integer ownerId){
		Map<String, Object> modelMap = new HashMap<>();
		ShopExecution se = shopService.queryShopByOwnerId(ownerId);
		modelMap.put("success", true);
		modelMap.put("shopList", se.getShopList());
		
		return modelMap;
	}
}
