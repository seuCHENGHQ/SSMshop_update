package com.chq.ssmshop.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chq.ssmshop.BaseTest;
import com.chq.ssmshop.dto.PersonInfoExecution;

public class PersonInfoServiceTest extends BaseTest {
	
	@Autowired
	private PersonInfoService personInfoService;
	
	@Test
	public void getPersonInfoTest() {
//		PersonInfoExecution pie = personInfoService.getPersonInfo(1);
//		assertEquals(1, pie.getCount());
		PersonInfoExecution pie = personInfoService.getPersonInfo(2);
		assertEquals(0, pie.getCount());
	}
}
