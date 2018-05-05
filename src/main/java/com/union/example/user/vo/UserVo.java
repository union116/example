/**
 * 
 */
package com.union.example.user.vo;

import java.io.Serializable;

/**
 * @Title                  사용자 vo
 * @Packagename    com.union.example.user.vo
 * @Methodname      UserVo
 * @Author               lee
 * @Date                  2018. 3. 9.
 * @Version              1.0
 */
public class UserVo implements Serializable {

	private static final long serialVersionUID = -7013764711865823811L;
		
	private String password;
	private String email;
	private String nickName;
	private String snsId;
	private String snsType;
	private String snsProfile;
	
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * @return the snsId
	 */
	public String getSnsId() {
		return snsId;
	}
	/**
	 * @param snsId the snsId to set
	 */
	public void setSnsId(String snsId) {
		this.snsId = snsId;
	}
	/**
	 * @return the snsType
	 */
	public String getSnsType() {
		return snsType;
	}
	/**
	 * @param snsType the snsType to set
	 */
	public void setSnsType(String snsType) {
		this.snsType = snsType;
	}
	/**
	 * @return the snsProfile
	 */
	public String getSnsProfile() {
		return snsProfile;
	}
	/**
	 * @param snsProfile the snsProfile to set
	 */
	public void setSnsProfile(String snsProfile) {
		this.snsProfile = snsProfile;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((snsId == null) ? 0 : snsId.hashCode());
		result = prime * result + ((snsProfile == null) ? 0 : snsProfile.hashCode());
		result = prime * result + ((snsType == null) ? 0 : snsType.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserVo other = (UserVo) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;		
		if (nickName == null) {
			if (other.nickName != null)
				return false;
		} else if (!nickName.equals(other.nickName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (snsId == null) {
			if (other.snsId != null)
				return false;
		} else if (!snsId.equals(other.snsId))
			return false;
		if (snsProfile == null) {
			if (other.snsProfile != null)
				return false;
		} else if (!snsProfile.equals(other.snsProfile))
			return false;
		if (snsType == null) {
			if (other.snsType != null)
				return false;
		} else if (!snsType.equals(other.snsType))
			return false;
		return true;
	}
			
	
}
