package com.yu.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.yu.user.po.Account;

public interface AccountMapper {

	@Select("SELECT * FROM Account WHERE passport = #{passport}")
	public Account queryAccountByPassport(String passport);

	@Select("SELECT * FROM Account")
	public List<Account> selectAll();

	public Account selectOne();

	public List<Account> queryAll(Map<String, Object> params);

	public int insert();
}
