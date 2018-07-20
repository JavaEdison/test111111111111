package com.zjf.babasports.utils;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestUtils {
	/**
	 * 单点登录手动验证sessionid
	 * @param request
	 * @param response
	 * @return
	 */
	public static String getCSessionID(HttpServletRequest request,HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("CSESSIONID")){
					return cookie.getValue();
				}
			}
		}
		String CSESSIONID=UUID.randomUUID().toString().replaceAll("-", "");
		Cookie cookie=new Cookie("CSESSIONID", CSESSIONID);
		//设置存活时间
		cookie.setMaxAge(-1);
		//设置路径
		cookie.setPath("/");//表示www.jd.com/这个路径内，也就是全网站都带此cookie
		response.addCookie(cookie);
		return CSESSIONID;
	}
}
