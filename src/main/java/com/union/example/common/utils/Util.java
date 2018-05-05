package com.union.example.common.utils;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SimpleTimeZone;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;

public class Util
{
  public static Object invoke(Object obj, String methodName, Object[] objList)
  {
    Method[] methods = obj.getClass().getMethods();

    for (int i = 0; i < methods.length; i++) {
      if (!methods[i].getName().equals(methodName)) continue;
      try {
        if (methods[i].getReturnType().getName().equals("void"))
          methods[i].invoke(obj, objList);
        else
          return methods[i].invoke(obj, objList);
      }
      catch (IllegalAccessException lae) {
        System.out.println("LAE : " + lae.getMessage());
      } catch (InvocationTargetException ite) {
        System.out.println("ITE : " + ite.getMessage());
      }
    }

    return null;
  }

  public static int getIntTime()
  {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf1 = new SimpleDateFormat("hh");
    return parseInt(sdf1.format(cal.getTime()));
  }

  public static String addDate(String vDate, int calcNum)
    throws ParseException
  {
    if (!isNotEmpty(vDate)) {
      return "";
    }
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal = Calendar.getInstance();
    cal.setTime(dateFormat.parse(vDate));
    cal.add(5, calcNum);
    return dateFormat.format(cal.getTime());
  }

  public static boolean isPast(String date)
    throws ParseException
  {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    if (isEmptyString(date)) {
      return false;
    }

    Date now = new Date();
    Date v_date = format.parse(date);

    int nowCompareStartDate = now.compareTo(v_date);

    return nowCompareStartDate > 0;
  }

  public static boolean isBetweenDate(String nowDate, String startDate, String endDate)
    throws ParseException
  {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    Date nDate = new Date();
    if (isNotEmpty(nowDate)) {
      nDate = format.parse(nowDate);
    }

    if ((startDate == null) || 
      (startDate.equals("")) || 
      (endDate == null) || 
      (endDate.equals(""))) {
      return false;
    }

    Date sDate = format.parse(startDate);

    Date eDate = format.parse(endDate);
    eDate = Fn.getDateAddDay(eDate, 1);

    int nowCompareStartDate = nDate.compareTo(sDate);
    int nowCompareEndDate = nDate.compareTo(eDate);

    return (nowCompareStartDate != -1) && (nowCompareEndDate == -1);
  }

  public static boolean getCellPhone(String phone)
    throws Exception
  {
    boolean result = false;
    String[] phoneType = { "010", "011", "016", "017", "018", "019" };
    for (int i = 0; i < phoneType.length; i++) {
      if (phoneType[i].equals(phone)) {
        return true;
      }
    }
    return result;
  }

  public static String replaceSmsMessage(String message)
  {
    if (isNotEmpty(message)) {
      message = Fn.replaceAll(message, "[", "");
      message = Fn.replaceAll(message, "]", "");
      message = Fn.replaceAll(message, "(", "");
      message = Fn.replaceAll(message, ")", "");
      message = Fn.replaceAll(message, "<", "");
      message = Fn.replaceAll(message, ">", "");
    }

    return message;
  }

  public static String getMyAddress(HttpServletRequest request) throws UnknownHostException
  {
    return request.getRemoteAddr();
  }

  public static void toastSuccessMessage(String message, HttpServletRequest request)
  {
    toastMessageSet(request, Constants.SUCCESS[0], Constants.SUCCESS[1], message, null);
  }

  public static void toastErrorMessage(String message, HttpServletRequest request)
  {
    toastMessageSet(request, Constants.ERROR[0], Constants.ERROR[1], message, null);
  }

  public static void toastInfoMessage(String message, HttpServletRequest request)
  {
    toastMessageSet(request, Constants.INFO[0], Constants.INFO[1], message, null);
  }

  public static void toastWarningMessage(String message, HttpServletRequest request)
  {
    toastMessageSet(request, Constants.WARNING[0], Constants.WARNING[1], message, null);
  }

  public static void toastSuccessMessage(String message, ModelMap model)
  {
    toastMessageSet(model, Constants.SUCCESS[0], Constants.SUCCESS[1], message, null);
  }

  public static void toastErrorMessage(String message, ModelMap model)
  {
    toastMessageSet(model, Constants.ERROR[0], Constants.ERROR[1], message, null);
  }

  public static void toastInfoMessage(String message, ModelMap model)
  {
    toastMessageSet(model, Constants.INFO[0], Constants.INFO[1], message, null);
  }

  public static void toastWarningMessage(String message, ModelMap model)
  {
    toastMessageSet(model, Constants.WARNING[0], Constants.WARNING[1], message, null);
  }

  public static void toastSuccessMessages(List<String> message, ModelMap model)
  {
    toastMessageSet(model, Constants.SUCCESS[0], Constants.SUCCESS[1], stringListToStringWithFlag(message), null);
  }

  public static void toastErrorMessages(List<String> message, ModelMap model)
  {
    toastMessageSet(model, Constants.ERROR[0], Constants.ERROR[1], stringListToStringWithFlag(message), null);
  }

  public static void toastInfoMessages(List<String> message, ModelMap model)
  {
    toastMessageSet(model, Constants.INFO[0], Constants.INFO[1], stringListToStringWithFlag(message), null);
  }

  public static void toastWarningMessages(List<String> message, ModelMap model)
  {
    toastMessageSet(model, Constants.WARNING[0], Constants.WARNING[1], stringListToStringWithFlag(message), null);
  }

  public static void toastMessageSet(HttpServletRequest request, String mode, String title, String message, String position)
  {
    FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
    flashMap.put("toastMode", emptyCk(mode).equals("") ? Constants.INFO[0] : mode);
    flashMap.put("toastTitle", emptyCk(title).equals("") ? Constants.INFO[1] : title);
    flashMap.put("toastMessage", emptyCk(message).equals("") ? "처리가 완료 되었습니다." : message);
    flashMap.put("toastPosition", emptyCk(position).equals("") ? "toast-bottom-left" : position);
  }

  public static void toastMessageSet(ModelMap model, String mode, String title, String message, String position)
  {
    model.addAttribute("toastMode", emptyCk(mode).equals("") ? Constants.INFO[0] : mode);
    model.addAttribute("toastTitle", emptyCk(title).equals("") ? Constants.INFO[1] : title);
    model.addAttribute("toastMessage", emptyCk(message).equals("") ? "처리가 완료 되었습니다." : message);
    model.addAttribute("toastPosition", emptyCk(position).equals("") ? "toast-bottom-left" : position);
  }

  public static void swalSuccessMessage(String message, HttpServletRequest request)
  {
    swalMessageSet(request, Constants.SUCCESS[0], Constants.SUCCESS[1], message);
  }

  public static void swalErrorMessage(String message, HttpServletRequest request)
  {
    swalMessageSet(request, Constants.ERROR[0], Constants.ERROR[1], message);
  }

  public static void swalInfoMessage(String message, HttpServletRequest request)
  {
    swalMessageSet(request, Constants.INFO[0], Constants.INFO[1], message);
  }

  public static void swalWarningMessage(String message, HttpServletRequest request)
  {
    swalMessageSet(request, Constants.WARNING[0], Constants.WARNING[1], message);
  }

  public static void swalSuccessMessage(String message, ModelMap model)
  {
    swalMessageSet(model, Constants.SUCCESS[0], Constants.SUCCESS[1], message);
  }

  public static void swalErrorMessage(String message, ModelMap model)
  {
    swalMessageSet(model, Constants.ERROR[0], Constants.ERROR[1], message);
  }

  public static void swalInfoMessage(String message, ModelMap model)
  {
    swalMessageSet(model, Constants.INFO[0], Constants.INFO[1], message);
  }

  public static void swalWarningMessage(String message, ModelMap model)
  {
    swalMessageSet(model, Constants.WARNING[0], Constants.WARNING[1], message);
  }

  public static void swalSuccessMessages(List<String> message, ModelMap model)
  {
    swalMessageSet(model, Constants.SUCCESS[0], Constants.SUCCESS[1], stringListToStringWithFlag(message));
  }

  public static void swalErrorMessages(List<String> message, ModelMap model)
  {
    swalMessageSet(model, Constants.ERROR[0], Constants.ERROR[1], stringListToStringWithFlag(message));
  }

  public static void swalInfoMessages(List<String> message, ModelMap model)
  {
    swalMessageSet(model, Constants.INFO[0], Constants.INFO[1], stringListToStringWithFlag(message));
  }

  public static void swalWarningMessages(List<String> message, ModelMap model)
  {
    swalMessageSet(model, Constants.WARNING[0], Constants.WARNING[1], stringListToStringWithFlag(message));
  }

