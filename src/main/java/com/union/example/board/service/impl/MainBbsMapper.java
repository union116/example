package com.union.example.board.service.impl;

import com.union.example.board.vo.MainBbsVo;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * @Title                  메인 게시판 Mapper
 * @Packagename    com.union.example.board.service.impl
 * @Methodname      MainBbsMapper
 * @Author               lee
 * @Date                  2018. 3. 7.
 * @Version              1.0
 */
@Repository("mainBbsMapper")
public interface MainBbsMapper {
	
	/**
	 * @Title : 목록
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	public List<MainBbsVo> selectMainList(MainBbsVo paramMainBbsVo) throws Exception;

	/**
	 * @Title : 전체 목록 수 
	 * @Author : lee
	 * @Date : 2018. 3. 7.
	 */
	public int selectMainListCnt() throws Exception;

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