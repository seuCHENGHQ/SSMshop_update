package com.chq.ssmshop.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chq.ssmshop.BaseTest;
import com.chq.ssmshop.entity.HeadLine;

public class HeadLineServiceTest extends BaseTest {
	
	@Autowired
	private HeadLineService headLineService;
	
	@Test
	public void getHeadLineListTest() {
//		HeadLine headLineCondition = new HeadLine();
//		headLineCondition.setEnableStatus(0);
//		List<HeadLine> headLineList = headLineService.getHeadLineList(headLineCondition);
//		assertEquals(0, headLineList.size());
		
//		HeadLine headLineCondition = new HeadLine();
//		headLineCondition.setEnableStatus(1);
//		List<HeadLine> headLineList = headLineService.getHeadLineList(headLineCondition);
//		assertEquals(4, headLineList.size());
		
		HeadLine headLineCondition = new HeadLine();
//		headLineCondition.setEnableStatus(1);
		List<HeadLine> headLineList = headLineService.getHeadLineList(headLineCondition);
		assertEquals(4, headLineList.size());
	}
}
