package com.yu.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.common.LoginUserContext;
import com.yu.common.Result;
import com.yu.common.ResultCode;
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

	@RequestMapping("/auth/login.do")
	@ResponseBody
	public Result<String> login(Account account, HttpServletRequest request, HttpServletResponse response) {
//		String host = request.getHeader("host");
//		String uri = request.getRequestURI();
		Cookie cookie = new Cookie("passport", account.getPassport());
		cookie.setDomain("localhost"); // 请求域来源
//		cookie.setHttpOnly(true); // 前端脚本无法获取
		cookie.setMaxAge(-1); // -1表示关闭浏览器则消失
		cookie.setPath("/");
		response.addCookie(cookie);
		LoginUserContext.setLoginAccount(account);
		Result<String> result = new Result<String>(ResultCode.SUCCESS, "haha",
				"login success");
		return result;
	}

}
