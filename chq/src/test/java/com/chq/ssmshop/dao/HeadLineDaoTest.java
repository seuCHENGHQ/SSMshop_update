package com.chq.ssmshop.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chq.ssmshop.BaseTest;
import com.chq.ssmshop.entity.HeadLine;

public class HeadLineDaoTest extends BaseTest {
	
	@Autowired
	private HeadLineDao headLineDao;
	
	@Test
	public void queryHeadLineTest() {
		List<HeadLine> headLineList = headLineDao.queryHeadLine(new HeadLine());
		assertEquals(0, headLineList.size());
	}
}
