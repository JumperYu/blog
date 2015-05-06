package com.yu.article.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.ueditor.ActionEnter;
import com.yu.common.web.BaseController;

@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

	@RequestMapping("/view.html")
	public String index() {
		return "view";
	}

	@RequestMapping("/config.html")
	@ResponseBody
	public String config(HttpServletRequest request) {
		String root = getClass().getClassLoader()
				.getResource("/ueditor.config.json").getPath();
		String result = new ActionEnter(request, root).exec();
		return result;
	}

	@RequestMapping(value = "/submit.html", method = RequestMethod.POST)
	@ResponseBody
	public String submit(String content) {
		log.info(content);
		return "success";
	}
}
