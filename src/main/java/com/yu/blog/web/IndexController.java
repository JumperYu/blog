package com.yu.blog.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yu.article.po.Article;
import com.yu.common.exception.ServiceException;
import com.yu.common.po.ResultCode;
import com.yu.common.web.BaseController;

/**
 * 
 * blog.bradypod.com 博客二级域名
 *
 * @author zengxm
 * @date 2015年5月8日
 *
 */
@Controller
public class IndexController extends BaseController {
	
	// 首页
	@RequestMapping("/")
	public String root(Map<String, Object> context) {
		return index(context);
	}
	
	// 首页
	@RequestMapping("/index.html")
	public String index(Map<String, Object> context) {
		context.putAll(INIT_PARAMS);
		List<Article> articles = getArticleService().getAllArticles("", "", "");
		context.put("articles", articles);
		return "index";
	}

	// 阅览具体路径下的文章
	@RequestMapping(value = "/article/{articleId}")
	public String view(@PathVariable long articleId, Map<String, Object> context) {
		context.putAll(INIT_PARAMS);
		Article article = getArticleService().getArticle(articleId);
		if (article != null)
			context.put("article", article);
		else
			throw new ServiceException(ResultCode.UNKNOWN_ERROR,
					"article not found");
		return "view";
	}

}
