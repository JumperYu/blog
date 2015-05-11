package com.yu.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yu.common.util.LoginUserContext;
import com.yu.user.po.Account;

/**
 * 
 * 拦截所有请求
 * 检查ThreadLocal的副本变量和Cookie里面的存储值
 *
 * @author    zengxm
 * @date       2015年5月2日
 *
 */
public class AuthInterceptor implements HandlerInterceptor {

	private static Map<String, Boolean> excludeUrlMap = new HashMap<String, Boolean>();
	private static String loginPage;

	private static final Logger log = LoggerFactory
			.getLogger(AuthInterceptor.class);

	public void setExcludeUrls(String[] excludeUrls) {
		for (String s : excludeUrls) {
			excludeUrlMap.put(s, Boolean.TRUE);
		}
		log.info(String.format("初始化忽略拦截接口:%s", excludeUrlMap));
	}

	public void setLoginPage(String page) {
		loginPage = page;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		log.info(request.getRequestURI());
		if (excludeUrlMap.containsKey(request.getRequestURI()))
			return true;
		else {
			Account account = LoginUserContext.getLoginAccount();
			if (account != null) {
				log.info(String.format("The user %s is logged in context",
						account.getPassport()));
				return true;
			}
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie != null && "passport".equals(cookie.getName())) {
						LoginUserContext.setLoginAccount(new Account(cookie
								.getValue()));
						log.info(String.format(
								"The user %s is logged in cookied",
								cookie.toString()));
						return true;
					}
				}
			}
			log.info("The user is not log in, redirect to the login page");
			response.sendRedirect(loginPage + "?fromPage=" + request.getRequestURI());
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
