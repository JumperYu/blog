package com.yu.filter;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

/**
 * 耗时拦截器
 * 
 * @author chenjianhong
 * @time 2014年9月25日 上午11:19:42
 */
public class TimeConsumingInterceptor implements HandlerInterceptor {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final ThreadLocal<Long> timeThreadLocal = new ThreadLocal<Long>();

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		long beginTime = System.currentTimeMillis();
		timeThreadLocal.set(beginTime);
		return true;
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
		long endTime = System.currentTimeMillis();
		long beginTime = timeThreadLocal.get();
		long time = endTime - beginTime;

		String uri = getUri(request);
		String params = getParameters(request);
		logger.info(String
				.format("consuming uri and params: [uri:%s] [params:%s] [internal_mills:%d]",
						uri, params, time));
	}

	/**
	 * 获取请求链接
	 * 
	 * @param request
	 */
	private String getUri(HttpServletRequest request) {
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		String ctxPath = helper.getOriginatingContextPath(request);

		return uri.replaceFirst(ctxPath, "");
	}

	/**
	 * 获取请求参数(组合为字符串返回)
	 * 
	 * @param request
	 */
	private String getParameters(HttpServletRequest request) {

		String uri = getUri(request);
		if (uri.startsWith("/account/")) {
			// 如果是管理员相关的请求, 则不记录参数, 避免重要数据(例如密码)泄露
			return "";
		}

		StringBuilder params = new StringBuilder();

		Enumeration<String> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String name = e.nextElement();
			Object value = request.getParameterValues(name);
			if (value instanceof String[]) {
				for (String v : (String[]) value)
					params.append(name).append("=").append(v).append("&");
			} else {
				params.append(name).append("=").append(value).append("&");
			}
		}

		if (params.length() != 0) {
			params.deleteCharAt(params.length() - 1);
		}

		return params.toString();
	}

}
