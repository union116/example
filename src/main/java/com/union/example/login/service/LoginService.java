/**
 * 
 */
package com.union.example.login.service;

import com.union.example.user.vo.UserVo;

/**
 * @Title                  로그인 service
 * @Packagename    com.union.example.login.controller
 * @Methodname      LoginService
 * @Author               lee
 * @Date                  2018. 3. 12.
 * @Version              1.0
 */
public interface LoginService {

	/**
	 * @Title : 회원정보 조회
	 * @Author : lee
	 * @Date : 2018. 3. 12.
	 */
	public UserVo selectUserInfoByIdnPwd(UserVo userVo) throws Exception;

}
