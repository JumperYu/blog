package com.yu.common.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	@Resource
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}
}
