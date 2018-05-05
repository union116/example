package com.union.example;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title                  home controller
 * @Packagename    com.union.example
 * @Methodname      HomeController
 * @Author               lee
 * @Date                  2018. 3. 7.
 * @Version              1.0
 */
@Controller
public class HomeController{

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * @Title : 메인 페이지 이동 
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	@RequestMapping(value={"/"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String home(Locale locale, Model model) {
		return "redirect:/main/list";
	};
	
}