  public static void swalMessageSet(HttpServletRequest request, String mode, String title, String message)
  {
    FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
    flashMap.put("swalMode", emptyCk(mode).equals("") ? Constants.INFO[0] : mode);
    flashMap.put("swalTitle", emptyCk(title).equals("") ? Constants.INFO[1] : title);
    flashMap.put("swalMessage", emptyCk(message).equals("") ? "처리가 완료 되었습니다." : message);
  }

  public static void swalMessageSet(ModelMap model, String mode, String title, String message)
  {
    model.addAttribute("swalMode", emptyCk(mode).equals("") ? Constants.INFO[0] : mode);
    model.addAttribute("swalTitle", emptyCk(title).equals("") ? Constants.INFO[1] : title);
    model.addAttribute("swalMessage", emptyCk(message).equals("") ? "처리가 완료 되었습니다." : message);
  }

  public static String getCmsSiteDiv(String str)
  {
    String result = "";
    if (Fn.isEmpty(str)) return result;

    if (Fn.isEqual(str, "ko"))
      result = "open_content";
    else if (Fn.isEqual(str, "en"))
      result = "english";
    else if (Fn.isEqual(str, "ch"))
      result = "chinese";
    else if (Fn.isEqual(str, "ja"))
      result = "japanese";
    else if (Fn.isEqual(str, "ru"))
      result = "russian";
    else if (Fn.isEqual(str, "de")) {
      result = "other";
    }

    return result;
  }

  public static String stringListToStringWithFlag(List<String> list)
  {
    String rtn = "";

    if ((list != null) && (list.size() > 0)) {
      for (int i = 0; i < list.size(); i++) {
        String value = (String)list.get(i);
        rtn = rtn + ", " + value;
      }
      rtn = rtn.substring(1, rtn.length());
    }
    return "추가 입력사항 오류 [ " + rtn + " ](은)는 필수입력 값 입니다.";
  }

  public static HashMap<String, String[]> detpIdAndBbsCodeInfo()
  {
    HashMap map = new HashMap();

    String[] civili = { "B_000064", "행정정보공개", "3220186,3220187,3220188,3220033,3220036" };

    String[] doc = { "B_000060", "민원서식(사무편람)", "3220186,3220187,3220188,3220033,3220036" };

    String[] news = { "B_000031", "보도자료", "" };

    String[] notice = { "B_000001", "공지사항", "3220186,3220187,3220188,3220033,3220036" };

    String[] qna = { "B_000063", "자주묻는 질문", "3220186,3220187,3220188,3220033,3220036" };

    map.put("civili", civili);
    map.put("doc", doc);
    map.put("news", news);
    map.put("notice", notice);
    map.put("qna", qna);

    return map;
  }

  public static boolean isMobile(HttpServletRequest request)
  {
    String agent = request.getHeader("user-agent");
    String[] mobileAgent = { "iPhone", "iPod", "SymbOS", "IEMobile", "Mobile", "lgtelecom", "Android", "BlackBerry", "Windows CE", "LG", "MOT", "SAMSUNG", "SonyEricsson", "SymbianOS", "Nokia", "IEMobile", "PPC" };
    boolean isMobile = false;
    for (int i = 0; i < mobileAgent.length; i++) {
      if (agent.indexOf(mobileAgent[i]) > -1) {
        isMobile = true;
        break;
      }
    }
    return isMobile;
  }

  public static boolean isNumberCheck(String s) {
    try {
      Double.parseDouble(s);
      return true; } catch (NumberFormatException e) {
    }
    return false;
  }

  public static boolean exists(String fileName)
  {
    boolean res = false;
    try {
      File f = new File(fileName);
      if (f.exists()) res = true; 
    }
    catch (Exception e)
    {
      res = true;
    }
    return res;
  }

  public static boolean isEmpty(Object obj)
  {
    if ((obj instanceof String)) return (obj == null) || ("".equals(obj.toString().trim()));
    if ((obj instanceof List)) return (obj == null) || (((List)obj).isEmpty());
    if ((obj instanceof Map)) return (obj == null) || (((Map)obj).isEmpty());
    if ((obj instanceof Object[])) return (obj == null) || (Array.getLength(obj) == 0);
    return obj == null;
  }

  public static boolean isNotEmpty(String s)
  {
    return !isEmpty(s);
  }

  public static boolean isEquals(String s, String t)
  {
    return checkNull(s).equals(t);
  }

  public static boolean emailValidate(String emailStr)
  {
    Matcher matcher = Constants.VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
    return matcher.find();
  }

  public static byte[] toByte(String str) {
    char[] chs = str.toCharArray();
    byte[] bytes = new byte[chs.length];
    for (int i = 0; i < chs.length; i++)
      bytes[i] = (byte)chs[i];
    return bytes;
  }

  public static String toStr(byte[] bytes) {
    char[] chars = new char[bytes.length];
    for (int i = 0; i < bytes.length; i++)
      chars[i] = (char)bytes[i];
    return String.valueOf(chars);
  }

  public static String getDownFilePath(ServletContext application, String dir)
  {
    String str = application.getRealPath(dir);

    if (str != null) {
      str = replace(str, "\\", "/");
    }
    return str;
  }

  public static int getDateTerm(String date1, String date2)
  {
    int ret = 0;
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      Date dt1 = sdf.parse(date1);
      Date dt2 = sdf.parse(date2);

      long diff = dt2.getTime() - dt1.getTime();
      ret = (int)(diff / 24L / 60L / 60L / 1000L);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return ret;
  }

