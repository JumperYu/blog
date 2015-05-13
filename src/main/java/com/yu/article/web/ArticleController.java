package com.yu.article.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.ueditor.ActionEnter;
import com.yu.article.po.Article;
import com.yu.common.exception.ServiceException;
import com.yu.common.po.Result;
import com.yu.common.po.ResultCode;
import com.yu.common.util.LoginUserContext;
import com.yu.common.web.BaseController;
import com.yu.user.po.Account;

/**
 * 
 * 管理文章
 *
 * @author zengxm
 * @date 2015年5月8日
 *
 */
@Controller
public class ArticleController extends BaseController {

	// 阅览具体路径下的文章
	@RequestMapping(value = "/article/{articleId}")
	public String view(@PathVariable long articleId,
			Map<String, Object> context) {
		context.putAll(INIT_PARAMS);
		Article article = getArticleService().getArticle(articleId);
		if (article != null)
			context.put("article", article);
		else
			throw new ServiceException(ResultCode.UNKNOWN_ERROR,
					"article not found");
		return "article/view";
	}
	
	// 编辑或添加文章
	@RequestMapping("/article/edit.html")
	public String edit(@RequestParam(required = false, defaultValue = "0") long articleId, Map<String, Object> context) {
		Account account = LoginUserContext.getLoginAccount();
		context.put("account", account);
		context.putAll(INIT_PARAMS);
		Article article = getArticleService().getArticle(articleId);
		if (article != null)
			context.put("article", article);
		return "article/edit";
	}
	
	// 文章列表
	@RequestMapping("/article/list.html")
	public String listPages(Map<String, Object> context) {
		return date("", "", "", context);
	}
	
	// 年份下面的文章列表
	@RequestMapping("/{year}")
	public String year(@PathVariable String year, Map<String, Object> context) {
		return month(year, "", context);
	}

	// 年月份下面的文章列表
	@RequestMapping("/{year}/{month}")
	public String month(@PathVariable String year, String month, Map<String, Object> context) {
		return date(year, month, "", context);
	}

	// 年月日份下面的文章列表
	@RequestMapping("/{year}/{month}/{day}")
	public String date(@PathVariable String year, @PathVariable String month, @PathVariable String day,
			Map<String, Object> context) {
		Account account = LoginUserContext.getLoginAccount();
		context.put("account", account);
		context.put("articles", getArticleService().getAllArticles(year, month, day));
		context.putAll(INIT_PARAMS);
		return "article/list";
	}
	
	// 返回UEditor的初始化JSON - 有点坑
	@RequestMapping("/article/config.json")
	@ResponseBody
	public String config(HttpServletRequest request) {
		String root = getClass().getClassLoader()
				.getResource("/ueditor.config.json").getPath();
		String result = new ActionEnter(request, root).exec();
		return result;
	}
	
	// 提交文章
	@RequestMapping(value = "/article/submit")
	@ResponseBody
	public Result<String> submit(Article article) {// @RequestParam(required =
													// false, value = "file")
													// MultipartFile file
		int rows = getArticleService().saveOrUpdateArticle(article);
		Result<String> result = new Result<String>(ResultCode.SUCCESS,
				"success", "successs");

		if (rows == 1) {
			result.setCode(ResultCode.SUCCESS);
			result.setMessage("发布成功");
		} else {
			result.setCode(ResultCode.FAILURE);
			result.setMessage("发布失败");
		}

		return result;
	}

}
