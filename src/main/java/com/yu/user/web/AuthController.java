package com.yu.user.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.article.po.Article;
import com.yu.common.po.Result;
import com.yu.common.po.ResultCode;
import com.yu.common.util.LoginUserContext;
import com.yu.common.util.ParameterUtil;
import com.yu.common.web.BaseController;
import com.yu.user.po.Account;

/**
 * 用户授权
 *
 * @author zengxm
 * @date 2015年4月23日
 *
 */
@Controller
public class AuthController extends BaseController {

	@RequestMapping("/auth/login")
	@ResponseBody
	public Result<String> login(Account account, HttpServletRequest request,
			HttpServletResponse response) {
		Result<String> result = new Result<String>();
		try {
			ParameterUtil.assertNotBlank(account.getPassport(), "用户账号为空");
			ParameterUtil.assertNotBlank(account.getPassword(), "用户密码为空");
			boolean isExists = getUserService().validateAccount(account);
			if (isExists) {
				Cookie cookie = new Cookie("passport", account.getPassport());
				cookie.setDomain("localhost"); // 请求域来源
				cookie.setHttpOnly(true); // 前端脚本无法获取
				cookie.setMaxAge(60 * 60 * 24 * 7); // -1 表示关闭浏览器则消失
				cookie.setPath("/");
				response.addCookie(cookie);
				LoginUserContext.setLoginAccount(account);
				result.setCode(ResultCode.SUCCESS);
				result.setMessage("成功登陆");
			} else {
				result.setCode(ResultCode.RECORD_NOT_EXIST);
				result.setMessage("账号或密码错误");
			}
		} catch (Exception e) {
			log.error("系统未知错误", e);
			result.setCode(ResultCode.UNKNOWN_ERROR);
			result.setMessage("系统出现未知错误");
		}
		return result;
	}

	@RequestMapping("/index.html")
	public String index(Map<String, Object> context) {
		context.putAll(INIT_PARAMS);
		List<Article> articles = getArticleService().getAllArticles();
		context.put("articles", articles);
		return "index";
	}

	@RequestMapping("/welcome.html")
	public String welcome(Map<String, Object> context) {
		Account account = LoginUserContext.getLoginAccount();
		context.put("account", account);
		context.putAll(INIT_PARAMS);
		return "welcome";
	}

	@RequestMapping("/login.html")
	public String login() {
		return "login";
	}
}
