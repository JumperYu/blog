package com.yu.user.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.yu.common.mapper.BaseMapper;
import com.yu.user.po.Account;

/**
 * 用户相关SQL逻辑
 *
 * @author zengxm
 * @date 2015年5月4日
 *
 */
public interface AccountMapper extends BaseMapper<Account> {

	@Select("select * from account where passport=#{passport} and password=#{password}")
	public Account queryAccount(Map<String, Object> params);

	@Select("select * from account where passport=#{passport} and password=#{passport}")
	public Account queryAccountByPassport(String passport);

	@Select("select count(1) from account where passport=#{passport} and password=#{password}")
	public int isExists(Map<String, Object> params);

	@Select("select 1 from account where passport=#{passport}")
	public int isExistsWithPassport(String passport);
}
