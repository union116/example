/**
 * 
 */
package com.union.example.login.service.impl;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.union.example.login.service.LoginService;
import com.union.example.user.vo.UserVo;

/**
 * @Title                  로그인 serviceImpl
 * @Packagename    com.union.example.login.service.impl
 * @Methodname      LoginServiceImpl
 * @Author               lee
 * @Date                  2018. 3. 12.
 * @Version              1.0
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService, UserDetailsService {

	@Resource(name="loginMapper")
	private LoginMapper loginMapper;
	
	
	/* (non-Javadoc)
	 * @see com.union.example.login.service.LoginService#selectUserInfoByIdnPwd(com.union.example.user.vo.UserVo)
	 */
	@Override
	public UserVo selectUserInfoByIdnPwd(UserVo userVo) throws Exception {
		return loginMapper.selectUserInfoByIdnPwd(userVo);
	}


	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
