package com.chq.ssmshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chq.ssmshop.dao.AreaDao;
import com.chq.ssmshop.entity.Area;
import com.chq.ssmshop.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {
	
	@Autowired
	private AreaDao areaDao;

	@Override
	public List<Area> queryArea() {
		// TODO Auto-generated method stub
		return areaDao.listArea();
	}

}
