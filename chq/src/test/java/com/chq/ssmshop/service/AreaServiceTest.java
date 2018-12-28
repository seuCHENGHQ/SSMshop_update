package com.chq.ssmshop.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chq.ssmshop.BaseTest;
import com.chq.ssmshop.entity.Area;

public class AreaServiceTest extends BaseTest {
	
	@Autowired
	private AreaService areaService;
	
	@Test
	public void queryListTest() {
		List<Area> result = areaService.queryArea();
		assertEquals(result.size(), 3);
	}
}
