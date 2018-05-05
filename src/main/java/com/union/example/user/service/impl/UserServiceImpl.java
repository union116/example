/**
 * 
 */
package com.union.example.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.union.example.user.service.UserService;
import com.union.example.user.vo.UserVo;

/**
 * @Title                  사용자 service
 * @Packagename    com.union.example.user.service.impl
 * @Methodname      UserServiceImpl
 * @Author               lee
 * @Date                  2018. 3. 9.
 * @Version              1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name="userMapper")
	private UserMapper userMapper;
	
	/* (non-Javadoc)
	 * @see com.union.example.user.service.UserService#insertUserInfo(com.union.example.user.vo.UserVo)
	 */
	@Override
	public int insertUserInfo(UserVo userVo) throws Exception {
		return userMapper.insertUserInfo(userVo);
	}

	/* (non-Javadoc)
	 * @see com.union.example.user.service.UserService#selectCntUserInfo(com.union.example.user.vo.UserVo)
	 */
	@Override
	public int selectCntUserInfo(UserVo userVo) throws Exception {
		return userMapper.selectCntUserInfo(userVo);
	}

}
