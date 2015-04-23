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

import com.yu.common.LoginUserContext;
import com.yu.po.Account;

public class AuthInterceptor implements HandlerInterceptor {

	private final Map<String, Boolean> excludeUrlMap = new HashMap<String, Boolean>();

	private static final Logger log = LoggerFactory
			.getLogger(AuthInterceptor.class);

	public void setExcludeUrls(String[] excludeUrls) {
		for (String s : excludeUrls) {
			excludeUrlMap.put(s, Boolean.TRUE);
		}
		log.info(String.format("初始化忽略拦截接口:%s", excludeUrlMap));
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (excludeUrlMap.containsKey(request.getRequestURI()))
			return true;
		else {
			Account account = LoginUserContext.getLoginAccount();
			if (account != null) {
				log.info(account.toString());
				return true;
			}
			Cookie[] cookies = request.getCookies();
			if (cookies != null)
				for (Cookie cookie : cookies) {
					if (cookie != null && "passport".equals(cookie.getName())) {
						log.info(cookie.toString());
						return true;
					}
				}
			else
				log.info("cookie is null");
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
