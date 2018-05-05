package com.union.example.common.utils;

import java.util.regex.Pattern;

/**
 * @Title                  변수
 * @Packagename    com.union.example.common.utils
 * @Methodname      Constants
 * @Author               lee
 * @Date                  2018. 3. 7.
 * @Version              1.0
 */
public class Constants {
	
	// 첨부파일 web path	
	public static final String WEB_ATTACH_PATH = "/upload";
	// 이메일 패턴
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", 2);
	// 기본 리스트 수
	public static final int DEFAULT_PAGE_LIST_NO = 10;
	// 모바일 리스트 수
	public static final int MOBILE_PAGE_LIST_NO = 5;
	// 기본 페이징 수
	public static final int DEFAULT_PAGE_SIZE_NO = 5;
	// 모바일 페이징 수
	public static final int MOBILE_PAGE_SIZE_NO = 1;
	// 파라메터
	public static final String PARAM_NAME = "no,keyfield,keyword,lists";
	
	// TOAST 오류
	public static final String[] ERROR = { "error", "오류" };
	// TOAST 성공
	public static final String[] SUCCESS = { "success", "성공" };
	// TOAST 정보
	public static final String[] INFO = { "info", "정보" };
	// TOAST 주의
	public static final String[] WARNING = { "warning", "주의" };
	
	// 첨부파일 아이콘 웹서버 패스
	public static final String WEB_ICON_FILE_PATH = "/assets/images/icon";
	// 기본 썸네일 가로 사이즈
	public static final int THUMBNAIL_WIDTH = 475;
	// 기본 썸네일 새로 사이즈
	public static final int THUMBNAIL_HEIGHT = 285;
	// 썸네일 작업 패스
	public static final String THUMBNAIL_WORK_SERVER_PATH = "/web-data/2016_www";
	
}