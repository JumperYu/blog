package com.yu.user.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.yu.common.service.MyBatisBaseService;
import com.yu.user.mapper.AccountMapper;
import com.yu.user.po.Account;

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

	/**
	 * 验证账号是否存在
	 * 
	 * @param passport
	 *            账号
	 * @param password
	 *            地址
	 * @return
	 */
	public boolean validateAccount(Account account) {
		if (StringUtils.isEmpty(account.getPassport())
				|| StringUtils.isEmpty(account.getPassword()))
			return false;

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("passport", account.getPassport());
		params.put("password", account.getPassword());
		if (accountMapper.isExists(params) != 1)
			return false;

		return true;
	}

}
