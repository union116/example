package com.union.example.board.service.impl;

import com.union.example.board.service.MainBbsService;
import com.union.example.board.vo.MainBbsVo;
import com.union.example.common.utils.PaginationInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

/**
 * @Title                  메인 게시판 serviceImpl
 * @Packagename    com.union.example.board.service.impl
 * @Methodname      MainBbsServiceImpl
 * @Author               lee
 * @Date                  2018. 3. 7.
 * @Version              1.0
 */
@Service("mainBbsService")
public class MainBbsServiceImpl implements MainBbsService{

	@Resource(name="mainBbsMapper")
	private MainBbsMapper mainBbsMapper;

	public List<MainBbsVo> selectMainList(MainBbsVo mainVo, ModelMap model) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		List mainList = new ArrayList();

		paginationInfo.setTotalRecordCount(mainBbsMapper.selectMainListCnt());
		paginationInfo.setCurrentPageNo(mainVo.getPgno());
		paginationInfo.setRecordCountPerPage(mainVo.getLists());
		paginationInfo.setPageSize(mainVo.getPageSize());

		mainVo.setFirstRecordIndex(paginationInfo.getFirstRecordIndex());

		mainList = mainBbsMapper.selectMainList(mainVo);
		model.addAttribute("paginationInfo", paginationInfo);

		return mainList;
	};

	public void insertMainData(MainBbsVo mainVo) throws Exception {
		mainBbsMapper.insertMainData(mainVo);
	};

	public MainBbsVo selectMainData(MainBbsVo mainVo) throws Exception {
		return mainBbsMapper.selectMainData(mainVo);
	};

	public void deleteMainData(MainBbsVo mainVo) throws Exception {
		mainBbsMapper.deleteMainData(mainVo);
	};

	public void modifyMainData(MainBbsVo mainVo) throws Exception {
		mainBbsMapper.modifyMainData(mainVo);
	};
	
}