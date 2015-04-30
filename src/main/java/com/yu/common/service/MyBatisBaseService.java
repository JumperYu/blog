package com.yu.common.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yu.common.dao.MyBatisBaseDAO;

@Service
public class MyBatisBaseService {
	
	protected static final Logger log = LoggerFactory.getLogger(MyBatisBaseService.class);
	
	private MyBatisBaseDAO myBatisBaseDAO;

	@Resource
	public void setMyBatisBaseDAO(MyBatisBaseDAO myBatisBaseDAO) {
		this.myBatisBaseDAO = myBatisBaseDAO;
	}

	public MyBatisBaseDAO getMyBatisBaseDAO() {
		return myBatisBaseDAO;
	}

}
