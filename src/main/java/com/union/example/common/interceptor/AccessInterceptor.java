package com.union.example.common.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.union.example.common.utils.Util;

/**
 * @Title                  access intercepter
 * @Packagename    com.union.example.common.interceptor
 * @Methodname      AccessInterceptor
 * @Author               lee
 * @Date                  2018. 3. 7.
 * @Version              1.0
 */
public class AccessInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private ServletContext servletContext;

	/**
	 * @Title : request 객체 호출
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	protected static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	};

	/**
	 * @Title : 세션
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	protected HttpSession getSession() {
		return getRequest().getSession();
	};

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();

		getSession().setAttribute("IS_MOBILE", Boolean.valueOf(false));
		if (Util.isMobile(request)) {
			getSession().setAttribute("IS_MOBILE", Boolean.valueOf(true));
		};

		return true;
	};
	
}