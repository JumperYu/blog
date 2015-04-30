package com.yu.user.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.yu.common.service.MyBatisBaseService;
import com.yu.user.mapper.AccountMapper;

/**
 * 
 * 用户模块业务层
 *
 * @author zengxm
 * @date 2015年4月30日
 *
 */
@Service
public class UserService extends MyBatisBaseService {

	private AccountMapper accountMapper;

	@PostConstruct
	public void init() {
		accountMapper = getMyBatisBaseDAO().getSqlSession().getMapper(
				AccountMapper.class);
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
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("passport", "haha");
		params.put("password", "12345678");
//		log.info("inserted: " + accountMapper.insert(params));
//		log.info("updated:" + accountMapper.update(params));
		log.info("deleted:" + accountMapper.delete(params));
	}

}
