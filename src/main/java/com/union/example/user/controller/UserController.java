/**
 * 
 */
package com.union.example.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.union.example.common.controller.CommonController;
import com.union.example.common.utils.Util;
import com.union.example.login.controller.LoginController;
import com.union.example.user.service.UserService;
import com.union.example.user.vo.UserVo;

/**
 * @Title                  사용자 controller
 * @Packagename    com.union.example.user.controller
 * @Methodname      UserController
 * @Author               lee
 * @Date                  2018. 3. 7.
 * @Version              1.0
 */
@Controller
@RequestMapping("/user")
public class UserController extends CommonController {

	private static final Logger LOGGER = Logger.getLogger(LoginController.class);
	
	@Resource(name="userService")
	public UserService userService;

	@Resource(name="passwordEncoder")
	private ShaPasswordEncoder encoder;
		
	/**
	 * @Title : 회원 가입 페이지 이동
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	@RequestMapping("/add")
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		return "user/add";
	};
	
	/**
	 * @Title : 회원 등록
	 * @Author : lee
	 * @Date : 2018. 3. 9.
	 */
	@RequestMapping("/insert")
	public String insert(HttpServletRequest request, @ModelAttribute("userVo") UserVo userVo) throws Exception {

		// password encoding
		String encodedPassword = encoder.encodePassword(userVo.getPassword(),null);
		//LOGGER.debug("pasword incoding : "+encodedPassword);
		userVo.setPassword(encodedPassword);
		
		String resultView = "";
		int cnt = userService.selectCntUserInfo(userVo);
		if(cnt > 0) {
			Util.swalErrorMessage("이미 존재하는 이메일 입니다.", request);
			resultView = "redirect:/user/add";
		}else{
			int result = userService.insertUserInfo(userVo);		
			Util.swalSuccessMessage("회원 등록 되었습니다.", request);
			resultView ="redirect:/login";
		};
		
		return resultView;
	};
	
}
