package com.yu.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.common.LoginUserContext;
import com.yu.common.po.Result;
import com.yu.common.po.ResultCode;
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
		Cookie cookie = new Cookie("passport", account.getPassport());
		cookie.setDomain("localhost"); // 请求域来源
		cookie.setHttpOnly(true); // 前端脚本无法获取
		cookie.setMaxAge(60 * 60 * 24 * 7); // -1 表示关闭浏览器则消失
		cookie.setPath("/");
		response.addCookie(cookie);
		LoginUserContext.setLoginAccount(account);
		Result<String> result = new Result<String>(ResultCode.SUCCESS, "haha",
				"login success");
		return result;
	}
	
	@RequestMapping("/index.html")
	public String index() {
		return "index";
	}

	@RequestMapping("/welcome.html")
	public String welcome() {
		return "welcome";
	}

	@RequestMapping("/login.html")
	public String login() {
		return "login";
	}
}
