package com.chq.ssmshop.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chq.ssmshop.BaseTest;
import com.chq.ssmshop.entity.PersonInfo;

public class PersonInfoDaoTest extends BaseTest {
	
	@Autowired
	PersonInfoDao personInfoDao;
	
	@Test
	public void queryPersonInfoByUserIdTest() {
		PersonInfo user = personInfoDao.queryPersonInfoByUserId(1);
		System.out.println(user.getName());
		assertEquals(1, user.getUserId().intValue());
	}
}
