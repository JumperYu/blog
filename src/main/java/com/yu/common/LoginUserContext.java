package com.yu.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yu.po.Account;

public class LoginUserContext {

	private static final Logger log = LoggerFactory
			.getLogger(LoginUserContext.class);

	private static ThreadLocal<Account> curThread = new ThreadLocal<Account>();

	public static Account getLoginAccount() {
		Account account = curThread.get();
		log.info(account == null ? "account is not login" : account.toString());
		return account;
	}

	public static void setLoginAccount(Account account) {
		curThread.set(account);
	}
}
