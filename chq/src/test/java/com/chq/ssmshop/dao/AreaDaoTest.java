package com.chq.ssmshop.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chq.ssmshop.BaseTest;
import com.chq.ssmshop.entity.Area;

public class AreaDaoTest extends BaseTest {
	@Autowired
	private AreaDao areaDao;
	
	@Test
	public void listArea(){
		List<Area> result = areaDao.listArea();
		assertEquals(result.size(), 3);
	}
}
