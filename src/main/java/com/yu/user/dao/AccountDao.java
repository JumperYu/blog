package com.yu.user.dao;

import org.springframework.stereotype.Component;

import com.yu.common.dao.MyBatisDao;
import com.yu.user.mapper.AccountMapper;

@Component
public class AccountDao extends MyBatisDao {

	public void test() {
		log.info(getSqlSession().getMapper(AccountMapper.class)
				.queryAccountByPassport("haha").toString());
	}

	public void test_2() {
		log.info(getSqlSession().getMapper(AccountMapper.class).selectAll()
				.toString());
	}
}
