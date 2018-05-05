/**
 * 
 */
package com.union.example.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.union.example.common.controller.CommonController;
import com.union.example.common.utils.Constants;
import com.union.example.common.utils.Fn;
import com.union.example.common.utils.Util;
import com.union.example.login.service.LoginService;
import com.union.example.user.vo.UserVo;


/**
 * @Title                  로그인 controller
 * @Packagename    com.union.example.login.controller
 * @Methodname      LoginController
 * @Author               lee
 * @Date                  2018. 3. 7.
 * @Version              1.0
 */
@Controller
public class LoginController extends CommonController {

	private static final Logger LOGGER = Logger.getLogger(LoginController.class);
	
	public final String PARAMS = "schDateField,schStartDate,schEndDate";
	
	@Resource(name="loginService")
	private LoginService loginService;
	
	/**
	 * @Title : 로그인 페이지 이동
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request, ModelMap model) throws Exception {
		
		// 이전 페이지
		String referer = (String)request.getHeader("Referer"); 
		model.addAttribute("returnUrl", referer);
		
		String paramStr = Fn.toParamStr(request, "&", Constants.PARAM_NAME + "," + PARAMS);
		model.addAttribute("paramStr", paramStr);
		
		return "login/login";
	};

	/**
	 * @Title : 로그인
	 * @Author : lee
	 * @Date : 2018. 3. 12.
	 */
	@RequestMapping("/loginProc")
	public String loginProc(HttpServletRequest request, @ModelAttribute("userVo") UserVo userVo, ModelMap model) throws Exception {
				
		String resultView = "";		
		
		// 비밀번호 암호화
		
		// 아이디 비밀번호 비교		
		userVo = loginService.selectUserInfoByIdnPwd(userVo);
		
		if (userVo != null) {
			// 세션에 저장
			getSession().setAttribute("UVO", userVo);
			Util.swalSuccessMessage("로그인 되었습니다.", request);
			resultView = getParameter("returnUrl");
		}else{
			Util.swalWarningMessage("아이디 또는 비밀번호를 다시 확인하세요. 등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.", model);
			//model.addAttribute("returnUrl", returnUrl);
			//return returnPage;
			resultView = "login/login"; 
		};
		
		return resultView;
	};
	
}
