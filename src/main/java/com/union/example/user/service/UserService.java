/**
 * 
 */
package com.union.example.user.service;

import com.union.example.user.vo.UserVo;

/**
 * @Title                  사용자 service
 * @Packagename    com.union.example.user.service
 * @Methodname      UserService
 * @Author               lee
 * @Date                  2018. 3. 9.
 * @Version              1.0
 */

public interface UserService {

	/**
	 * @Title : 회원 등록
	 * @Author : lee
	 * @Date : 2018. 3. 9.
	 */
	public int insertUserInfo(UserVo userVo) throws Exception;

	/**
	 * @Title : "title"
	 * @Author : lee
	 * @Date : 2018. 3. 9.
	 */
	public int selectCntUserInfo(UserVo userVo) throws Exception;

}
