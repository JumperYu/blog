package com.yu.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * 返回视图示例
 *
 * @author zengxm
 * @date 2015年4月14日
 *
 */
@Controller
public class DemoController extends BaseController {

	public DemoController() {
		log.debug("hah");
	}

	@RequestMapping("/test1")
	@ResponseBody
	public String test1() {
		return "haha";
	}

	@RequestMapping("/test2")
	@ResponseBody
	public Map<String, Object> test2() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("a", 123);
		return result;
	}
	
	@RequestMapping("/test3")
	public String test3() {
		return "free";
	}

	@RequestMapping(value = "/test")
	public String test() {
		log.info("qu si ");
		return "haha";
	}

}
