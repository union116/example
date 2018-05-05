package com.union.example.board.service;

import com.union.example.board.vo.MainBbsVo;
import java.util.List;
import org.springframework.ui.ModelMap;

/**
 * @Title                  메인 게시판 service
 * @Packagename    com.union.example.board.service
 * @Methodname      MainBbsService
 * @Author               lee
 * @Date                  2018. 3. 7.
 * @Version              1.0
 */
public interface MainBbsService{
	
	/**
	 * @Title : 목록
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	public List<MainBbsVo> selectMainList(MainBbsVo paramMainBbsVo, ModelMap paramModelMap) throws Exception;

	/**
	 * @Title : 등록
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	public void insertMainData(MainBbsVo paramMainBbsVo) throws Exception;

	/**
	 * @Title : 상세
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	public MainBbsVo selectMainData(MainBbsVo paramMainBbsVo) throws Exception;

	/**
	 * @Title : 삭제
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	public void deleteMainData(MainBbsVo paramMainBbsVo) throws Exception;

	/**
	 * @Title : 수정
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	public void modifyMainData(MainBbsVo paramMainBbsVo) throws Exception;
	
}