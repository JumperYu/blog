package com.yu.common.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yu.service.ArticleService;
import com.yu.user.service.UserService;

/**
 * 
 * 基础模板
 *
 * @author zengxm
 * @date 2015年4月14日
 *
 */
public class BaseController {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
	// 资源地址
	public static final String RESOURCE_ROOT_PATH = "http://resource.bradypod.com/"; 
	// 首页模板地址
	public static final String TEMPLATE_HEAD_PATH = "/template/default/head.html";
	public static final String TEMPLATE_HEADER_PATH = "/template/default/header.html";
	public static final String TEMPLATE_FOOTER_PATH = "/template/default/footer.html";
	public static final String TEMPLATE_SCRIPT_PATH = "/template/default/script.html";
	// 树懒博客地址
	public static final String ROOT_BLOG_BRADYPOD_COM_PATH = "http://localhost";//http://blog.bradypod.com
	
	
	public static final Map<String, Object> INIT_PARAMS = new HashMap<String, Object>() {
		private static final long serialVersionUID = 1L;

		{
			this.put("RESOURCE_ROOT_PATH", RESOURCE_ROOT_PATH);
			this.put("TEMPLATE_HEAD_PATH", TEMPLATE_HEAD_PATH);
			this.put("TEMPLATE_HEADER_PATH", TEMPLATE_HEADER_PATH);
			this.put("TEMPLATE_FOOTER_PATH", TEMPLATE_FOOTER_PATH);
			this.put("TEMPLATE_SCRIPT_PATH", TEMPLATE_SCRIPT_PATH);
			this.put("ROOT_BLOG_BRADYPOD_COM_PATH", ROOT_BLOG_BRADYPOD_COM_PATH);
		}
	};

	@Resource
	private UserService userService;

	@Resource
	private ArticleService articleService;

	public UserService getUserService() {
		return userService;
	}

	public ArticleService getArticleService() {
		return articleService;
	}
}
