package com.union.example.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Title                  공통 controller
 * @Packagename    com.union.example.common.controller
 * @Methodname      CommonController
 * @Author               lee
 * @Date                  2018. 3. 7.
 * @Version              1.0
 */
public class CommonController {
	
	/**
	 * @Title : request 객체 호출
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	protected static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	};
	
	/**
	 * @Title : response 객체 호출
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	public static HttpServletResponse getResponse() {
		return (HttpServletResponse)RequestContextHolder.getRequestAttributes();
	};

	/**
	 * @Title : 세션
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	protected HttpSession getSession() {
		return getRequest().getSession();
	};

	/**
	 * @Title : request parameter 조회
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	protected String getParameter(String parameterName) {
		return getRequest().getParameter(parameterName);
	};

	/**
	 * @Title : context 호출
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	public ApplicationContext getContext() {
		ApplicationContext AppContext = ContextLoader.getCurrentWebApplicationContext();
		return AppContext;
	};
}