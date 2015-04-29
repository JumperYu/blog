package com.yu.user.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.yu.common.dao.MyBatisDao;
import com.yu.user.mapper.AccountMapper;

@Repository
public class AccountDao extends MyBatisDao {

	private AccountMapper accountMapper;

	@PostConstruct
	public void init() {
		accountMapper = getSqlSession().getMapper(AccountMapper.class);
	}

	public void test() {
		log.info(accountMapper.queryAccountByPassport("haha").toString());
	}

	public void test_2() {
		log.info(accountMapper.selectAll().toString());
	}

	public void test_3() {
		log.info(accountMapper.selectOne().toString());
	}

	public void test_4() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("index", 0);
		params.put("rows", 10);
		log.info(accountMapper.queryAll(params).toString());
	}

	public void test_5() {

	}

}
