package com.yu.user.mapper;

import org.apache.ibatis.annotations.Select;

import com.yu.user.po.Account;

public interface AccountMapper {

	@Select("SELECT * FROM Account WHERE passport = #{passport}")
	public Account queryAccountByPassport(String passport);
	
	public Account selectAll();
	
}
