/**
 * 
 */
package com.union.example.user.service.impl;

import org.springframework.stereotype.Repository;

import com.union.example.user.vo.UserVo;

/**
 * @Title                  사용자 Mapper
 * @Packagename    com.union.example.user.service.impl
 * @Methodname      UserMapper
 * @Author               lee
 * @Date                  2018. 3. 9.
 * @Version              1.0
 */
@Repository("userMapper")
public interface UserMapper {

	/**
	 * @Title : 사용자 등록
	 * @Author : lee
	 * @Date : 2018. 3. 9.
	 */
	public int insertUserInfo(UserVo userVo) throws Exception;

	/**
	 * @Title : 이메일 중복 조회
	 * @Author : lee
	 * @Date : 2018. 3. 9.
	 */
	public int selectCntUserInfo(UserVo userVo) throws Exception;

}
