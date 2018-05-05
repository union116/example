package com.union.example.common.vo;

import com.union.example.common.utils.Util;

/**
 * @Title                  공통 vo
 * @Packagename    com.union.example.common.vo
 * @Methodname      CommonVo
 * @Author               lee
 * @Date                  2018. 3. 7.
 * @Version              1.0
 */
public class CommonVo {
	
	private int pgno = 1;
	private int lists = 0;
	private int pageSize = 5;
	private String keyfield;
	private String keyword;
	private String schStartDate;
	private String schEndDate;
	private int firstRecordIndex;
	
	/**
	 * @return the pgno
	 */
	public int getPgno() {
		return pgno;
	}
	/**
	 * @param pgno the pgno to set
	 */
	public void setPgno(int pgno) {
		this.pgno = pgno;
	}
	/**
	 * @return the lists
	 */
	public int getLists() {
		return lists == 0 ? 10 : lists;
	}
	/**
	 * @param lists the lists to set
	 */
	public void setLists(int lists) {
		this.lists = lists;
	}
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the keyfield
	 */
	public String getKeyfield() {
		return Util.StringReplaceSpecialCharacter(keyfield);
	}
	/**
	 * @param keyfield the keyfield to set
	 */
	public void setKeyfield(String keyfield) {
		this.keyfield = keyfield;
	}
	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}
	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	/**
	 * @return the schStartDate
	 */
	public String getSchStartDate() {
		return schStartDate;
	}
	/**
	 * @param schStartDate the schStartDate to set
	 */
	public void setSchStartDate(String schStartDate) {
		this.schStartDate = schStartDate;
	}
	/**
	 * @return the schEndDate
	 */
	public String getSchEndDate() {
		return schEndDate;
	}
	/**
	 * @param schEndDate the schEndDate to set
	 */
	public void setSchEndDate(String schEndDate) {
		this.schEndDate = schEndDate;
	}
	/**
	 * @return the firstRecordIndex
	 */
	public int getFirstRecordIndex() {
		return firstRecordIndex;
	}
	/**
	 * @param firstRecordIndex the firstRecordIndex to set
	 */
	public void setFirstRecordIndex(int firstRecordIndex) {
		this.firstRecordIndex = firstRecordIndex;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + firstRecordIndex;
		result = prime * result + ((keyfield == null) ? 0 : keyfield.hashCode());
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
		result = prime * result + lists;
		result = prime * result + pageSize;
		result = prime * result + pgno;
		result = prime * result + ((schEndDate == null) ? 0 : schEndDate.hashCode());
		result = prime * result + ((schStartDate == null) ? 0 : schStartDate.hashCode());
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
		CommonVo other = (CommonVo) obj;
		if (firstRecordIndex != other.firstRecordIndex)
			return false;
		if (keyfield == null) {
			if (other.keyfield != null)
				return false;
		} else if (!keyfield.equals(other.keyfield))
			return false;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		if (lists != other.lists)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (pgno != other.pgno)
			return false;
		if (schEndDate == null) {
			if (other.schEndDate != null)
				return false;
		} else if (!schEndDate.equals(other.schEndDate))
			return false;
		if (schStartDate == null) {
			if (other.schStartDate != null)
				return false;
		} else if (!schStartDate.equals(other.schStartDate))
			return false;
		return true;
	};
	
	
}