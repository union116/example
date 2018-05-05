package com.union.example;
/**
 * 한글이름 영문으로 변환자바
 * @author 오현명
 * @version 1.0
 * @see
 */

public class NameTrans {
	
	 private static NameTrans instance = null;
	 
	 public static NameTrans getInstance() {
		 if(instance == null) {
			 instance = new NameTrans();
		 }
		 return instance;
	 }
	 
    /* 초성, 중성, 종성 */ 
    private final String[] firstSounds = {"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"}; 
    private final String[] middleSounds = {"ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"};
    private final String[] lastSounds = {" ", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"}; 

    /* 초성, 중성, 종성 에 대한 영어 매핑 */ 
    private final String[] efirstSounds = {"g", "kk", "n", "d", "tt", "r", "m", "b", "pp", "s", "ss", "", "j", "jj", "ch", "k", "t", "p", "h"};
    private final String[] emiddleSounds = {"a", "ae", "ya", "yae", "eo", "e", "yeo", "ye", "o", "wa", "wae", "oe", "yo", "u", "wo", "we", "wi", "yu", "eu", "ui", "i"};
    private final String[] elastSounds = {"", "k", "uk", "None", "n", "n", "n", "None", "l", "l", "None", "None", "None", "None", "None", "None", "m", "b", "p", "s", "ss", "ng", "t", "c", "c", "t", "p", "h"}; 
    
	/*자음 모음 분리*/
	/** 초성 - 가(ㄱ), 날(ㄴ) 닭(ㄷ) */
	public char[] arrChoSung = { 0x3131, 0x3132, 0x3134, 0x3137, 0x3138,
			0x3139, 0x3141, 0x3142, 0x3143, 0x3145, 0x3146, 0x3147, 0x3148,
			0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };
	/** 중성 - 가(ㅏ), 야(ㅑ), 뺨(ㅑ)*/
	public char[] arrJungSung = { 0x314f, 0x3150, 0x3151, 0x3152,
			0x3153, 0x3154, 0x3155, 0x3156, 0x3157, 0x3158, 0x3159, 0x315a,
			0x315b, 0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161, 0x3162,
			0x3163 };
	/** 종성 - 가(없음), 갈(ㄹ) 천(ㄴ) */
	public char[] arrJongSung = { 0x0000, 0x3131, 0x3132, 0x3133,
			0x3134, 0x3135, 0x3136, 0x3137, 0x3139, 0x313a, 0x313b, 0x313c,
			0x313d, 0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144, 0x3145,
			0x3146, 0x3147, 0x3148, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };
	
	/*예외처리*/
	private final String[] Firstextarget = {"gim","i","bak","choe","Jeong","jo","gang","yun","jang","im","sin","o","an","gwon","yu","jeon","mun","ju","gwak","seong","u","gu","eom","gang","hyeon","byeon","myeong"};
	private final String[] Firstexchange = {"kim","lee","park","choi","jung","cho","kang","yoon","chang","lim","shin","oh","ahn","kwon","yoo","jun","moon","joo","kwak","sung","woo","koo","um","kang","hyun","byun","myoung"};
    
    public static void main(String[] args){
		String eHanGeul = "명계남";
		String enName = NameTrans.getInstance().ChangetoEnglishName(eHanGeul);
		
		System.out.println("한글명 : " + eHanGeul);
		System.out.println("영문명 : " + enName); 
    }


	public String ChangetoEnglishName(String word) {

		if(word.equals("")||word.equals(null)){
			return word;
		}
		
		String result		= "";	// 한글 쪼개기
		String enresult		= "";	// 영문변환
		
		for (int i = 0; i < word.length(); i++) {
			
			/*  한글자씩 읽어들인다. */
			char chars = (char) (word.charAt(i) - 0xAC00);

			if (chars >= 0 && chars <= 11172) {
				/* A. 자음과 모음이 합쳐진 글자인경우 */

				/* A-1. 초/중/종성 분리 */
				int chosung 	= chars / (21 * 28);
				int jungsung 	= chars % (21 * 28) / 28;
				int jongsung 	= chars % (21 * 28) % 28;

				/* A-2. result에 담기 */
				result = result + arrChoSung[chosung] + arrJungSung[jungsung];
				enresult = enresult + getFirstName(String.valueOf(arrChoSung[chosung])) 
						 			+ getMiddletName(String.valueOf(arrJungSung[jungsung]));
				
				/* 자음분리 */
				if (jongsung != 0x0000) {
					/* A-3. 종성이 존재할경우 result에 담는다 */
					result =  result + arrJongSung[jongsung];
					enresult = enresult + getLastName(String.valueOf(arrJongSung[jongsung]));
				}

			} else {
				/* B. 한글이 아니거나 자음만 있을경우 */
				/* 자음분리 */
				result = result + ((char)(chars + 0xAC00));
			}//if
			
			result = result+" ";
			enresult = enresult+" ";
		}//for
		
		//예외 처리 (ex: 이 ==> i(x), LEE)
		String[] enresultarray = enresult.split(" ");
		boolean changeYN = false;
		String change = "";
		
		change = enresultarray[0];
		/*for (int i = 0; i < enresultarray.length; i++) {*/
			for (int j = 0; j < Firstextarget.length; j++) {
				if(enresultarray[0].equals(Firstextarget[j])){
					change = Firstexchange[j];
					changeYN = true;
				}/*else{
					change = enresultarray[0];
				}*/
			}
			if(enresultarray.length == 3){
				change = change +" "+enresultarray[1]+" "+enresultarray[2];
			}else if(enresultarray.length == 2){
				change = change +" "+enresultarray[1];
			}
		/*}*/
		
		if(changeYN){
			enresult = change;
		}
		
		//System.out.println("============ result ==========");
		//System.out.println("단어     : " + word);
		//System.out.println("자음분리 : " + result);
		//System.out.println("자음분리 : " + enresult);
		
		return enresult;
	}
    
    
    
    /***************** 영어작업 *****************/ 
    public String getFirstName(String First){
    	
    	String FirstName = "";
    	
    	for (int i = 0; i < firstSounds.length; i++) {
    		if(First.equals(firstSounds[i])){
    			FirstName = efirstSounds[i];
    			break;
    		}
		}
		return FirstName;
    }
    
    public String getMiddletName(String Middle){
    	
    	String MiddleName = "";
    	
    	for (int i = 0; i < middleSounds.length; i++) {
    		if(Middle.equals(middleSounds[i])){
    			MiddleName = emiddleSounds[i];
    			break;
    		}
		}
		return MiddleName;
    }
    
    public String getLastName(String Last){
    	
    	String LastName = "";
    	if(!Last.equals("")||!Last.equals(null)){
	    	for (int i = 0; i < lastSounds.length; i++) {
	    		if(Last.equals(lastSounds[i])){
	    			LastName = elastSounds[i];
	    			break;
	    		}
			}
    	}
		return LastName;
    }

}
