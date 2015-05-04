package com.yu.common.util;

import com.yu.user.po.Account;

public class LoginUserContext {

	private static ThreadLocal<Account> curThread = new ThreadLocal<Account>();

	public static Account getLoginAccount() {
		Account account = curThread.get();
		return account;
	}

	public static void setLoginAccount(Account account) {
		curThread.set(account);
	}
}
