package com.yu.article.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.ueditor.ActionEnter;
import com.yu.article.po.Article;
import com.yu.common.exception.ServiceException;
import com.yu.common.po.Result;
import com.yu.common.po.ResultCode;
import com.yu.common.web.BaseController;

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

	@RequestMapping(value = "/{year}/{month}/{day}/{page}.html")
	public String view(@PathVariable String year, @PathVariable String month,
			@PathVariable String day, @PathVariable String page,
			Map<String, Object> context) {
		context.putAll(INIT_PARAMS);
		String path = String.format("/%s/%s/%s/%s", year, month, day, page);
		Article article = getArticleService().getArticle(path);
		if (article != null)
			context.put("article", article);
		else
			throw new ServiceException(ResultCode.UNKNOWN_ERROR,
					"article not found");
		return "view";
	}

	@RequestMapping("/article/edit.html")
	public String edit() {
		return "article/edit";
	}

	@RequestMapping("/article/config.html")
	@ResponseBody
	public String config(HttpServletRequest request) {
		String root = getClass().getClassLoader()
				.getResource("/ueditor.config.json").getPath();
		String result = new ActionEnter(request, root).exec();
		return result;
	}

	@RequestMapping(value = "/article/submit")
	@ResponseBody
	public Result<String> submit(Article article) {// @RequestParam(required =
													// false, value = "file")
													// MultipartFile file
		log.info(article.toString());
		int rows = getArticleService().addArticle(article);
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

	@RequestMapping("/article/list.html")
	public String list() {
		return "article/list";
	}

}