  public static float round(float f, int len, int round_type)
  {
    float retval = 0.0F;
    try
    {
      retval = new BigDecimal(f).setScale(len, round_type).floatValue();
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    return retval;
  }

  public static float division(float son, float mother)
  {
    float retval = 0.0F;

    if (mother == 0.0F)
    {
      retval = son;
    }
    else
    {
      retval = son / mother;
    }

    return retval;
  }

  public static String toEN(String ko)
  {
    String new_str = null;
    try
    {
      if (ko != null) {
        new_str = new String(ko.getBytes("KSC5601"), "8859_1");
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }

    return new_str;
  }

  public static String toKSC(String en)
  {
    String new_str = null;
    try
    {
      if (en != null) {
        new_str = new String(en.getBytes("8859_1"), "KSC5601");
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }

    return new_str;
  }

  public static String toKSC2(String en)
  {
    String new_str = null;
    try
    {
      if (en != null)
        new_str = new String(en.getBytes("8859_1"), "KSC5601");
      else {
        new_str = "";
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }

    return new_str;
  }

  public static String spaceToNBSP(String source)
  {
    StringBuffer sb = new StringBuffer(source);
    StringBuffer result = new StringBuffer();
    String ch = null;

    for (int i = 0; i < source.length(); i++)
    {
      if (Character.isSpaceChar(sb.charAt(i)))
        ch = "&nbsp;";
      else {
        ch = String.valueOf(sb.charAt(i));
      }
      result.append(ch);
    }

    return result.toString();
  }

  public static String spaceToPercent(String source)
  {
    StringBuffer sb = new StringBuffer(source);
    StringBuffer result = new StringBuffer();
    String ch = null;

    for (int i = 0; i < source.length(); i++)
    {
      if (Character.isSpaceChar(sb.charAt(i)))
        ch = "$";
      else {
        ch = String.valueOf(sb.charAt(i));
      }
      result.append(ch);
    }

    return result.toString();
  }

  public static boolean spaceToCheck(String source)
  {
    StringBuffer sb = new StringBuffer(source);
    boolean strCheck = false;

    for (int i = 0; i < source.length(); i++)
    {
      if (Character.isSpaceChar(sb.charAt(i))) {
        strCheck = true;
        break;
      }
    }

    return strCheck;
  }

  public static String dateFormat(String format)
  {
    String date = null;
    try
    {
      TimeZone tz = new SimpleTimeZone(32400000, "KST");
      TimeZone.setDefault(tz);
      Date d = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      date = sdf.format(d);
    }
    catch (Exception localException)
    {
    }

    return date;
  }

  public static String getDate()
  {
    String ch = null;
    try
    {
      TimeZone tz = new SimpleTimeZone(32400000, "KST");
      TimeZone.setDefault(tz);
      Date d = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd");
      ch = sdf.format(d);
    }
    catch (Exception localException)
    {
    }

    return ch;
  }

  public static String getYyyyMm()
  {
    String ch = null;
    try
    {
      TimeZone tz = new SimpleTimeZone(32400000, "KST");
      TimeZone.setDefault(tz);
      Date d = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM");
      ch = sdf.format(d);
    }
    catch (Exception localException)
    {
    }

    return ch;
  }

  public static String getDate4()
  {
    String ch = null;
    try
    {
      TimeZone tz = new SimpleTimeZone(32400000, "KST");
      TimeZone.setDefault(tz);
      Date d = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy'.'MM'.'dd");
      ch = sdf.format(d);
    }
    catch (Exception localException)
    {
    }

    return ch;
  }

  public static String getDate1()
  {
    String ch = null;
    try
    {
      TimeZone tz = new SimpleTimeZone(32400000, "KST");
      TimeZone.setDefault(tz);
      Date d = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
      ch = sdf.format(d);
    }
    catch (Exception localException)
    {
    }
    return ch;
  }

  public static String getDate3()
  {
    String ch = null;
    try
    {
      TimeZone tz = new SimpleTimeZone(32400000, "KST");
      TimeZone.setDefault(tz);
      Date d = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSSS");
      ch = sdf.format(d);
    }
    catch (Exception localException)
    {
    }
    return ch;
  }

  public static String dateToString(Date d)
  {
    String ch = null;
    try
    {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd");
      ch = sdf.format(d);
    }
    catch (Exception localException)
    {
    }

    return ch;
  }

  public static String dateToString(Date d, String format)
  {
    String ch = null;
    try
    {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      ch = sdf.format(d);
    }
    catch (Exception localException)
    {
    }

    return ch;
  }

  public static Date stringToDate(String d, String format)
  {
    Date ch = null;
    try
    {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      ch = sdf.parse(d);
    }
    catch (Exception localException)
    {
    }
    return ch;
  }

  public static Date dateFormat(Date d, String format)
  {
    String str = dateToString(d, format);
    return stringToDate(str, format);
  }

  public static String stringToDateString(String d, String format)
  {
    if ((d == null) || (d.length() < 6)) return "";

    if (d.length() < 7) {
      d = d.substring(0, 4) + "-" + d.substring(4, 6) + "-" + d.substring(6);
    }

    return dateToString(stringToDate(d, "yyyy'-'MM'-'dd"), format);
  }

  public static String getTime()
  {
    String ch = null;
    try
    {
      TimeZone tz = new SimpleTimeZone(32400000, "KST");
      TimeZone.setDefault(tz);
      Date d = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd'-'HH'-'mm'-'ss");
      ch = sdf.format(d);
    }
    catch (Exception localException)
    {
    }

    return ch;
  }

  public static String getTime1()
  {
    String ch = null;
    try
    {
      TimeZone tz = new SimpleTimeZone(32400000, "KST");
      TimeZone.setDefault(tz);
      Date d = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd' 'HH':'mm':'ss");
      ch = sdf.format(d);
    }
    catch (Exception localException)
    {
    }

    return ch;
  }

  public static String getTimeString()
  {
    String ch = null;
    try
    {
      TimeZone tz = new SimpleTimeZone(32400000, "KST");
      TimeZone.setDefault(tz);
      Date d = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
      ch = sdf.format(d);
    }
    catch (Exception localException)
    {
    }
    return ch;
  }

  public static String getDateString()
  {
    String ch = null;
    try {
      TimeZone tz = new SimpleTimeZone(32400000, "KST");
      TimeZone.setDefault(tz);
      Date d = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      ch = sdf.format(d); } catch (Exception localException) {
    }
    return ch;
  }

  public static String getDate(int type)
  {
    String ch = getDate();
    String format = null;
    switch (type) {
    case 1:
      format = ch.substring(0, 4);
      break;
    case 2:
      format = ch.substring(5, 7);
      break;
    case 4:
      format = ch.substring(0, 4) + ch.substring(5, 7) + ch.substring(8, 10);
      break;
    case 3:
    default:
      format = ch.substring(8, 10);
    }

    return format;
  }

  public static String decodeURL(String s)
  {
    ByteArrayOutputStream out = new ByteArrayOutputStream(s.length());
    for (int i = 0; i < s.length(); i++) {
      int c = s.charAt(i);
      if (c == 43) {
        out.write(32);
      } else if (c == 37) {
        i++; int c1 = Character.digit(s.charAt(i), 16);
        i++; int c2 = Character.digit(s.charAt(i), 16);
        out.write((char)(c1 * 16 + c2));
      } else {
        out.write(c);
      }
    }
    return out.toString();
  }

  public static int parseInt(String str)
  {
    int parseInt = 0;
    try {
      parseInt = Integer.parseInt(str); } catch (Exception localException) {
    }
    return parseInt;
  }

  public static float parseFloat(String str)
  {
    float parseFloat = 0.0F;
    try {
      parseFloat = Float.parseFloat(str); } catch (Exception localException) {
    }
    return parseFloat;
  }

  public static long parseLong(String str)
  {
    long parseLong = 0L;
    try {
      parseLong = Long.parseLong(str); } catch (Exception localException) {
    }
    return parseLong;
  }

  public static int parseInt(String str, int def)
  {
    int parseInt = 0;
    try {
      parseInt = Integer.parseInt(str); } catch (Exception nf) {
      parseInt = def;
    }return parseInt;
  }

  public static double parseDouble(String str)
  {
    double parseDouble = 0.0D;
    try {
      parseDouble = Double.parseDouble(str); } catch (Exception localException) {
    }
    return parseDouble;
  }

  public static String checkNull(String key)
  {
    String value = key;
    if (key == null)
      value = "";
    return value;
  }

  public static String parseDate(String date, int type)
  {
    String parse = "";
    if ((date != null) && (date.length() == 8)) {
      switch (type) {
      case 1:
        parse = date.substring(0, 4);
        break;
      case 2:
        parse = date.substring(4, 6);
        break;
      default:
        parse = date.substring(6, 8);
      }
    }

    return parse;
  }

  public static String parseDateYm(String date)
  {
    String parse = "";

    if ((date != null) && (date.length() >= 10)) {
      parse = date.substring(0, 4) + "-" + date.substring(5, 7);
    }

    return parse;
  }

  public static String parseDateYmd(String date)
  {
    String parse = "";

    if ((date != null) && (date.length() >= 10))
    {
      parse = date.substring(0, 4) + "년 " + date.substring(5, 7) + "월 " + date.substring(8, 10) + "일";
    }

    return parse;
  }

  public static String phoneFormat(String phoneNo)
  {
    if (phoneNo.length() == 0) {
      return phoneNo;
    }

    String strTel = phoneNo.replace("-", "");

    String[] strDDD = { "02", "031", "032", "033", "041", "042", "043", 
      "051", "052", "053", "054", "055", "061", "062", 
      "063", "064", "010", "011", "012", "013", "015", 
      "016", "017", "018", "019", "070" };

    if (strTel.length() < 9)
    {
      return phoneNo;
    }
    if (strTel.substring(0, 2).equals(strDDD[0]))
      strTel = strTel.substring(0, 2) + '-' + strTel.substring(2, strTel.length() - 4) + 
        '-' + strTel.substring(strTel.length() - 4, strTel.length());
    else {
      for (int i = 1; i < strDDD.length; i++) {
        if (strTel.substring(0, 3).equals(strDDD[i])) {
          strTel = strTel.substring(0, 3) + '-' + strTel.substring(3, strTel.length() - 4) + 
            '-' + strTel.substring(strTel.length() - 4, strTel.length());
        }
      }
    }
    return strTel;
  }

  public static String parseYYMM(String date, String delim)
  {
    String parse = null;
    if ((date != null) && (date.length() == 8)) {
      parse = date.substring(4, 6) + delim + date.substring(6, 8);
    }
    return parse;
  }

  public static String nbspToSpace(String nbsp)
  {
    String value = "";
    if ((nbsp != null) && (!nbsp.trim().equals("&nbsp;"))) {
      value = nbsp;
    }
    return value;
  }

  public static String nullToString(String str)
  {
    String value = str;
    if (str == null) {
      value = "";
    }
    return value;
  }

  public static String nullToString(String str, String rtnVal)
  {
    String value = str;
    if (str == null) {
      value = nullToString(rtnVal);
    }
    return value;
  }

  public static String emptyCk(String getData)
  {
    if (getData == null) return "";
    return getData;
  }

  public static String emptyCk(String getData, String str)
  {
    if (emptyCk(getData).equals("")) {
      getData = str;
    }
    return getData;
  }

  public static String nullToNbsp(String str)
  {
    String value = str;
    if ((str == null) || (str.equals(""))) {
      value = "&nbsp;";
    }
    return value;
  }

  public static int getPageCount(int token, int allPage)
  {
    int lastPage = (allPage - 1) / token + 1;
    return lastPage;
  }

  public static int getDataNum(int token, int page, int allPage)
  {
    if (allPage <= token) {
      return allPage;
    }
    int num = allPage - token * page + token;
    return num;
  }

  public static int getAscDataNum(int token, int page, int allPage)
  {
    if (allPage <= token) {
      return allPage;
    }
    int num = token * page - token + 1;
    return num;
  }

  public static int countSubstring(String src, String sub)
  {
    int count = 0;
    int index = 0;

    while ((index = src.indexOf(sub, index)) > 0) {
      count++;
      index += sub.length();
    }

    return count;
  }

  public static String levelCount(int level)
  {
    StringBuffer sb = new StringBuffer("");
    int p = 0;
    for (int i = 1; i <= level; i++) {
      sb.append("&nbsp;&nbsp;");
      if (2 * i == level) {
        p = i;
      }
    }
    if (level == 1) {
      sb.append("<img src='/img/bbs/bbs_top.gif'>");
    } else {
      sb.append("<img src='/img/bbs/bbs_elbow.gif'>");
      sb.append("<img src='/img/bbs/bbs_re" + p % 2 + ".gif'>");
    }
    return sb.toString();
  }

  public static String levelCountSe(int level) {
    StringBuffer sb = new StringBuffer("");
    for (int i = 1; i <= level; i++) {
      sb.append("&nbsp;&nbsp;");
    }
    return sb.toString();
  }

  public static String getString(String line, String def)
  {
    if ((line == null) || ("".equals(line)))
      return def;
    return line;
  }

  public static String getNull(String result)
  {
    String str = result;
    if ((result == null) || (result.indexOf("") == -1)) {
      return "";
    }

    return str;
  }

  public static boolean RyuBetweenDate(String time1, String time2)
  {
    String ch = null;
    try
    {
      TimeZone tz = new SimpleTimeZone(32400000, "KST");
      TimeZone.setDefault(tz);
      Date d = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      ch = sdf.format(d);
    }
    catch (Exception localException) {
    }
    return (parseInt(time1) <= parseInt(ch)) && (parseInt(ch) <= parseInt(time2));
  }

  public static boolean RyuAfterDate(String time1)
  {
    String ch = null;
    try
    {
      TimeZone tz = new SimpleTimeZone(32400000, "KST");
      TimeZone.setDefault(tz);
      Date d = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      ch = sdf.format(d);
    }
    catch (Exception localException) {
    }
    return parseInt(time1) <= parseInt(ch);
  }

  public static boolean RyuBeforeDate(String time1)
  {
    String ch = null;
    try
    {
      TimeZone tz = new SimpleTimeZone(32400000, "KST");
      TimeZone.setDefault(tz);

      Date d = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      ch = sdf.format(d);
    }
    catch (Exception localException)
    {
    }

    return parseInt(ch) <= parseInt(time1);
  }

  public static String reqTime()
  {
    String ch = null;
    try {
      TimeZone tz = new SimpleTimeZone(32400000, "KST");
      TimeZone.setDefault(tz);
      Date d = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd' 'HH");
      ch = sdf.format(d); } catch (Exception localException) {
    }
    return ch;
  }

  public static boolean betweenDateTime(String first, String second)
  {
    boolean flag = false;

    long start = 0L;
    long end = 0L;
    long current = 0L;

    DateFormat format = new SimpleDateFormat("yyyy'-'MM'-'dd' 'HH':'mm':'ss");
    try
    {
      start = format.parse(first).getTime();
      end = format.parse(second).getTime();
      current = format.parse(getTime1()).getTime();
    }
    catch (Exception pe) {
      return false;
    }

    if ((current >= start) && (current <= end)) {
      flag = true;
    }

    return flag;
  }

  public static boolean beforeNotEqualDateTime(String first)
  {
    boolean flag = false;
    long currentTime = 0L;
    long firstTime = 0L;
    DateFormat format = new SimpleDateFormat("yyyy'-'MM'-'dd' 'HH':'mm':'ss");
    try
    {
      currentTime = format.parse(getTime1()).getTime();
      firstTime = format.parse(first).getTime();
    }
    catch (Exception pe) {
      System.err.println("afterDate 에러입니다." + pe.getMessage());
    }

    if (firstTime > currentTime) {
      flag = true;
    }

    return flag;
  }

  public static boolean betweenDate(String first, String second)
  {
    boolean flag = false;
    long start = 0L;
    long end = 0L;
    long current = 0L;

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    try
    {
      start = format.parse(first).getTime();
      end = format.parse(second).getTime();
      current = format.parse(getDate()).getTime();
    }
    catch (Exception pe) {
      return false;
    }

    if ((current >= start) && (current <= end)) {
      flag = true;
    }

    return flag;
  }

  public static boolean afterDate(String second)
  {
    boolean flag = false;
    long currentTime = 0L;
    long secondTime = 0L;
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    try
    {
      currentTime = format.parse(getDate()).getTime();
      secondTime = format.parse(second).getTime();
    }
    catch (Exception pe) {
      System.err.println("afterDate 에러입니다." + pe.getMessage());
    }

    if (secondTime <= currentTime) {
      flag = true;
    }

    return flag;
  }

  public static boolean beforeDate(String first)
  {
    boolean flag = false;
    long currentTime = 0L;
    long firstTime = 0L;
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    try
    {
      currentTime = format.parse(getDate()).getTime();
      firstTime = format.parse(first).getTime();
    } catch (Exception pe) {
      System.err.println("afterDate 에러입니다." + pe.getMessage());
    }

    if (firstTime >= currentTime) {
      flag = true;
    }
    return flag;
  }

  public static boolean afterNotEqualDate(String second)
  {
    boolean flag = false;

    long currentTime = 0L;
    long secondTime = 0L;
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    try
    {
      currentTime = format.parse(getDate()).getTime();

      secondTime = format.parse(second).getTime();
    }
    catch (Exception pe) {
      System.err.println("afterDate 에러입니다." + pe.getMessage());
    }

    if (secondTime < currentTime) {
      flag = true;
    }
    return flag;
  }

  public static boolean beforeNotEqualDate(String first)
  {
    boolean flag = false;
    long currentTime = 0L;
    long firstTime = 0L;
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    try
    {
      currentTime = format.parse(getDate()).getTime();
      firstTime = format.parse(first).getTime();
    } catch (Exception pe) {
      System.err.println("afterDate 에러입니다." + pe.getMessage());
    }

    if (firstTime > currentTime) {
      flag = true;
    }

    return flag;
  }

  public static String parseDecimal(double unFormat)
  {
    DecimalFormat df = new DecimalFormat("###,###.##");
    String format = df.format(unFormat);
    return format;
  }

  public static String parseDecimal(double unFormat, String foramt)
  {
    DecimalFormat df = new DecimalFormat(foramt);
    String format = df.format(unFormat);
    return format;
  }

  public static Object clone(Object object)
  {
    Class c = object.getClass();
    Object newObject = null;
    try {
      newObject = c.newInstance();
    } catch (Exception e) {
      return null;
    }

    Field[] field = c.getFields();
    for (int i = 0; i < field.length; i++)
      try {
        Object f = field[i].get(object);
        field[i].set(newObject, f);
      } catch (Exception localException1) {
      }
    return newObject;
  }

  public static String getStackTrace(Throwable e)
  {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    PrintWriter writer = new PrintWriter(bos);
    e.printStackTrace(writer);
    writer.flush();
    return bos.toString();
  }

  public static String replace(String line, String oldString, String newString)
  {
    int index = 0;
    while ((index = line.indexOf(oldString, index)) >= 0) {
      line = line.substring(0, index) + newString + line.substring(index + oldString.length());
      index += newString.length();
    }
    return line;
  }

  public static String substring(String str, int start, int end)
  {
    String val = null;
    try {
      val = str.substring(start, end);
    } catch (Exception e) {
      return "";
    }
    return val;
  }

  public static final String substring(String source, int length, String suffix)
  {
    if ((source == null) || (source.trim().equals("")) || (length == 0)) {
      return source;
    }

    StringBuffer result = new StringBuffer();
    int cnt = 0;

    for (int i = 0; i < source.length(); i++)
    {
      if (source.charAt(i) > '')
        cnt += 2;
      else {
        cnt++;
      }
      if (cnt <= length) {
        result.append(source.charAt(i));
      }
    }
    if (cnt > length) {
      result.append(suffix);
    }

    return result.toString();
  }

  public static String[] getItemArray(String src)
  {
    String[] retVal = null;
    if (src.length() == 0) return null;

    int nitem = 1;

    for (int i = 0; i < src.length(); i++) {
      if (src.charAt(i) != ',') continue; nitem++;
    }
    retVal = new String[nitem];

    int ep = 0;
    int sp = 0;

    for (int i = 0; i < nitem; i++) {
      ep = src.indexOf(",", sp);
      if (ep == -1) ep = src.length();
      retVal[i] = new String(src.substring(sp, ep));
      sp = ep + 1;
    }

    return retVal;
  }

  public static String[] getItemArray(String src, char parser)
  {
    String[] retVal = null;
    if (src.length() == 0) return null;

    int nitem = 1;

    for (int i = 0; i < src.length(); i++) {
      if (src.charAt(i) != parser) continue; nitem++;
    }
    retVal = new String[nitem];

    int ep = 0;
    int sp = 0;

    for (int i = 0; i < nitem; i++) {
      ep = src.indexOf(parser, sp);
      if (ep == -1) ep = src.length();
      retVal[i] = new String(src.substring(sp, ep));
      sp = ep + 1;
    }

    return retVal;
  }

  public static String returnDate(String date)
  {
    if (date == null)
      return "";
    if (date.length() < 8) {
      return date;
    }

    String year = date.substring(0, 4);
    String month = date.substring(4, 6);
    String day = date.substring(6, 8);

    return year + "/" + month + "/" + day;
  }

  public static boolean checkEmbolism(int year)
  {
    int remain = 0;
    int remain_1 = 0;
    int remain_2 = 0;

    remain = year % 4;
    remain_1 = year % 100;
    remain_2 = year % 400;

    if (remain == 0)
    {
      if (remain_1 == 0)
      {
        return remain_2 == 0;
      }

      return true;
    }

    return false;
  }

  public static int getMonthDate(int year, int month)
  {
    int[] dateMonth = new int[12];

    dateMonth[0] = 31;
    dateMonth[1] = 28;
    dateMonth[2] = 31;
    dateMonth[3] = 30;
    dateMonth[4] = 31;
    dateMonth[5] = 30;
    dateMonth[6] = 31;
    dateMonth[7] = 31;
    dateMonth[8] = 30;
    dateMonth[9] = 31;
    dateMonth[10] = 30;
    dateMonth[11] = 31;

    if (checkEmbolism(year)) dateMonth[1] = 29;

    return dateMonth[(month - 1)];
  }

  public static String addZero(String str)
  {
    return Integer.toString(Integer.parseInt(str) + 100).substring(1, 3);
  }

  public static String addZero2(int num)
  {
    return Integer.toString(num + 100).substring(1, 3);
  }

  public static void DeleteFile(String path)
  {
    File f = new File(path);
    f.delete();
  }
/*
  public static String[] GetTodayString()
  {
    SimpleTimeZone kst = new SimpleTimeZone(32400000, "KST");
    Calendar cal = Calendar.getInstance(kst);

    String[] today = new String[3];
    today[0] = "";
    today[1] = "";
    today[0] = "";

    int year = cal.get(1);
    today[0] = Integer.toString(year);

    if (cal.get(2) + 1 < 10)
      today[1] = ("0" + (cal.get(2) + 1));
    else today[1] = (cal.get(2) + 1);

    if (cal.get(5) < 10)
      today[2] = ("0" + cal.get(5));
    else today[2] = cal.get(5);

    return today;
  }
*/
  public static String GetNTodayString()
  {
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm");
    String strStodayt = sdf.format(date);

    return strStodayt;
  }

  public static String toDateYYYMMDD2(String date)
  {
    Date d = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
    return df.format(d);
  }

  public static int[] GetTodayInt()
  {
    SimpleTimeZone kst = new SimpleTimeZone(32400000, "KST");
    Calendar cal = Calendar.getInstance(kst);

    int[] today = new int[3];

    today[0] = cal.get(1);
    today[1] = (cal.get(2) + 1);
    today[2] = cal.get(5);

    return today;
  }

  public static String makeDateString(String date)
  {
    if (date.length() == 8) {
      return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6);
    }

    return date;
  }

  public static String makeDateString1(String date)
  {
    if (date.length() > 10) return date;
    return date.substring(0, 4) + date.substring(5, 7) + date.substring(8, 10);
  }

  public static String courseDepth(int level, String identifier_flag)
  {
    StringBuffer sb = new StringBuffer("");

    int p = 0;
    for (int i = 3; i <= level; i++) {
      sb.append("&nbsp;&nbsp;");
      if (2 * i == level) {
        p = i;
      }
    }
    if (identifier_flag.equals("O"))
      sb.append("<img src='/img/bbs/lesson.gif'>");
    else if (identifier_flag.equals("I")) {
      sb.append("<img src='/img/bbs/unit.gif'>");
    }
    return sb.toString();
  }

  public static String rtnExt1(String str)
  {
    String[] tmp = new String[2];
    String ExtStr = "";

    StringTokenizer token = new StringTokenizer(str, ".");

    if (token.countTokens() == 2) {
      for (int i = 0; token.hasMoreElements(); i++) {
        tmp[i] = token.nextToken();
      }

      if ("txt".equals(tmp[1]))
        ExtStr = "<img src=/img/file/text.gif border=0>";
      else if ("rar".equals(tmp[1]))
        ExtStr = "<img src=/img/file/compressed.gif border=0>";
      else if ("zip".equals(tmp[1]))
        ExtStr = "<img src=/img/file/compressed.gif border=0>";
      else if ("alz".equals(tmp[1]))
        ExtStr = "<img src=/img/file/compressed.gif border=0>";
      else if ("xls".equals(tmp[1]))
        ExtStr = "<img src=/img/file/excel.gif border=0>";
      else if ("exe".equals(tmp[1]))
        ExtStr = "<img src=/img/file/exe.gif border=0>";
      else if ("html".equals(tmp[1]))
        ExtStr = "<img src=/img/file/html.gif border=0>";
      else if ("hwp".equals(tmp[1]))
        ExtStr = "<img src=/img/file/hwp.gif border=0>";
      else if ("gif".equals(tmp[1]))
        ExtStr = "<img src=/img/file/image.gif border=0>";
      else if ("jpg".equals(tmp[1]))
        ExtStr = "<img src=/img/file/image.gif border=0>";
      else if ("png".equals(tmp[1]))
        ExtStr = "<img src=/img/file/image.gif border=0>";
      else if ("bmp".equals(tmp[1]))
        ExtStr = "<img src=/img/file/image.gif border=0>";
      else if ("avi".equals(tmp[1]))
        ExtStr = "<img src=/img/file/movie.gif border=0>";
      else if ("mpg".equals(tmp[1]))
        ExtStr = "<img src=/img/file/movie.gif border=0>";
      else if ("wma".equals(tmp[1]))
        ExtStr = "<img src=/img/file/mp3.gif border=0>";
      else if ("mp3".equals(tmp[1]))
        ExtStr = "<img src=/img/file/mp3.gif border=0>";
      else if ("ppt".equals(tmp[1]))
        ExtStr = "<img src=/img/file/ppt.gif border=0>";
      else if ("ra".equals(tmp[1]))
        ExtStr = "<img src=/img/file/ra.gif border=0>";
      else if ("rlt".equals(tmp[1]))
        ExtStr = "<img src=/img/file/rlt.gif border=0>";
      else if ("doc".equals(tmp[1]))
        ExtStr = "<img src=/img/file/word.gif border=0>";
      else if ("pdf".equals(tmp[1]))
        ExtStr = "<img src=/img/file/pdf.gif border=0>";
      else {
        ExtStr = "<img src=/img/file/default.gif border=0>";
      }
    }
    return ExtStr;
  }

  public static String CutStr(String str, int len, String tail)
  {
    if (str.length() <= len) {
      return str;
    }
    StringCharacterIterator sci = new StringCharacterIterator(str);
    StringBuffer buffer = new StringBuffer();
    buffer.append(sci.first());
    for (int i = 1; i < len; i++) {
      if (i < len - 1) {
        buffer.append(sci.next());
      } else {
        char c = sci.next();
        if (c != ' ') {
          buffer.append(c);
        }
      }
    }
    buffer.append(tail);
    return buffer.toString();
  }
/*
  public static String getPlusDay(String syear, String smon, String sday, int daydiff)
  {
    SimpleTimeZone kst = new SimpleTimeZone(32400000, "KST");
    Calendar calendar = Calendar.getInstance(kst);
    calendar.set(Integer.parseInt(syear), Integer.parseInt(smon) - 1, Integer.parseInt(sday));

    calendar.add(5, daydiff);

    String y = calendar.get(1);
    String m;
    String m;
    if (calendar.get(2) < 10)
      m = "0" + (calendar.get(2) + 1);
    else m = calendar.get(2) + 1;
    String d;
    String d;
    if (calendar.get(5) < 10)
      d = "0" + calendar.get(5);
    else d = calendar.get(5);

    return y + m + d;
  }

  public static String getDate2()
  {
    SimpleTimeZone kst = new SimpleTimeZone(32400000, "KST");
    Calendar cal = Calendar.getInstance(kst);

    String y = cal.get(1);
    String m;
    String m;
    if (cal.get(2) + 1 < 10)
      m = "0" + (cal.get(2) + 1);
    else m = cal.get(2) + 1;
    String d;
    String d;
    if (cal.get(5) < 10)
      d = "0" + cal.get(5);
    else d = cal.get(5);

    return y + m + d;
  }
*/
  public static String stringCutHtmlTag(String comment, int len)
  {
    int length = 0;
    String start = "N";
    String end = "N";
    StringBuffer buffer = new StringBuffer();

    for (int i = 0; i < comment.length(); i++) {
      String comp = comment.substring(i, i + 1);

      if ("<".compareTo(comp) == 0) {
        start = "Y";
        buffer.append(comp);
      }
      else
      {
        if (("Y".equals(start)) && ("N".equals(end))) {
          buffer.append(comp);
        } else {
          length += 2;
          buffer.append(comp);
          if (length > len) {
            buffer.append("....");
            return buffer.toString();
          }
        }

        if (">".compareTo(comp) == 0) {
          start = "N";
          end = "N";
        }
      }
    }
    return buffer.toString();
  }

  public static String toDateYMD(String date)
  {
    Date d = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yy/MM/dd");
    return df.format(d);
  }

  public static String toDateYYYMMDD(String date)
  {
    Date d = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    return df.format(d);
  }

  public static String toDateYYYMMDD(Date date) {
    Date d = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    return df.format(d);
  }

  public static String getDateStringAddPeriod(String date)
  {
    String rtnVal = "";

    if (date.length() >= 8) {
      rtnVal = date.substring(0, 4) + "." + date.substring(4, 6) + "." + date.substring(6, 8);
    }

    return rtnVal;
  }

  public static String getFileImage(String filename)
  {
    String icon_str = "/assets/images/icon/";
    String re_str = "";
    String strResult = "";
    if ((filename != null) && (!filename.equals("")))
    {
      if ((filename.endsWith(".hwp")) || (filename.endsWith(".HWP")))
        re_str = "file_hwp.png";
      else if ((filename.endsWith(".doc")) || (filename.endsWith(".DOC")))
        re_str = "file_doc.png";
      else if ((filename.endsWith(".xls")) || (filename.endsWith(".XLS")) || (filename.endsWith(".xlsx")) || (filename.endsWith(".XLSX")))
        re_str = "file_xls.png";
      else if ((filename.endsWith(".ppt")) || (filename.endsWith(".PPT")) || (filename.endsWith(".pptx")) || (filename.endsWith(".PPTX")))
        re_str = "file_ppt.png";
      else if ((filename.endsWith(".pdf")) || (filename.endsWith(".PDF")))
        re_str = "file_pdf.png";
      else if ((filename.endsWith(".zip")) || (filename.endsWith(".ZIP")))
        re_str = "file_zip.png";
      else if ((filename.endsWith(".jpg")) || (filename.endsWith(".JPG")) || (filename.endsWith(".jpge")) || (filename.endsWith(".JPGE")))
        re_str = "file_jpg.png";
      else if ((filename.endsWith(".png")) || (filename.endsWith(".PNG")))
        re_str = "file_png.png";
      else if ((filename.endsWith(".gif")) || (filename.endsWith(".GIF")))
        re_str = "file_gif.png";
      else if ((filename.endsWith(".xml")) || (filename.endsWith(".XML")) || (filename.endsWith(".html")) || (filename.endsWith(".htm")))
        re_str = "file_xml.png";
      else {
        re_str = "file_txt.png";
      }

      strResult = icon_str + re_str;
    }

    return strResult;
  }

  public static String getExtension(String fileStr)
  {
    return fileStr.substring(fileStr.lastIndexOf(".") + 1, fileStr.length());
  }

  public static String getStrToPasswordType(String dat)
  {
    String tmp = "";
    String passStr = "";

    if (dat.length() < 3)
      passStr = "*";
    else if (dat.length() > 3)
      passStr = "***";
    else {
      passStr = "**";
    }

    tmp = substring(dat, 0, 1) + passStr;
    return tmp;
  }

  public static String trim(String str)
  {
    return str.replaceAll("[ ]+", "");
  }

  public static String StringReplace(String str)
  {
    String match = "[^가-힣xfe0-9a-zA-Z@.\\s-]";
    str = str.replaceAll(match, " ");
    return str;
  }

  public static boolean isSqlCheckSpecialCharacter(String str) {
    String a = "";
    if (Fn.isEmpty(str)) {
      return false;
    }

    str = str.toUpperCase();
    String[] strArr = str.split(" ");
    String[] blacklist = { "CHAR", "NCAHAR", "VARCHAR", "NVARCHAR", "ALTER", 
      "BEGIN", "CAST", "CREATE", "CURSOR", "DECLARE", 
      "DELETE", "DROP", "END", "EXEC", "EXECUTE", 
      "FETCH", "INSERT", "KILL", "OPEN", "SELECT", 
      "SYS", "SYSOBJECTS", "SYSCOLUMNS", "TABLE", "UPDATE" };

    boolean result = false;
    for (String strValue : strArr) {
      if (!result) {
        for (String check : blacklist) {
          if (Fn.isEqual(strValue, check)) {
            result = true;
            break;
          }
        }
      }
    }

    if (result) {
      return true;
    }
    String match = "[^가-힣xfe0-9a-zA-Z\\s_]";
    str = str.replaceAll(" ", "");
    a = str.replaceAll(match, "");

    return str.length() != a.length();
  }

  public static String StringReplaceSpecialCharacter(String str)
  {
    if (Fn.isEmpty(str)) {
      return "";
    }

    str = str.toUpperCase();
    String[] strArr = str.split(" ");
    String[] blacklist = { "CHAR", "NCAHAR", "VARCHAR", "NVARCHAR", "ALTER", 
      "BEGIN", "CAST", "CREATE", "CURSOR", "DECLARE", 
      "DELETE", "DROP", "END", "EXEC", "EXECUTE", 
      "FETCH", "INSERT", "KILL", "OPEN", "SELECT", 
      "SYS", "SYSOBJECTS", "SYSCOLUMNS", "TABLE", "UPDATE" };

    String resultstr = "";
    for (String strValue : strArr) {
      for (String check : blacklist) {
        if ((!Fn.isEmpty(strValue)) && (Fn.isEqual(strValue, check))) {
          strValue = "";
        }
      }

      resultstr = resultstr + strValue;
    }

    String match = "[^가-힣xfe0-9a-zA-Z\\s_]";
    resultstr = resultstr.replaceAll(match, " ");

    return resultstr;
  }

  public static String continueSpaceRemove(String str) {
    String match2 = "\\s{2,}";
    str = str.replaceAll(match2, " ");
    return str;
  }

  public static String allReplace(String strAll, String strSrc, String strDest)
  {
    while (strAll.indexOf(strSrc) != -1) {
      strAll = strAll.substring(0, strAll.indexOf(strSrc)) + strDest + strAll.substring(strAll.indexOf(strSrc) + strSrc.length(), strAll.length());
    }
    return strAll;
  }

  public static String htmlConvert(String strAll)
  {
    strAll = allReplace(strAll, "&amp;", "&");
    strAll = allReplace(strAll, "&lt;", "<");
    strAll = allReplace(strAll, "&gt;", ">");
    return strAll;
  }

  public static String htmlBR(String comment)
  {
    if (comment == null) return "&nbsp;";
    int length = comment.length();
    StringBuffer buffer = new StringBuffer();

    for (int i = 0; i < length; i++)
    {
      String comp = comment.substring(i, i + 1);

      if (("\r".compareTo(comp) == 0) || ("\n".compareTo(comp) == 0))
      {
        if ("\r".compareTo(comp) == 0) {
          i++; comp = comment.substring(i, i + 1);
          if ("\n".compareTo(comp) == 0)
            buffer.append("<BR>\r");
        } else {
          buffer.append("<BR>\r");
        }
      }
      buffer.append(comp);
    }
    return buffer.toString();
  }

  public static boolean isEmail(String email)
  {
    String str = email;
    if (str == "")
      return false;
    int i = str.indexOf("@");
    if (i < 0)
      return false;
    i = str.indexOf(".");

    return i >= 0;
  }

  public static String arrayToString(String[] arrays)
  {
    String rtn = "";

    if ((arrays != null) && (arrays.length > 0)) {
      for (int i = 0; i < arrays.length; i++) {
        if (i == 0)
          rtn = "'" + arrays[i] + "'";
        else {
          rtn = rtn + ",'" + arrays[i] + "'";
        }
      }
    }
    return rtn;
  }

  public static String arrayToStrings(String[] arrays)
  {
    String rtn = "";

    if ((arrays != null) && (arrays.length > 0)) {
      for (int i = 0; i < arrays.length; i++) {
        if (i == 0)
          rtn = arrays[i];
        else {
          rtn = rtn + "|" + arrays[i];
        }
      }
    }
    return rtn;
  }

  public static Object[] getArgs(Class fieldType, String initValue)
  {
    if (initValue != null)
      try {
        if (fieldType == Integer.TYPE)
          return new Object[] { new Integer(initValue) };
        if (fieldType == Long.TYPE)
          return new Object[] { new Long(initValue) };
        if (fieldType == Float.TYPE)
          return new Object[] { new Float(initValue) };
        if (fieldType == Double.TYPE) {
          return new Object[] { new Double(initValue) };
        }
        return new Object[] { initValue };
      }
      catch (Exception localException) {
      }
    return null;
  }

  public static String getUID() {
    String strReturn = "";
    Random ran = new Random();

    for (int i = 0; i < 10; i++) strReturn = strReturn + (char)(ran.nextInt(26) + 65);
    strReturn = strReturn + "-";
    for (int i = 0; i < 10; i++) strReturn = strReturn + (char)(ran.nextInt(26) + 65);
    strReturn = strReturn + "-";
    for (int i = 0; i < 10; i++) strReturn = strReturn + (char)(ran.nextInt(26) + 65);
    strReturn = strReturn + "-";
    for (int i = 0; i < 10; i++) strReturn = strReturn + (char)(ran.nextInt(26) + 65);

    return strReturn;
  }

  public static boolean valiedEmail(String email)
  {
    Pattern p = Pattern.compile("^(?:\\w+\\.?)*[a-zA-Z0-9\\-]+@(?:[a-zA-Z0-9\\-]+\\.)+\\w+$");
    Matcher m = p.matcher(email);
    return m.matches();
  }
/*
  public static boolean valiedId(String id)
  {
    Pattern p = Pattern.compile("^[a-zA-Z0-9,!,@,#,$,%,^,&,*,?,_,~]+$");
    int count = id.length();
    boolean check = false;
    for (int i = 0; i < count; i++) {
      Matcher m = p.matcher(id.charAt(i));
      if (!m.matches()) {
        check = true;
      }
    }
    return check;
  }

  public static boolean valiedPassword(String password)
  {
    Pattern p = Pattern.compile("[a-zA-Z0-9\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\|\\`\\-\\=\\[\\]\\{\\}\\;\\'\\:\\,\\.\\/\\<\\>\\\\?\"]");
    int count = password.length();
    boolean check = false;
    for (int i = 0; i < count; i++) {
      Matcher m = p.matcher(password.charAt(i));
      if (!m.matches()) {
        check = true;
      }
    }
    return check;
  }

  public static boolean valiedHp(String hp)
  {
    Pattern p = Pattern.compile("[\\d]*");
    int count = hp.length();
    boolean check = false;
    for (int i = 0; i < count; i++) {
      Matcher m = p.matcher(hp.charAt(i));
      if (!m.matches()) {
        check = true;
      }
    }
    return check;
  }*/
/*
  public static boolean valiedCharacterJa(String str)
  {
    Pattern p = Pattern.compile("[a-zA-Z0-9，．／￥ －「」（ ）アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲンガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポァィゥェォッャュョヮヵヶ　]");
    int count = str.length();
    boolean check = true;
    for (int i = 0; i < count; i++) {
      Matcher m = p.matcher(str.charAt(i));
      if (!m.matches()) {
        check = false;
      }
    }
    return check;
  }
*/
  public static String addComma(String str)
  {
    StringBuffer sb = new StringBuffer(str);
    StringBuffer rsb = new StringBuffer();
    sb = sb.reverse();
    int p = 0;

    for (int i = 0; i < str.length(); i++) {
      p = i % 3;

      if ((i > 0) && 
        (p == 0)) {
        rsb.append(",");
      }
      rsb.append(sb.substring(i, i + 1));
    }

    return rsb.reverse().toString();
  }
/*
  public static String addComma(int num)
  {
    String str = num;
    StringBuffer sb = new StringBuffer(str);
    StringBuffer rsb = new StringBuffer();
    sb = sb.reverse();
    int p = 0;

    for (int i = 0; i < str.length(); i++) {
      p = i % 3;

      if ((i > 0) && 
        (p == 0)) {
        rsb.append(",");
      }
      rsb.append(sb.substring(i, i + 1));
    }

    return rsb.reverse().toString();
  }
*/
  public static String removeHTML(String str)
  {
    return str.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
  }

  public static String removeTag(String str)
  {
    if (emptyCk(str).equals("")) {
      return "";
    }

    Pattern script = Pattern.compile("<(no)?script[^>]*>.*?</(no)?script>", 32);
    Matcher mat = script.matcher(str);
    str = mat.replaceAll("");

    Pattern style = Pattern.compile("<style[^>]*>.*</style>", 32);
    mat = style.matcher(str);
    str = mat.replaceAll("");

    Pattern tag = Pattern.compile("<(\"[^\"]*\"|'[^']*'|[^'\">])*>");
    mat = tag.matcher(str);
    str = mat.replaceAll("");

    Pattern ntag = Pattern.compile("<\\w+\\s+[^<]*\\s*>");
    mat = ntag.matcher(str);
    str = mat.replaceAll("");

    Pattern Eentity = Pattern.compile("&[^;]+;");
    mat = Eentity.matcher(str);
    str = mat.replaceAll("");

    Pattern wspace = Pattern.compile("\\s\\s+");
    mat = wspace.matcher(str);
    str = mat.replaceAll("");

    return str;
  }

  public static String tempPassword() {
    Random rnd = new Random();

    char[] possibleCharacters = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
    int possibleCharactersLength = possibleCharacters.length;
    int passwordSize = 10;
    StringBuffer passwordBuf = new StringBuffer(10);

    for (int i = 0; i < 10; i++) {
      passwordBuf.append(possibleCharacters[rnd.nextInt(possibleCharactersLength)]);
    }

    return passwordBuf.toString();
  }

  public static String checkNull(String val, String str) {
    if (isEmptyString(val)) {
      return str;
    }
    return val;
  }

  public static boolean isEmptyString(String val)
  {
    return (val == null) || ("".equals(val));
  }

  public static void setCookie(HttpServletResponse response, String name, String value, String path, String secure)
  {
    Cookie cookie = new Cookie(name, value);
    if (secure.equals("true"))
      cookie.setSecure(true);
    else {
      cookie.setSecure(false);
    }
    cookie.setPath(path);
    cookie.setMaxAge(2592000);

    response.addCookie(cookie);
  }

  public static Cookie getCookie(HttpServletRequest request, String name)
  {
    Cookie[] cookies = request.getCookies();
    Cookie returnCookie = null;

    if (cookies == null) {
      return returnCookie;
    }

    for (int i = 0; i < cookies.length; i++) {
      Cookie thisCookie = cookies[i];

      if ((!thisCookie.getName().equals(name)) || 
        (thisCookie.getValue().equals(""))) continue;
      returnCookie = thisCookie;

      break;
    }

    return returnCookie;
  }

  public static void getDelCookie(HttpServletResponse response, HttpServletRequest request, String name, String path, String secure)
  {
    Cookie[] cookies = request.getCookies();

    for (int i = 0; i < cookies.length; i++) {
      Cookie thisCookie = cookies[i];

      if ((!thisCookie.getName().equals(name)) || (thisCookie.getValue().equals("")))
        continue;
      Cookie cookie = new Cookie(name, "");
      cookie.setMaxAge(0);
      if (secure.equals("true"))
        cookie.setSecure(true);
      else {
        cookie.setSecure(false);
      }
      cookie.setPath(path);
      response.addCookie(cookie);

      break;
    }
  }

  public static final String getCommaListInt(String[] cds)
  {
    StringBuffer str = new StringBuffer();
    for (int i = 0; i < cds.length; i++) {
      if (i > 0)
        str.append(",");
      str.append(cds[i]);
    }
    return str.toString();
  }

  public static String voidNull(String param)
  {
    if (param == null)
      return "";
    if (param.trim().equals("")) {
      return "";
    }
    return param.trim();
  }

  public static String getFileSize(long size)
  {
    int s = 0;
    String suffix = "";
    int round = 0;
    while (size / 1024L > 0L) {
      round = Math.round((float)(size % 1024L) / 1024.0F * 10.0F);
      size /= 1024L;
      s++;
    }
    switch (s) {
    case 0:
      suffix = "B";
      break;
    case 1:
      suffix = "KB";
      break;
    case 2:
      suffix = "MB";
      break;
    case 3:
      suffix = "GB";
      break;
    case 4:
      suffix = "TB";
      break;
    default:
      suffix = "B";
    }

    String str = String.valueOf(size);
    if (round > 0)
      str = str + "." + String.valueOf(round);
    str = str + suffix;
    return str;
  }

  public static final String[] split(String str, String chr)
  {
    String[] temp = null;

    if (str == null)
      return new String[0];
    if (str.trim().equals("")) {
      return new String[0];
    }
    Vector buf = new Vector();
    int pos = -1;
    while ((pos = str.indexOf(chr)) > -1) {
      buf.addElement(str.substring(0, pos));
      str = str.substring(pos + 1);
    }
    buf.addElement(str);
    temp = new String[buf.size()];
    for (int i = 0; i < buf.size(); i++) {
      temp[i] = ((String)buf.get(i));
    }
    return temp;
  }

  public static String getRandomKey(int len)
  {
    String chararray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    String ret = "";
    for (int i = 0; i < len; i++) {
      ret = ret + chararray.charAt((int)(Math.random() * 100.0D) % chararray.length());
    }
    return ret;
  }

  public static String newAttachIdx()
  {
    return System.currentTimeMillis() + "_" + getRandomKey(10);
  }

  public static StringBuffer getRequestURL(HttpServletRequest req) {
    StringBuffer url = new StringBuffer();
    String scheme = req.getScheme();
    int port = req.getServerPort();
    String urlPath = req.getRequestURI();
    url.append(scheme);
    url.append("://");
    url.append(req.getServerName());
    if (((scheme.equals("http")) && (port != 80)) || ((scheme.equals("https")) && (port != 443)))
    {
      url.append(':');
      url.append(req.getServerPort());
    }
    url.append(urlPath);
    return url;
  }

  public static final void generateHTML(HttpServletRequest request, StringBuffer html, String path, String name)
    throws Exception
  {
    try
    {
      String _path = request.getRealPath("") + File.separator + "WEB-INF" + File.separator + "html" + File.separator + path;
      System.out.println("\t\t\t::: HTML Generate Path : " + _path + File.separator + name + ".html");
      File file = new File(_path);
      if ((!file.exists()) || (!file.isDirectory())) {
        file.mkdirs();
      }

      BufferedWriter out = new BufferedWriter(new FileWriter(_path + File.separator + name + ".html"));

      out.write(html.toString());
      out.newLine();

      System.out.println("\t\t\t::: HTML Generated");
      out.close();
    } catch (IOException e) {
      System.out.println("\t\t\t::: HTML Generate Fail");
      throw e;
    }
  }

  public static String makeRtnUrl(HttpServletRequest request)
  {
    Enumeration param = request.getParameterNames();
    String strParam = "";
    String rtn_url = "";
    int i = 0;
    while (param.hasMoreElements()) {
      String name = (String)param.nextElement();
      String value = request.getParameter(name);

      if (i == 0)
        strParam = strParam + name + "=" + value;
      else {
        strParam = strParam + "&" + name + "=" + value;
      }

      i++;
    }
    if (strParam.equals(""))
      rtn_url = request.getRequestURI().toString();
    else {
      rtn_url = request.getRequestURI() + "?" + strParam;
    }

    return rtn_url;
  }

  public static String convertHtmlimg(String img) {
    String t = "";
    if (isNotEmpty(img)) {
      Pattern p = Pattern.compile("(?i)<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
      Matcher m = p.matcher(img);
      while (m.find()) {
        System.out.println(m.group(1));
        t = m.group(1);
        if (!t.equals("")) {
          break;
        }
      }
    }
    return t;
  }

  public static String convertHtmlalt(String img) {
    String t = "";
    if (isNotEmpty(img)) {
      Pattern p = Pattern.compile("(?i)<img[^>]*alt=[\"']?([^>\"']+)[\"']?[^>]*>");
      Matcher m = p.matcher(img);
      while (m.find()) {
        System.out.println(m.group(1));
        t = m.group(1);
        if (!t.equals("")) {
          break;
        }
      }
    }
    return t;
  }

  public static List<String> convertHtmlimgList(String img) {
    String t = "";
    List list = new ArrayList();
    if (isNotEmpty(img)) {
      Pattern p = Pattern.compile("(?i)<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
      Matcher m = p.matcher(img);
      while (m.find()) {
        t = m.group(1);
        list.add(t);
      }
    }
    return list;
  }

  public static List<HashMap<String, String>> convertHtmlimgListMap(String img) {
    HashMap hashMap = null;
    List list = new ArrayList();
    if (isNotEmpty(img)) {
      Pattern img_p = Pattern.compile("(?i)<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
      Matcher img_m = img_p.matcher(img);
      while (img_m.find()) {
        hashMap = new HashMap();
        hashMap.put("img", img_m.group(1));
        hashMap.put("alt", "");
        list.add(hashMap);
      }

      Pattern alt_p = Pattern.compile("(?i)<img[^>]*alt=[\"']?([^>\"']+)[\"']?[^>]*>");
      Matcher alt_m = alt_p.matcher(img);
      int i = 0;
      while (alt_m.find()) {
        ((HashMap)list.get(i)).put("alt", alt_m.group(1));
        i++;
      }
    }

    return list;
  }

  public static String converHtmlImgFileSetVoOnlyOne(String contents)
  {
    String rtnVal = "";

    Pattern p = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");

    contents = contents.replaceAll("<IMG", "<img");
    contents = contents.replaceAll("<Img", "<img");
    Matcher m = p.matcher(contents);

    int i = 0;
    while ((m.find()) && (i == 0)) {
      rtnVal = m.group(1);
      i++;
    }
    return rtnVal;
  }

  public static String getAddMonth(int num)
  {
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");

    Calendar cal = Calendar.getInstance();
    cal.add(2, num);

    return sdformat.format(cal.getTime());
  }

  public static String getAddDate(int num)
  {
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");

    Calendar cal = Calendar.getInstance();
    cal.add(5, num);

    return sdformat.format(cal.getTime());
  }

  public static String getAddMonth(String dateStr, int inc)
    throws ParseException
  {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    Calendar c = Calendar.getInstance();

    c.setTime(sdf.parse(dateStr));
    c.add(2, inc);

    return sdf.format(c.getTime());
  }

  public static String getNextMonth(String dateStr)
    throws ParseException
  {
    return getAddMonth(dateStr, 1);
  }

  public static String getPrevMonth(String dateStr)
    throws ParseException
  {
    return getAddMonth(dateStr, -1);
  }

  public static String paramMapToParamString(Map<String, String> params)
    throws UnsupportedEncodingException
  {
    StringBuilder sb = new StringBuilder();
    for (String key : params.keySet()) {
      sb.append("&");
      sb.append(key);
      String value = (String)params.get(key);
      if (value != null) {
        sb.append("=");
        sb.append(value);
      }
    }
    return sb.toString();
  }

  public static String paramMapToParamString(HttpServletRequest request)
    throws UnsupportedEncodingException
  {
    Enumeration enums = request.getParameterNames();

    StringBuilder sb = new StringBuilder();

    while (enums.hasMoreElements()) {
      String paramName = (String)enums.nextElement();
      String[] parameters = request.getParameterValues(paramName);

      sb.append("&amp;");
      sb.append(paramName);
      sb.append("=");

      if (parameters.length > 1) {
        sb.append(parameters);
      }
      else {
        sb.append(parameters[0]);
      }
    }

    return sb.toString();
  }

  public static String paramMapToParamHiddenHtmlTag(HttpServletRequest request)
  {
    Enumeration enums = request.getParameterNames();

    StringBuilder sb = new StringBuilder();

    while (enums.hasMoreElements()) {
      String paramName = (String)enums.nextElement();
      String[] parameters = request.getParameterValues(paramName);

      sb.append("<input type=\"hidden\" name=\"" + paramName + "\" value=\"" + parameters[0] + "\" />\n");
    }

    return sb.toString();
  }

  public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException
  {
    Map retMap = new HashMap();

    if (json != JSONObject.NULL) {
      retMap = toMap(json);
    }
    return retMap;
  }

  public static Map<String, Object> toMap(JSONObject object) throws JSONException {
    Map map = new HashMap();

    Iterator keysItr = object.keys();
    while (keysItr.hasNext()) {
      String key = (String)keysItr.next();
      Object value = object.get(key);

      if ((value instanceof JSONArray)) {
        value = toList((JSONArray)value);
      }
      else if ((value instanceof JSONObject)) {
        value = toMap((JSONObject)value);
      }
      map.put(key, value);
    }
    return map;
  }

  public static List<Object> toList(JSONArray array) throws JSONException {
    List list = new ArrayList();
    for (int i = 0; i < array.length(); i++) {
      Object value = array.get(i);
      if ((value instanceof JSONArray)) {
        value = toList((JSONArray)value);
      }
      else if ((value instanceof JSONObject)) {
        value = toMap((JSONObject)value);
      }
      list.add(value);
    }
    return list;
  }
}