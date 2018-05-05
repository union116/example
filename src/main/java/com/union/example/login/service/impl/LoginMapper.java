/**
 * 
 */
package com.union.example.login.service.impl;

import org.springframework.stereotype.Repository;

import com.union.example.user.vo.UserVo;

/**
 * @Title                  로그인 mapper
 * @Packagename    com.union.example.login.service.impl
 * @Methodname      LoginMapper
 * @Author               lee
 * @Date                  2018. 3. 12.
 * @Version              1.0
 */
@Repository("loginMapper")
public interface LoginMapper {

	/**
	 * @Title : 회원정보 조회
	 * @Author : lee
	 * @Date : 2018. 3. 12.
	 */
	public UserVo selectUserInfoByIdnPwd(UserVo userVo) throws Exception;

}
