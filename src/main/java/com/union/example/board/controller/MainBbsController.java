package com.union.example.board.controller;

import com.union.example.board.service.MainBbsService;
import com.union.example.board.vo.MainBbsVo;
import com.union.example.common.controller.CommonController;
import com.union.example.common.utils.Constants;
import com.union.example.common.utils.Fn;
import com.union.example.common.utils.Util;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Title                  메인 게시판 controller
 * @Packagename    com.union.example.board.controller
 * @Methodname      MainBbsController
 * @Author               lee
 * @Date                  2018. 3. 6.
 * @Version              1.0
 * 
 */
@Controller
@RequestMapping("/main")
public class MainBbsController extends CommonController {
	
	private static final Logger LOGGER = Logger.getLogger(MainBbsController.class);

	@Resource(name="mainBbsService")
	private MainBbsService mainService;
	
	public final String PARAMS = "schDateField,schStartDate,schEndDate";

	/**
	 * @Title : 목록
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, MainBbsVo mainVo, ModelMap model) throws Exception {
		
		if (((Boolean)getSession().getAttribute("IS_MOBILE")).booleanValue()) {
			mainVo.setPageSize(1);
			mainVo.setLists(5);
		}
	
		List<MainBbsVo> list = mainService.selectMainList(mainVo, model);
		model.addAttribute("mainList", list); 
	
		return "main/list";
	};

	/**
	 * @Title : 상세
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	@RequestMapping("/view")
	public ModelAndView view(HttpServletRequest request, MainBbsVo mainVo) throws Exception {
	    ModelAndView mv = new ModelAndView();
	    MainBbsVo resultVo = new MainBbsVo();
	
	    mv.setViewName("main/view");
	
	    resultVo = mainService.selectMainData(mainVo);
	    mv.addObject("mainVo", resultVo);
	
	    return mv;
	};

	/**
	 * @Title : 등록 페이지 이동
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	@RequestMapping("/add")
	public String add(HttpServletRequest request, MainBbsVo mainVo, ModelMap model) throws Exception {
		return "main/add";
	};

	/**
	 * @Title : 등록
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	@RequestMapping("/insert")
	public String insert(HttpServletRequest request, @ModelAttribute("mainVo") MainBbsVo mainVo, ModelMap model) throws Exception {
	    mainService.insertMainData(mainVo);
	
	    Util.swalSuccessMessage("등록 되었습니다.", request);
	    //String paramStr = Fn.toParamStr(request, "&", Constants.PARAM_NAME + "," + PARAMS);
	
		//return "redirect:/main/view?no=" + mainVo.getNo() + paramStr;
		return "redirect:/main/view?no=" + mainVo.getNo();
	};

	/**
	 * @Title : 삭제
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	@RequestMapping({"/delete"})
	public String delete(HttpServletRequest request, @ModelAttribute("mainVo") MainBbsVo mainVo, ModelMap model) throws Exception {
		mainService.deleteMainData(mainVo);

		Util.swalSuccessMessage("삭제 되었습니다.", request);

		return "redirect:/main/list";
	};

	/**
	 * @Title : 수정 페이지 이동
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	@RequestMapping({"/update"})
	public ModelAndView update(HttpServletRequest request, MainBbsVo mainVo) throws Exception {
		ModelAndView mv = new ModelAndView();
		MainBbsVo resultVo = new MainBbsVo();

		mv.setViewName("main/update");

		resultVo = mainService.selectMainData(mainVo);
		mv.addObject("mainVo", resultVo);

		return mv;
	};

	/**
	 * @Title : 수정
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	@RequestMapping({"/modify"})
	public String modify(HttpServletRequest request, @ModelAttribute("mainVo") MainBbsVo mainVo, ModelMap model) throws Exception {
		mainService.modifyMainData(mainVo);

		Util.swalSuccessMessage("수정 되었습니다.", request);
		//String paramStr = Fn.toParamStr(request, "&", Constants.PARAM_NAME + "," + PARAMS);

		//return "redirect:/main/view?no=" + mainVo.getNo() + paramStr;
		return "redirect:/main/view?no=" + mainVo.getNo();
	};
	
}