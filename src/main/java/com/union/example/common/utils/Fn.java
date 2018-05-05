package com.union.example.common.utils;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Fn
{
  private static Fn instance = new Fn();
  private static String DEFAULT_ENCODING = "UTF-8";

  public static Fn getInstance() {
    return instance;
  }

  public static String zeroFill(String format, String input)
  {
    DecimalFormat DF = new DecimalFormat(format);
    String outPut = DF.format(Integer.parseInt(input));
    return outPut;
  }

  public static String htmlParseIframeUrl(String html)
  {
    Pattern p = Pattern.compile("<iframe(.*?)src=\\\"(.*?)\\\"");
    Matcher m = p.matcher(html);
    String url = "";

    while (m.find()) {
      String s = m.group().toString();
      url = s.substring(s.indexOf("src=") + 5, s.length() - 1);
      if ((url != null) && (!url.equals("")))
      {
        break;
      }
    }
    return url;
  }

  public static String htmlParseIframeYoutubeId(String html)
  {
    String url = html;
    if (url.indexOf("<iframe") > -1) {
      url = htmlParseIframeUrl(url);
    }

    String vId = "";
    Pattern pattern = Pattern.compile(
      "^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$", 
      2);
    Matcher matcher = pattern.matcher(url);
    if (matcher.matches()) {
      vId = matcher.group(1);
    }

    return vId;
  }

  public static String htmlParseIframeYoutubeThumb(String html, String size)
  {
    if (isEmpty(html)) {
      return "";
    }

    String rtnId = htmlParseIframeYoutubeId(html);

    if (!rtnId.equals("")) {
      return "http://img.youtube.com/vi/" + htmlParseIframeYoutubeId(html) + "/" + Util.emptyCk(size, "0") + ".jpg";
    }

    return "";
  }

  public static int toInt(String value)
  {
    int to_int = 0;
    try { to_int = Integer.parseInt(replaceAll(value, ",", "")); } catch (Exception localException) {
    }return to_int;
  }

  public static int toInt(Object value)
  {
    return toInt(toString(value));
  }

  public static int[] toIntArray(String[] value_array)
  {
    int[] revalue_array = null;
    if ((value_array != null) && (value_array.length > 0)) {
      revalue_array = new int[value_array.length];
      for (int i = 0; i < value_array.length; i++)
        revalue_array[i] = toInt(value_array[i]);
    }
    else {
      revalue_array = new int[1];
    }
    return revalue_array;
  }

  public static int[] toIntArray(String[] value_array, int array_size)
  {
    int[] revalue_array = new int[array_size];

    if ((value_array != null) && (value_array.length > 0)) {
      for (int i = 0; i < value_array.length; i++) {
        if (array_size > i) {
          revalue_array[i] = toInt(value_array[i]);
        }
      }
    }
    else {
      revalue_array = new int[1];
    }

    return revalue_array;
  }

  public static int[] toIntArray(Object[] value_array)
  {
    int[] revalue_array = null;
    if ((value_array != null) && (value_array.length > 0)) {
      revalue_array = new int[value_array.length];
      for (int i = 0; i < value_array.length; i++)
        revalue_array[i] = toInt(value_array[i]);
    }
    else {
      revalue_array = new int[1];
    }
    return revalue_array;
  }

  public static int toInt(String value, int revalue)
  {
    int temp = toInt(value);
    return temp != 0 ? temp : revalue;
  }

  public static int toInt(Integer value, int revalue)
  {
    return toInt(value, revalue);
  }

  public static int toInt(HttpServletRequest request, String value)
  {
    return toInt(request.getParameter(value));
  }

  public static int toInt(HttpServletRequest request, String value, int revalue)
  {
    return toInt(request.getParameter(value), revalue);
  }

  public static int toInt(HttpServletRequest request, String value, String revalue, String chkvalue_str)
  {
    return toInt(toString(request, value, revalue, chkvalue_str));
  }

  public static int toInt(HttpSession session, String value)
  {
    return toInt(session.getAttribute(value));
  }
/*
  public static int toInt(HttpSession session, String value, int revalue)
  {
    return toInt(session.getAttribute(value), revalue);
  }
*/
  public static int toString(HttpSession session, String value, String revalue, String chkvalue_str)
  {
    return toInt(Integer.valueOf(toString(session, value, revalue, chkvalue_str)));
  }

  public static int toInt(Map<String, Object> map, String value)
  {
    return map == null ? 0 : toInt(map.get(value));
  }

  /*public static int toInt(Map<String, Object> map, String value, int revalue)
  {
    return map == null ? 0 : toInt(map.get(value), revalue);
  }*/
  public static int toInt(Map<String, Object> map, String value, String revalue, String chkvalue_str) {
    return toInt(toString(map, value, revalue, chkvalue_str));
  }
/*  public static int toInt(Map<String, Object> map, String value, int revalue, String chkvalue_str) {
    return toInt(toString(map, value, revalue, chkvalue_str));
  }
*/
  public static double toDouble(String value)
  {
    double to_double = 0.0D;
    try { to_double = Double.parseDouble(replaceAll(value, ",", "")); } catch (Exception localException) {
    }return to_double;
  }

  public static double toDouble(Object value)
  {
    return toDouble(value);
  }

  public static double toDouble(String value, double revalue)
  {
    double temp = toDouble(value);
    return temp != 0.0D ? temp : revalue;
  }

  public static double toDouble(HttpServletRequest request, String value)
  {
    return toDouble(request.getParameter(value));
  }

  public static double toDouble(HttpServletRequest request, String value, double revalue)
  {
    return toDouble(request.getParameter(value), revalue);
  }

  public static double toDouble(HttpSession session, String value)
  {
    return toDouble(session.getAttribute(value));
  }

  public static double toDouble(HttpSession session, String value, double revalue)
  {
    return toDouble((String)session.getAttribute(value), revalue);
  }

  public static double toDouble(Map<String, Object> map, String value)
  {
    return map == null ? 0.0D : toDouble(map.get(value));
  }

  public static double toDouble(Map<String, Object> map, String value, double revalue)
  {
    return map == null ? revalue : toDouble((String)map.get(value), revalue);
  }

/*  public static String toHangle(String str)
  {
    String result = "";
    try {
      String[] dan1 = { "", "만 ", "억 ", "조 ", "경 ", "해 ", "시 ", "양 ", "구 ", "간 ", "정 " };
      String[] dan2 = { "", "십", "백", "천" };
      String[] han = { "", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구" };

      int i = str.length() - 1; for (int i2 = 0; i >= 0; i2++) {
        int temp = Integer.parseInt(str.charAt(i));

        int chk = i2 % 4 == 0 ? i2 / 4 : 0;
        result = ((temp == 1) && (i2 > 0) && (chk == 0) ? "" : han[temp]) + (temp == 0 ? "" : dan2[(i2 % 4)]) + dan1[chk] + result;

        i--;
      }
    }
    catch (Exception localException)
    {
    }

    return result;
  }*/
  public static String toHangle(int str) {
    return toHangle(str);
  }
/*  public static String toHangle(double str) {
    return toHangle(decimal(str, "#"));
  }
*/
  public static String toString(String value)
  {
    return (value == null) || (value.trim().equals("")) || (value.equals("null")) ? "" : value;
  }
  public static String toStringHtml(String value) {
    return htmlSpecialChars(toString(value));
  }
  public static String toStringYmd(String value) {
    String cfinput = "";
    try { cfinput = new SimpleDateFormat("yyyy-MM-dd").format(Timestamp.valueOf(value + " 00:00:00")); } catch (Exception localException) {
    }return (value.equals(cfinput)) && (cfinput.length() > 0) ? toString(value) : "";
  }

  public static String toString(Object value)
  {
    return toString(value);
  }
  public static String toStringHtml(Object value) {
    return htmlSpecialChars(toString(value));
  }
  public static String toStringYmd(Object value) {
    return toStringYmd(toString(value));
  }

  public static String toString(String value, String revalue)
  {
    return toString(value).length() > 0 ? value : revalue;
  }
  public static String toStringHtml(String value, String revalue) {
    return htmlSpecialChars(toString(value, revalue));
  }
  public static String toStringYmd(String value, String revalue) {
    return toString(toStringYmd(value), revalue);
  }

  public static String toString(Object value, Object revalue)
  {
    String value_str = toString(value);
    return value_str.length() > 0 ? value_str : toString(revalue);
  }
  public static String toStringHtml(Object value, Object revalue) {
    return htmlSpecialChars(toString(value, revalue));
  }
  public static String toStringYmd(Object value, Object revalue) {
    return toString(toStringYmd(value), revalue);
  }

  public static String toString(HttpServletRequest request, String value)
  {
    return toStringMethodDivChange(request, toString(request.getParameter(value)));
  }
  public static String toStringHtml(HttpServletRequest request, String value) {
    return htmlSpecialChars(toString(request, value));
  }
  public static String toStringYmd(HttpServletRequest request, String value) {
    return toStringYmd(toString(request, value));
  }

  private static String toStringMethodDivChange(HttpServletRequest request, String value)
  {
    return toStringMethodDivChange(request.getContentType(), request.getMethod(), value);
  }
  private static String toStringMethodDivChange(String content_type, String form_type, String value) {
    if (toString(content_type).indexOf("multipart/form-data") > -1)
    {
      return toString(value);
    }if (form_type.equals("GET")) {
      return toString(value);
    }
    return toString(value);
  }

  public static String toString(HttpServletRequest request, String value, String revalue)
  {
    return toString(toString(request, value), revalue);
  }
  public static String toStringHtml(HttpServletRequest request, String value, String revalue) {
    return htmlSpecialChars(toString(request, value, revalue));
  }
  public static String toStringYmd(HttpServletRequest request, String value, String revalue) {
    return toString(toStringYmd(toString(request, value)), revalue);
  }

  public static String toString(HttpServletRequest request, String value, String revalue, String chkvalue_str)
  {
    String param_value = toString(request, value);
    return isEqualValue(chkvalue_str, ",", param_value) ? param_value : revalue;
  }
  public static String toStringHtml(HttpServletRequest request, String value, String revalue, String chkvalue_str) {
    return htmlSpecialChars(toString(request, value, revalue, chkvalue_str));
  }
  public static String toStringYmdBetween(HttpServletRequest request, String value, String revalue, String schkvalue, String echkvalue) {
    String ymd = toStringYmd(request, value);
    boolean chk = (ymd.compareTo(toStringYmd(schkvalue)) >= 0) && (ymd.compareTo(toStringYmd(echkvalue)) <= 0);
    return chk ? ymd : revalue;
  }

  public static String toString(HttpSession session, String value)
  {
    return toString((String)session.getAttribute(value));
  }
  public static String toStringHtml(HttpSession session, String value) {
    return htmlSpecialChars(toString(session, value));
  }
  public static String toStringYmd(HttpSession session, String value) {
    return toStringYmd(toString(session, value));
  }

  public static String toString(Map<String, Object> map, String value)
  {
    return map == null ? "" : toString(map.get(value));
  }
  public static String toStringHtml(Map<String, Object> map, String value) {
    return htmlSpecialChars(toString(map, value));
  }

  public static String toStringYmd(Map<String, Object> map, String value) {
    return toStringYmd(toString(map, value));
  }
  public static String toStringYmd(Map<String, Object> map, String value, String revalue) {
    return toStringYmd(toString(map, value), revalue);
  }

  public static String toString(HttpSession session, String value, String revalue)
  {
    return toString((String)session.getAttribute(value), revalue);
  }
  public static String toStringHtml(HttpSession session, String value, String revalue) {
    return htmlSpecialChars(toString(session, value, revalue));
  }
  public static String toStringYmd(HttpSession session, String value, String revalue) {
    return toString(toStringYmd(toString(session, value)), revalue);
  }

  public static String toString(Map<String, Object> map, String value, String revalue)
  {
    return map == null ? "" : toString((String)map.get(value), revalue);
  }
  public static String toStringHtml(Map<String, Object> map, String value, String revalue) {
    return htmlSpecialChars(toString(map, value, revalue));
  }
  public static String toString(Map<String, Object> map, String value, String revalue, String chkvalue_str) {
    String param_value = toString(map, value);
    return isEqualValue(chkvalue_str, ",", param_value) ? param_value : revalue;
  }
  public static String toStringHtml(Map<String, Object> map, String value, String revalue, String chkvalue_str) {
    return htmlSpecialChars(toString(map, value, revalue, chkvalue_str));
  }

  public static String toStringStr(HashMap<String, String> hashmap, String key_str, String key_symbol, String value_symbol)
  {
    StringBuffer str_buff = new StringBuffer();
    String[] key_array = toStr_array(key_str, key_symbol);
    for (String key : key_array) {
      if (key.length() > 0) {
        String value = toString((String)hashmap.get(key));
        if (value.length() > 0) {
          str_buff.append(value + value_symbol);
        }
      }
    }
    str_buff.append(value_symbol);
    return replaceAll(str_buff.toString(), value_symbol + value_symbol, "");
  }

  public static String toStringStr(String[] value_array, String value_symbol)
  {
    StringBuffer str_buff = new StringBuffer();
    String[] arrayOfString = value_array; int j = value_array.length; for (int i = 0; i < j; i++) { String value = arrayOfString[i];
      if ((value.length() <= 0) || 
        (value.length() <= 0)) continue;
      str_buff.append(value + value_symbol);
    }

    str_buff.append(value_symbol);
    String return_str = replaceAll(str_buff.toString(), value_symbol + value_symbol, "");
    if (return_str.equals(value_symbol)) {
      return_str = "";
    }
    return return_str;
  }

  public static String toStringStr(List<String> value_list, String value_symbol)
  {
    StringBuffer str_buff = new StringBuffer();

    for (String value : value_list) {
      if ((value.length() <= 0) || 
        (value.length() <= 0)) continue;
      str_buff.append(value + value_symbol);
    }

    str_buff.append(value_symbol);
    return replaceAll(str_buff.toString(), value_symbol + value_symbol, "");
  }

  public static String toStrAdd(String org_str, String add_str)
  {
    return toStrAdd(org_str, add_str, ",");
  }
  public static String toStrAdd(String org_str, String add_str, String add_symbol) {
    String new_str = "";
    String[] org_arr = toStr_array(org_str, add_symbol);
    List strList = new ArrayList();
    for (String org : org_arr) {
      if ((org.length() <= 0) || 
        (org.equals(add_str))) continue;
      strList.add(org);
    }

    strList.add(add_str);
    if (strList.size() > 0) {
      for (int i = 0; i < strList.size(); i++) {
        new_str = new_str + (String)strList.get(i);
        if (i < strList.size() - 1) {
          new_str = new_str + add_symbol;
        }
      }
    }
    return new_str;
  }

  public static String toStrSub(String org_str, String sub_str)
  {
    return toStrSub(org_str, sub_str, ",");
  }
  public static String toStrSub(String org_str, String sub_str, String add_symbol) {
    String new_str = "";
    String[] org_arr = toStr_array(org_str, add_symbol);
    List strList = new ArrayList();
    for (String org : org_arr) {
      if ((org.length() <= 0) || 
        (org.equals(sub_str))) continue;
      strList.add(org);
    }

    if (strList.size() > 0) {
      for (int i = 0; i < strList.size(); i++) {
        new_str = new_str + (String)strList.get(i);
        if (i < strList.size() - 1) {
          new_str = new_str + add_symbol;
        }
      }
    }
    return new_str;
  }

  public static HashMap<String, String> toHashMapStringString(Object value)
  {
    HashMap tohash = new HashMap();
    if (value != null) {
      tohash = (HashMap)value;
    }
    return tohash;
  }

  public static HashMap<String, String> toHashMapStringString(Object value, Object revalue) {
    HashMap tohash = new HashMap();
    if (value == null)
      tohash = (HashMap)revalue;
    else {
      tohash = (HashMap)value;
    }
    return tohash;
  }

  public static HashMap<String, String> toHashMapStringString(Object value, HashMap<String, String> revalue) {
    HashMap tohash = new HashMap();
    if (value == null)
      tohash = revalue;
    else {
      tohash = (HashMap)value;
    }
    return tohash;
  }
  public static HashMap<String, String> toHashMapStringString(HashMap<String, String> value, HashMap<String, String> revalue) {
    if (value == null) {
      return revalue;
    }
    return value;
  }

  public static HashMap<String, Integer> toHashMapStringInteger(Object value)
  {
    HashMap tohash = new HashMap();
    if (value != null) {
      tohash = (HashMap)value;
    }
    return tohash;
  }

  public static HashMap<String, Integer> toHashMapStringInteger(Object value, Object revalue) {
    HashMap tohash = new HashMap();
    if (value == null)
      tohash = (HashMap)revalue;
    else {
      tohash = (HashMap)value;
    }
    return tohash;
  }

  public static HashMap<String, Integer> toHashMapStringInteger(Object value, HashMap<String, Integer> revalue) {
    HashMap tohash = new HashMap();
    if (value == null)
      tohash = revalue;
    else {
      tohash = (HashMap)value;
    }
    return tohash;
  }
  public static HashMap<String, Integer> toHashMapStringInteger(HashMap<String, Integer> value, HashMap<String, Integer> revalue) {
    if (value == null) {
      return revalue;
    }
    return value;
  }

  public static HashMap<String, Double> toHashMapStringDouble(Object value)
  {
    HashMap tohash = new HashMap();
    if (value != null) {
      tohash = (HashMap)value;
    }
    return tohash;
  }

  public static HashMap<String, Double> toHashMapStringDouble(Object value, Object revalue) {
    HashMap tohash = new HashMap();
    if (value == null)
      tohash = (HashMap)revalue;
    else {
      tohash = (HashMap)value;
    }
    return tohash;
  }

  public static HashMap<String, Double> toHashMapStringDouble(Object value, HashMap<String, Double> revalue) {
    HashMap tohash = new HashMap();
    if (value == null)
      tohash = revalue;
    else {
      tohash = (HashMap)value;
    }
    return tohash;
  }
  public static HashMap<String, Double> toHashMapStringDouble(HashMap<String, Double> value, HashMap<String, Double> revalue) {
    if (value == null) {
      return revalue;
    }
    return value;
  }

  public static HashMap<String, Object> toHashMapStringObject(Object value)
  {
    HashMap tohash = new HashMap();
    if (value != null) {
      tohash = (HashMap)value;
    }
    return tohash;
  }

  public static HashMap<String, Object> toHashMapStringObject(Object value, Object revalue) {
    HashMap tohash = new HashMap();
    if (value == null)
      tohash = (HashMap)revalue;
    else {
      tohash = (HashMap)value;
    }
    return tohash;
  }

  public static HashMap<String, Object> toHashMapStringObject(Object value, HashMap<String, Object> revalue) {
    HashMap tohash = new HashMap();
    if (value == null)
      tohash = revalue;
    else {
      tohash = (HashMap)value;
    }
    return tohash;
  }
  public static HashMap<String, Object> toHashMapStringObject(HashMap<String, Object> value, HashMap<String, Object> revalue) {
    if (value == null) {
      return revalue;
    }
    return value;
  }

  public static String toParamNoEncodeStr(HttpServletRequest request, String addSymbol, String names)
  {
    return toParamNoEncodeStr(request, addSymbol, names, "&");
  }

  public static String toParamNoEncodeStr(HttpServletRequest request, String addSymbol, String names, String start_symbol) {
    String[] name_array = names.split(",");
    try {
      StringBuffer param = new StringBuffer();
      for (int i = 0; i < name_array.length; i++) {
        param.append(addSymbol + name_array[i] + "=" + toString(request, name_array[i]));
      }
      return start_symbol + param.toString().substring(1); } catch (Exception e) {
    }
    return "";
  }

  public static String toParamStr(HttpServletRequest request, String addSymbol, String names)
  {
    return toParamStr(request, addSymbol, names, "&");
  }
  public static String toParamStrHtml(HttpServletRequest request, String addSymbol, String names) {
    return htmlSpecialChars(toParamStr(request, addSymbol, names));
  }
  public static String toParamStr(HttpServletRequest request, String addSymbol, String names, String start_symbol) {
    String[] name_array = names.split(",");
    try {
      StringBuffer param = new StringBuffer();
      for (int i = 0; i < name_array.length; i++)
      {
        param.append(addSymbol + name_array[i] + "=" + toString(request, name_array[i]));
      }
      return start_symbol + param.toString().substring(1); } catch (Exception e) {
    }
    return "";
  }

  public static String toParamStrHtml(HttpServletRequest request, String addSymbol, String names, String start_symbol) {
    return htmlSpecialChars(toParamStr(request, addSymbol, names, start_symbol));
  }

  public static String toParamStr(Map<String, Object> map, String addSymbol, String names)
  {
    return toParamStr(map, addSymbol, names, "&");
  }
  public static String toParamStrHtml(Map<String, Object> map, String addSymbol, String names) {
    return htmlSpecialChars(toParamStr(map, addSymbol, names));
  }
  public static String toParamStr(Map<String, Object> map, String addSymbol, String names, String start_symbol) {
    String[] name_array = names.split(",");
    try {
      StringBuffer param = new StringBuffer();
      for (int i = 0; i < name_array.length; i++)
      {
        param.append(addSymbol + name_array[i] + "=" + toString(map, name_array[i]));
      }
      return start_symbol + param.toString().substring(1); } catch (Exception e) {
    }
    return "";
  }

  public static String toParamStrHtml(Map<String, Object> map, String addSymbol, String names, String start_symbol) {
    return htmlSpecialChars(toParamStr(map, addSymbol, names, start_symbol));
  }

  public static String toParamHidden(HttpServletRequest request, String names)
  {
    String[] param_array = names.split(",");
    try {
      StringBuffer hidden = new StringBuffer();

      for (int i = 0; i < param_array.length; i++) {
        if (toString(request, param_array[i]).length() > 0) {
          hidden.append("<input type=\"hidden\" name=\"" + param_array[i] + "\" value=\"" + htmlSpecialChars(toString(request, param_array[i])) + "\" />");
        }
      }
      return hidden.toString(); } catch (Exception e) {
    }
    return "";
  }

  public static String toParamHidden(Map<String, Object> map, String names)
  {
    String[] param_array = names.split(",");
    try {
      StringBuffer hidden = new StringBuffer();

      for (int i = 0; i < param_array.length; i++) {
        if (toString(map, param_array[i]).length() > 0) {
          hidden.append("<input type=\"hidden\" name=\"" + param_array[i] + "\" value=\"" + htmlSpecialChars(toString(map, param_array[i])) + "\" />");
        }
      }
      return hidden.toString(); } catch (Exception e) {
    }
    return "";
  }

  public static String toParamAllHidden(HttpServletRequest request, String exclude_param)
  {
    StringBuffer hidden = new StringBuffer();

    Enumeration paramNames = request.getParameterNames();
    while (paramNames.hasMoreElements()) {
      String param_nm = paramNames.nextElement().toString();
      if ((isEqualValue(exclude_param, ",", param_nm)) || 
        (toString(request, param_nm).length() <= 0)) continue;
      hidden.append("<input type=\"hidden\" name=\"" + param_nm + "\" value=\"" + htmlSpecialChars(toString(request, param_nm)) + "\" />\n");
    }

    return hidden.toString();
  }

  public static String toParamValueStr(HttpServletRequest request, String names, String valueSymbol)
  {
    String[] name_array = names.split(",");
    try {
      StringBuffer param = new StringBuffer();
      for (int i = 0; i < name_array.length; i++)
      {
        param.append(toString(request, name_array[i]) + valueSymbol);
      }
      return param.toString().length() > 0 ? param.toString().substring(0, param.toString().length() - valueSymbol.length()) : ""; } catch (Exception e) {
    }
    return "";
  }

  public static String toParamValueStr(Map<String, Object> map, String names, String valueSymbol)
  {
    String[] name_array = names.split(",");
    try {
      StringBuffer param = new StringBuffer();
      for (int i = 0; i < name_array.length; i++)
      {
        param.append(toString(map, name_array[i]) + valueSymbol);
      }
      return param.toString().length() > 0 ? param.toString().substring(0, param.toString().length() - valueSymbol.length()) : ""; } catch (Exception e) {
    }
    return "";
  }

  public static String toParamTelNo(HttpServletRequest request, String names)
  {
    String[] name_array = names.split(",");
    if (name_array.length >= 3) {
      try {
        StringBuffer param = new StringBuffer();
        param.append(toParamAddSymbolStr(request, "-", name_array[0] + "," + name_array[1] + "," + name_array[2]));
        if (name_array.length == 4) {
          param.append("~" + toString(request, name_array[3]));
        }
        return param.toString();
      } catch (Exception e) {
        return "";
      }
    }
    return "";
  }

  public static String toParamTelNo(Map<String, Object> map, String names)
  {
    String[] name_array = names.split(",");
    if (name_array.length >= 3) {
      try {
        StringBuffer param = new StringBuffer();
        param.append(toParamAddSymbolStr(map, "-", name_array[0] + "," + name_array[1] + "," + name_array[2]));
        if (name_array.length == 4) {
          param.append("~" + toString(map, name_array[3]));
        }
        return param.toString();
      } catch (Exception e) {
        return "";
      }
    }
    return "";
  }

  public static String toParamAddSymbolStr(HttpServletRequest request, String symbol, String names)
  {
    String[] name_array = names.split(",");
    if (name_array.length > 1) {
      try {
        StringBuffer param = new StringBuffer();
        for (int i = 0; i < name_array.length; i++) {
          param.append(toString(request, name_array[i]));
          if (i < name_array.length - 1) {
            param.append(symbol);
          }
        }
        return param.toString();
      } catch (Exception e) {
        return "";
      }
    }
    return "";
  }

  public static String toParamAddSymbolStr(Map<String, Object> map, String symbol, String names)
  {
    String[] name_array = names.split(",");
    if (name_array.length > 1) {
      try {
        StringBuffer param = new StringBuffer();
        for (int i = 0; i < name_array.length; i++) {
          param.append(toString(map, name_array[i]));
          if (i < name_array.length - 1) {
            param.append(symbol);
          }
        }
        return param.toString();
      } catch (Exception e) {
        return "";
      }
    }
    return "";
  }

  public static String toParamValuesAddSymbolStr(HttpServletRequest request, String name, String addSymbol)
  {
    StringBuffer param_value_str = new StringBuffer();
    try {
      String[] param_value_array = request.getParameterValues(name);
      String content_type = request.getContentType();
      String form_type = request.getMethod();

      if (param_value_array != null)
        for (int i = 0; i < param_value_array.length; i++) {
          param_value_str.append(toStringMethodDivChange(content_type, form_type, param_value_array[i]));
          if (i < param_value_array.length - 1)
            param_value_str.append(addSymbol);
        }
    }
    catch (Exception e)
    {
      param_value_str.append(toString(request, name));
    }
    return param_value_str.toString();
  }

  public static String toParamValuesAddSymbolStr(Map<String, Object> map, String name, String addSymbol) {
    StringBuffer param_value_str = new StringBuffer();
    try {
      String[] param_value_array = (String[])map.get(name);
      if (param_value_array != null)
        for (int i = 0; i < param_value_array.length; i++) {
          param_value_str.append(param_value_array[i]);
          if (i < param_value_array.length - 1)
            param_value_str.append(addSymbol);
        }
    }
    catch (Exception e)
    {
      param_value_str.append(toString(map, name));
    }
    return param_value_str.toString();
  }

  public static String[] toArrayString(HttpServletRequest request, String name, String addSymbol)
  {
    return toStr_array(toString(request, name), addSymbol);
  }

  public static String[] toParameterValues(HttpServletRequest request, String name)
  {
    return toStr_array(toParamValuesAddSymbolStr(request, name, "##DIV##"), "##DIV##");
  }

  public static HashMap<String, String> toParamHash(HttpServletRequest request)
  {
    HashMap valuehash = new HashMap();

    Enumeration paramNames = request.getParameterNames();
    while (paramNames.hasMoreElements()) {
      String param_nm = paramNames.nextElement().toString();
      String pram_value = toString(request, param_nm);
      valuehash.put(param_nm, pram_value);
    }
    return valuehash;
  }

  public static HashMap<String, String> toParamHash(Map<String, Object> map)
  {
    HashMap valuehash = new HashMap();

    Set set = map.keySet();
    Iterator iter = set.iterator();
    while (iter.hasNext()) {
      String temp = (String)iter.next();
      String value = toString(map.get(temp));
      if (value.length() > 0) {
        valuehash.put(temp, map.get(temp));
      }
    }
    return valuehash;
  }

  public static String toStringCharSetEncodeParam(HttpServletRequest request, String value, String send_server_charset)
  {
    String param_value = "";
    if (request.getMethod().equals("GET"))
    {
      String[] arrayOfString1;
      int j = (arrayOfString1 = toString(request.getQueryString()).split("&")).length; for (int i = 0; i < j; ) { String param_temp = arrayOfString1[i];
        try {
          String[] key_value = param_temp.split("=");
          if (key_value[0].equals(value))
            param_value = URLDecoder.decode(toString(key_value[1]), send_server_charset);
        }
        catch (Exception localException)
        {
          i++;
        }

      }

    }

    return param_value;
  }

  public static long toLong(Date value)
  {
    return value.getTime();
  }

  public static long toLong(Timestamp value)
  {
    return value.getTime();
  }

  public static Date toDate(long value)
  {
    return new Date(value);
  }

  public static Date toDate(Timestamp value)
  {
    return value == null ? null : new Date(value.getTime());
  }

  public static Date toDate(String value, String type)
  {
    try
    {
      return new Date(new SimpleDateFormat(type).parse(value).getTime()); } catch (Exception e) {
    }
    return null;
  }

  public static Date toDate(String value) {
    String[] stamp_array = { 
      "yyyy-MM-dd HH:mm:ss.SSS", 
      "yyyy-MM-dd HH:mm:ss", 
      "yyyy-MM-dd HH:mm", 
      "yyyy-MM-dd HH", 
      "yyyy-MM-dd", 
      "yyyy.MM.dd HH:mm:ss.SSS", 
      "yyyy.MM.dd HH:mm:ss", 
      "yyyy.MM.dd HH:mm", 
      "yyyy.MM.dd HH", 
      "yyyy.MM.dd", 
      "yyyyMMddHHmmssSSS", 
      "yyyyMMddHHmmss", 
      "yyyyMMddHHmm", 
      "yyyyMMddHH", 
      "yyyyMMdd" };

    Date date = null;
    for (int i = 0; i < stamp_array.length; i++) {
      try {
        date = toDate(value, stamp_array[i]); } catch (Exception localException) {
      }
      if (date != null) {
        break;
      }
    }
    return date;
  }

  public static Timestamp toTimestamp(long value)
  {
    return new Timestamp(value);
  }

  public static Timestamp toTimestamp(Date value)
  {
    return value == null ? null : new Timestamp(value.getTime());
  }

  public static Timestamp toTimestamp(String value, String type)
  {
    try
    {
      return new Timestamp(new SimpleDateFormat(type).parse(value).getTime()); } catch (Exception e) {
    }
    return null;
  }

  public static Timestamp toTimestamp(String value) {
    String[] stamp_array = { 
      "yyyy-MM-dd HH:mm:ss.SSS", 
      "yyyy-MM-dd HH:mm:ss", 
      "yyyy-MM-dd HH:mm", 
      "yyyy-MM-dd HH", 
      "yyyy-MM-dd", 
      "yyyy.MM.dd HH:mm:ss.SSS", 
      "yyyy.MM.dd HH:mm:ss", 
      "yyyy.MM.dd HH:mm", 
      "yyyy.MM.dd HH", 
      "yyyy.MM.dd", 
      "yyyyMMddHHmmssSSS", 
      "yyyyMMddHHmmss", 
      "yyyyMMddHHmm", 
      "yyyyMMddHH", 
      "yyyyMMdd" };

    Timestamp timestamp = null;
    if (toString(value).length() > 0) {
      for (int i = 0; i < stamp_array.length; i++) {
        try {
          timestamp = toTimestamp(value, stamp_array[i]); } catch (Exception localException) {
        }
        if (timestamp != null) {
          break;
        }
      }
    }
    return timestamp;
  }

  public static Timestamp toTimestamp(String value, String date_split_symbol, String date_time_between_split_symbol, String time_split_symbol)
  {
    String yyyy = "";
    String MM = "";
    String dd = "";
    String HH = "00";
    String mm = "00";
    String ss = "00";

    if ((date_time_between_split_symbol != null) && (date_time_between_split_symbol.length() > 0)) {
      String[] date_time_array = toStr_array(value, date_time_between_split_symbol, 2);
      if ((date_split_symbol != null) && (date_split_symbol.length() > 0) && (date_time_array[0] != null) && (date_time_array[0].length() > 0)) {
        String[] date_array = toStr_array(date_time_array[0], date_split_symbol, 3);
        yyyy = date_array[0];
        MM = date_array[1];
        dd = date_array[2];
      } else if ((date_time_array[0] != null) && (date_time_array[0].length() > 0)) {
        try {
          yyyy = date_time_array[0].substring(0, 4); } catch (Exception localException) {
        }
        try {
          MM = date_time_array[0].substring(4, 6); } catch (Exception localException1) {
        }
        try {
          dd = date_time_array[0].substring(6); } catch (Exception localException2) {
        }
      }
      if ((time_split_symbol != null) && (time_split_symbol.length() > 0) && (date_time_array[1] != null) && (date_time_array[1].length() > 0)) {
        String[] time_array = toStr_array(date_time_array[1], time_split_symbol, 3);
        HH = time_array[0];
        mm = time_array[1];
        ss = time_array[2];
      } else if ((date_time_array[1] != null) && (date_time_array[1].length() > 0)) {
        try {
          HH = date_time_array[1].substring(0, 2); } catch (Exception localException3) {
        }
        try {
          mm = date_time_array[1].substring(2, 4); } catch (Exception localException4) {
        }
        try {
          ss = date_time_array[1].substring(4); } catch (Exception localException5) {
        }
      }
    } else {
      try {
        yyyy = value.substring(0, 4); } catch (Exception localException6) {
      }
      try {
        MM = value.substring(4, 6); } catch (Exception localException7) {
      }
      try {
        dd = value.substring(6, 8); } catch (Exception localException8) {
      }
      try {
        HH = value.substring(8, 10); } catch (Exception localException9) {
      }
      try {
        mm = value.substring(10, 12); } catch (Exception localException10) {
      }
      try {
        ss = value.substring(12); } catch (Exception localException11) {
      }
    }
    return Timestamp.valueOf(yyyy + "-" + addZero(MM) + "-" + addZero(dd) + " " + addZero(HH) + ":" + addZero(mm) + ":" + addZero(ss));
  }

  public static String toDateFormat(Date value, String type)
  {
    return value == null ? "" : new SimpleDateFormat(type).format(value);
  }

  public static String toDateFormat(Long value, String type)
  {
    return value == null ? "" : new SimpleDateFormat(type).format(new Date(value.longValue()));
  }

  public static String toDateFormat(String value, String type)
  {
    try
    {
      return value == null ? "" : new SimpleDateFormat(type).format(toDate(value)); } catch (Exception e) {
    }
    return "";
  }

  public static String toKor(String value)
  {
    String str = "";
    if ((value != null) && (!value.equals(""))) {
      try {
        str = new String(value.getBytes("ISO-8859-1"), DEFAULT_ENCODING);
      }
      catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }
    return str;
  }

  public static String toEng(String value)
  {
    String str = "";
    if ((value != null) && (!value.equals(""))) {
      try {
        str = new String(value.getBytes("KSC5601"), "ISO-8859-1");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }
    return str;
  }

  public static String toTel_no_format(String tel_no)
  {
    StringBuffer telbuffer = new StringBuffer();
    if ((tel_no != null) && (tel_no.length() > 0)) {
      String patternStr = "\\d";
      Pattern pattern = Pattern.compile(patternStr);
      Matcher matcher = pattern.matcher(tel_no);
      while (matcher.find()) {
        telbuffer.append(matcher.group(0));
      }
    }
    String tel_no_str = telbuffer.toString();
    if (tel_no_str.length() == 11)
      tel_no_str = tel_no_str.substring(0, 3) + "-" + tel_no_str.substring(3, 7) + "-" + tel_no_str.substring(7);
    else if (tel_no_str.length() == 10) {
      if (tel_no_str.substring(0, 2).equals("02"))
        tel_no_str = tel_no_str.substring(0, 2) + "-" + tel_no_str.substring(2, 6) + "-" + tel_no_str.substring(6);
      else
        tel_no_str = tel_no_str.substring(0, 3) + "-" + tel_no_str.substring(3, 6) + "-" + tel_no_str.substring(6);
    }
    else if (tel_no_str.length() == 9) {
      tel_no_str = tel_no_str.substring(0, 2) + "-" + tel_no_str.substring(2, 5) + "-" + tel_no_str.substring(5);
    }
    return tel_no_str;
  }

  public static String[] toTel_no_array(String tel_no)
  {
    String[] tel_no_array = new String[4];
    for (int i = 0; i < 4; i++) {
      tel_no_array[i] = "";
      try {
        if (i < 3) {
          tel_no_array[i] = tel_no.split("-")[i];
        } else {
          String[] temp = tel_no_array[2].split("~");
          if ((temp != null) && (temp.length > 1)) {
            tel_no_array[2] = temp[0];
            tel_no_array[3] = temp[1];
          }
        }
      } catch (Exception localException) {
      }
    }
    return tel_no_array;
  }

  public static String[] toStr_array(String value, String split_symbol, int max_array_size)
  {
    String[] str_array = new String[max_array_size];
    for (int i = 0; i < max_array_size; i++) {
      str_array[i] = "";
    }
    String[] temp_array = toStr_array(value, split_symbol);
    for (int i = 0; i < temp_array.length; i++)
      try {
        str_array[i] = temp_array[i];
      } catch (Exception localException) {
      }
    return str_array;
  }

  public static String[] toStr_array(String value, String split_symbol)
  {
    return replaceAll(value, split_symbol, "##d##i##v##").split("##d##i##v##");
  }

  public static HashMap<String, String> toHash(String value_str, String symbol_1, String symbol_2)
  {
    HashMap hash = new HashMap();
    try {
      String[] str_array = toStr_array(value_str, symbol_1);
      for (String str : str_array) {
        String[] strs = toStr_array(str, symbol_2, 2);
        hash.put(strs[0], strs[1]);
      }

    }
    catch (Exception localException)
    {
    }

    return hash;
  }

  public static String changeString(String[] inputs, String[] outputs, String value)
  {
    String result = value;
    if ((inputs != null) && (outputs != null) && (inputs.length == outputs.length)) {
      for (int i = 0; i < inputs.length; i++) {
        if (inputs[i].equals(value)) {
          result = outputs[i];
          break;
        }
      }
    }
    return result;
  }

  public static String changeString(String input_str, String output_str, String value)
  {
    return changeString(toStr_array(input_str, ","), toStr_array(output_str, ","), value);
  }
  /*public static String changeString(String input_str, String output_str, int value) {
    return changeString(toStr_array(input_str, ","), toStr_array(output_str, ","), value);
  }*/
  public static String changeString(String input_str, String output_str, String split_symbol, String value) {
    return changeString(toStr_array(input_str, split_symbol), toStr_array(output_str, split_symbol), value);
  }
  /*public static String changeString(String input_str, String output_str, String split_symbol, int value) {
    return changeString(toStr_array(input_str, split_symbol), toStr_array(output_str, split_symbol), value);
  }*/
  public static int changeInt(String input_str, String output_str, String value) {
    return toInt(changeString(input_str, output_str, ",", value));
  }
  /*public static int changeInt(String input_str, String output_str, int value) {
    return toInt(changeString(input_str, output_str, ",", value));
  }*/
  public static int changeInt(String input_str, String output_str, String split_symbol, String value) {
    return toInt(changeString(input_str, output_str, split_symbol, value));
  }
  /*public static int changeInt(String input_str, String output_str, String split_symbol, int value) {
    return toInt(changeString(input_str, output_str, split_symbol, value));
  }*/
  public static String changeString(HashMap<String, String> hash, String split_symbol, String value) {
    StringBuffer value_str = new StringBuffer();

    if ((hash != null) && (hash.size() > 0) && (split_symbol != null) && (split_symbol.length() > 0)) {
      String[] value_str_arr = toStr_array(value, split_symbol);

      for (int i = 0; i < value_str_arr.length; i++) {
        value_str.append((String)hash.get(value_str_arr[i]));
        if (i < value_str_arr.length - 1)
          value_str.append(split_symbol);
      }
    }
    else {
      value_str.append(value);
    }

    return value_str.toString();
  }

  public static String changeString(HashMap<String, String> hash, String split_symbol, String out_symbol, String value) {
    return replaceAll(changeString(hash, split_symbol, value), split_symbol, out_symbol);
  }

  public static int round(double value)
  {
    return (int)round(value, 0);
  }

  public static double round(double value, int digits)
  {
    return new BigDecimal(value).setScale(digits * -1, 4).doubleValue();
  }

  public static int roundDown(double value)
  {
    return (int)roundDown(value, 0);
  }

  public static double roundDown(double value, int digits)
  {
    return new BigDecimal(value).setScale(digits * -1, 1).doubleValue();
  }

  public static int roundUp(double value)
  {
    return (int)roundUp(value, 0);
  }

  public static double roundUp(double value, int digits)
  {
    return new BigDecimal(value).setScale(digits * -1, 0).doubleValue();
  }

  public static String amount(double value)
  {
    return amount((int)value);
  }

  public static String amount(int value)
  {
    String amount = "￦";
    amount = amount + addSymbol(value, ",");
    return amount;
  }

  public static String decimal(double value, String format)
  {
    return replaceAll(new DecimalFormat(format).format(value), "-0", "0");
  }

  public static String decimal(String value, String point_format)
  {
    if (value == null)
      return "null";
    try
    {
      String disit1 = value;
      String disit2 = "";
      if (value.indexOf(".") > -1) {
        disit1 = value.substring(0, value.indexOf("."));
        disit2 = value.substring(value.indexOf("."));
      }
      String re_disit1 = addSymbol(disit1, ",", 3);
      String re_disit2 = disit2;
      if (disit2.indexOf(".") > -1) {
        re_disit2 = decimal(toDouble("0" + disit2), point_format);
      }
      return re_disit1 + (re_disit2.indexOf(".") > -1 ? re_disit2.substring(re_disit2.indexOf("0.") + 1) : ""); } catch (Exception e) {
    }
    return value;
  }

  public static String shortSubstring(String value, int length)
  {
    return value.length() > length ? value.substring(0, length - 3) + "..." : value;
  }

  public static String shortByteString(String value, int maxbyte)
    throws Exception
  {
    if ((value == null) || (value.length() == 0)) {
      return "";
    }
    int currentByte = value.getBytes("EUC-KR").length;
    int len = value.length();
    if (currentByte > maxbyte) {
      int count = 0;

      while (count < len) {
        count++; if (value.substring(0, count).getBytes("EUC-KR").length > maxbyte - 3) {
          break;
        }
      }
      count--; return value.substring(0, count) + "...";
    }
    return value;
  }

  public static String shortByteSubString(String value, int maxbyte)
    throws Exception
  {
    if ((value == null) || (value.length() == 0)) {
      return "";
    }
    int currentByte = value.getBytes("EUC-KR").length;
    int len = value.length();
    if (currentByte > maxbyte) {
      int count = 0;

      while (count < len) {
        count++; if (value.substring(0, count).getBytes("EUC-KR").length > maxbyte) {
          break;
        }
      }
      count--; return value.substring(0, count);
    }
    return value;
  }

  public static String sortInteger(String value, String symbol, int order)
  {
    String[] value_array = toString(value).split(symbol);

    ArrayList arrayList = new ArrayList();
    for (int i = 0; i < value_array.length; i++) {
      arrayList.add(Integer.valueOf(toInt(value_array[i])));
    }
    Collections.sort(arrayList);

    String re_value = "";
    if (order == 0) {
      for (int i = 0; i < arrayList.size(); i++) {
        re_value = re_value + ((Integer)arrayList.get(i)).toString();
        if (i < arrayList.size() - 1)
          re_value = re_value + symbol;
      }
    }
    else {
      for (int i = arrayList.size() - 1; i >= 0; i--) {
        re_value = re_value + ((Integer)arrayList.get(i)).toString();
        if (i > 0) {
          re_value = re_value + symbol;
        }
      }
    }
    return re_value;
  }
  public static String sortString(String value, String symbol, int order) {
    String[] value_array = toString(value).split(symbol);

    ArrayList arrayList = new ArrayList();
    for (int i = 0; i < value_array.length; i++) {
      arrayList.add(value_array[i]);
    }
    Collections.sort(arrayList);

    String re_value = "";
    if (order == 0) {
      for (int i = 0; i < arrayList.size(); i++) {
        re_value = re_value + (String)arrayList.get(i);
        if (i < arrayList.size() - 1)
          re_value = re_value + symbol;
      }
    }
    else {
      for (int i = arrayList.size() - 1; i >= 0; i--) {
        re_value = re_value + (String)arrayList.get(i);
        if (i > 0) {
          re_value = re_value + symbol;
        }
      }
    }
    return re_value;
  }

  public static String[] sortIntegerArray(String key_str, String value_str, String symbol, int order)
  {
    HashMap keyvalue = new HashMap();
    String[] key = key_str.split(symbol);
    String[] value = value_str.split(symbol);
    for (int i = 0; i < key.length; i++) {
      keyvalue.put(key[i], value[i]);
    }
    String re_value_str = "";
    String re_key_str = sortInteger(key_str, symbol, order);
    String[] re_key_array = re_key_str.split(symbol);
    for (int i = 0; i < key.length; i++) {
      re_value_str = re_value_str + (String)keyvalue.get(re_key_array[i]);
      if (i < key.length - 1) {
        re_value_str = re_value_str + ",";
      }
    }
    String[] re_array_value = { re_key_str, re_value_str };
    return re_array_value;
  }
  public static String[] sortStringArray(String key_str, String value_str, String symbol, int order) {
    HashMap keyvalue = new HashMap();
    String[] key = key_str.split(symbol);
    String[] value = value_str.split(symbol);
    for (int i = 0; i < key.length; i++) {
      keyvalue.put(key[i], value[i]);
    }
    String re_value_str = "";
    String re_key_str = sortString(key_str, symbol, order);
    String[] re_key_array = re_key_str.split(symbol);
    for (int i = 0; i < key.length; i++) {
      re_value_str = re_value_str + (String)keyvalue.get(re_key_array[i]);
      if (i < key.length - 1) {
        re_value_str = re_value_str + ",";
      }
    }
    String[] re_array_value = { re_key_str, re_value_str };
    return re_array_value;
  }

  public static String limitByteAndEnterAddBrTag(String value, int maxbyte)
    throws Exception
  {
    if ((value == null) || (value.length() == 0)) {
      return "";
    }
    int currentByte = 0;
    StringBuffer strbuff = new StringBuffer();
    for (int len = 0; len < value.length(); len++) {
      String temp_cahr = value.substring(len, len + 1);
      currentByte += temp_cahr.getBytes("EUC-KR").length;
      strbuff.append(temp_cahr);

      if (((maxbyte > 0) && (currentByte >= maxbyte)) || (temp_cahr.equals("\n"))) {
        strbuff.append("#LINE#");
        currentByte = 0;
      }
    }
    String[] str_array = strbuff.toString().split("#LINE#");
    strbuff = new StringBuffer();
    for (int i = 0; i < str_array.length; i++) {
      String line_str = str_array[i].trim();
      if (line_str.length() > 0) {
        strbuff.append(line_str + "<br />");
      }
      else if (str_array[i].length() == 0) {
        strbuff.append("<br />");
      }
    }

    return strbuff.toString();
  }

  public static String limitByteAddBrTag(String value, int maxbyte)
    throws Exception
  {
    if ((value == null) || (value.length() == 0)) {
      return "";
    }
    int currentByte = 0;
    StringBuffer strbuff = new StringBuffer();
    for (int len = 0; len < value.length(); len++) {
      String temp_cahr = value.substring(len, len + 1);
      currentByte += temp_cahr.getBytes("EUC-KR").length;
      strbuff.append(temp_cahr);

      if (currentByte >= maxbyte) {
        strbuff.append("<br />");
        currentByte = 0;
      }
    }
    return strbuff.toString();
  }

  public static String lpad(String value, int len, String symbol)
  {
    String lpad = "";
    String SYMBOL = symbol.substring(0, 1);
    if (len > value.length()) {
      for (int i = 0; i < len - value.length(); i++) {
        lpad = lpad + SYMBOL;
      }
    }
    lpad = lpad + value;
    return lpad;
  }

  public static String rpad(String value, int len, String symbol)
  {
    String rpad = value;
    String SYMBOL = symbol.substring(0, 1);
    if (len > value.length()) {
      for (int i = 0; i < len - value.length(); i++) {
        rpad = rpad + SYMBOL;
      }
    }
    return rpad;
  }

  public static String substringBefore(String value, String search)
  {
    return value.indexOf(search) == -1 ? value : value.substring(0, value.indexOf(search));
  }

  public static String substringBefore2(String value, String search)
  {
    return value.indexOf(search) == -1 ? "" : value.substring(0, value.indexOf(search));
  }

  public static String substringAfter(String value, String search)
  {
    return value.indexOf(search) == -1 ? value : value.substring(value.indexOf(search) + search.length());
  }

  public static String substringAfter2(String value, String search)
  {
    return value.indexOf(search) == -1 ? "" : value.substring(value.indexOf(search) + search.length());
  }

  public static String encode(String value)
  {
    return encode(value, DEFAULT_ENCODING);
  }

  public static String encode(String value, String encodeing)
  {
    String result = "";
    try {
      result = URLEncoder.encode(value, encodeing);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public static String decode(String value)
  {
    return decode(value, DEFAULT_ENCODING);
  }

  public static String decode(String value, String enconding)
  {
    String result = "";
    try {
      result = URLDecoder.decode(value, enconding);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public static String base64Encode(String str)
  {
    String result = "";
    BASE64Encoder encoder = new BASE64Encoder();
    byte[] b1 = str.getBytes();
    result = encoder.encode(b1);
    return result;
  }

  public static String base64Decode(String str)
  {
    String result = "";
    try {
      BASE64Decoder decoder = new BASE64Decoder();
      byte[] b1 = decoder.decodeBuffer(str);
      result = new String(b1); } catch (Exception localException) {
    }
    return result;
  }

  public static String addSymbol(double value)
  {
    return NumberFormat.getNumberInstance().format(value);
  }

  public static String addSymbol(double value, String symbol)
  {
    return NumberFormat.getNumberInstance().format(value).replaceAll(",", symbol);
  }

  public static String addSymbol(String value, String symbol, int space_cnt)
  {
    if (value == null)
      return "null";
    try
    {
      String re_value = "";
      int cnt = 0;
      for (int i = value.length() - 1; i >= 0; i--) {
        cnt++;
        if ((cnt > 1) && (cnt % space_cnt == 1)) {
          re_value = symbol + re_value;
        }
        re_value = value.charAt(i) + re_value;
      }
      return re_value; } catch (Exception e) {
    }
    return value;
  }

  public static String addZero(String value)
  {
    return value.length() == 1 ? "0" + value : value;
  }

  public static String addZero(int value)
  {
    return addZero(value);
  }

  public static int getByteLength(String value)
  {
    int getByte = 0;
    try {
      getByte = value.getBytes("EUC-KR").length; } catch (Exception localException) {
    }
    return getByte;
  }

  public static int getMaxDay(int year, int month)
  {
    Calendar cal = Calendar.getInstance();
    cal.set(year, month - 1, 1);
    return cal.getActualMaximum(5);
  }
  public static int getMaxDay(String ymd) {
    String[] ymd_array = toStr_array(ymd, "-", 3);
    return getMaxDay(toInt(ymd_array[0]), toInt(ymd_array[1]));
  }

  public static int getTotalWeekTime(int year, int month)
  {
    return roundUp((getMaxDay(year, month) + getWeek(year, month, 1) - 1) / 7.0D);
  }
  public static int getTotalWeekTime(String ymd) {
    String[] ymd_array = toStr_array(ymd, "-", 3);
    return roundUp((getMaxDay(ymd) + getWeek(toInt(ymd_array[0]), toInt(ymd_array[1]), 1) - 1) / 7.0D);
  }

  public static int getWeekTime(String ymd)
  {
    return roundUp((toInt(ymd.substring(8)) + getWeek(ymd.substring(0, 7) + "-01") - 1) / 7.0D);
  }

  public static int getWeekDay(String ym, int weektime, int weekno)
  {
    return getWeekFirstDay(ym, weektime) + weekno - 1;
  }
  public static int getWeekDay(int year, int month, int weektime, int weekno) {
    return getWeekFirstDay(year, month, weektime) + weekno - 1;
  }

  public static String getWeekYmd(String ym, int weektime, int weekno)
    throws Exception
  {
    int day = getWeekFirstDay(ym, weektime) + weekno - 1;
    String ymd = "";
    if (day > 0)
      ymd = ym + "-" + addZero(day);
    else {
      ymd = getYmdAddDay(toDate(ym + "-01"), day - 1);
    }
    return ymd;
  }

  public static String getWeekYmd(int year, int month, int weektime, int weekno) throws Exception {
    int day = getWeekFirstDay(year, month, weektime) + weekno - 1;
    String ymd = "";
    if (day > 0)
      ymd = year + "-" + addZero(month) + "-" + addZero(day);
    else {
      ymd = getYmdAddDay(toDate(year + "-" + addZero(month) + "-01"), day - 1);
    }
    return ymd;
  }

  public static int getWeek(int year, int month, int day)
  {
    Calendar cal = Calendar.getInstance();
    cal.set(year, month - 1, day);
    return cal.get(7);
  }
  public static int getWeek(String ymd) {
    String[] ymd_array = toStr_array(ymd, "-", 3);
    return getWeek(toInt(ymd_array[0]), toInt(ymd_array[1]), toInt(ymd_array[2]));
  }

  public static int getWeekFirstDay(int year, int month, int weektime)
  {
    return 1 - (getWeek(year, month, 1) - 1) + 7 * (weektime - 1);
  }

  public static int getWeekFirstDay(String ym, int weektime) {
    String[] ym_array = toStr_array(ym, "-", 2);
    return getWeekFirstDay(toInt(ym_array[0]), toInt(ym_array[1]), weektime);
  }

  public static int getTotalWeek(int year, int month)
  {
    return roundUp((getMaxDay(year, month) + getWeek(year, month, 1) - 1) / 7.0D);
  }
  public static int getTotalWeek(String ymd) {
    String[] ymd_array = toStr_array(ymd, "-", 3);
    return roundUp((getMaxDay(ymd) + getWeek(toInt(ymd_array[0]), toInt(ymd_array[1]), 1) - 1) / 7.0D);
  }

  public static String getWeekKor(int year, int month, int day)
  {
    int week = getWeek(year, month, day);
    String[] weekkor = { "일", "월", "화", "수", "목", "금", "토" };
    return weekkor[(week - 1)];
  }
  public static String getWeekKor(String ymd) {
    String[] ymd_array = toStr_array(ymd, "-", 3);
    return getWeekKor(toInt(ymd_array[0]), toInt(ymd_array[1]), toInt(ymd_array[2]));
  }

  public static int getYear()
  {
    Calendar cal = Calendar.getInstance();
    return cal.get(1);
  }

  public static int getMonth()
  {
    Calendar cal = Calendar.getInstance();
    return cal.get(2) + 1;
  }

  public static int getDay()
  {
    Calendar cal = Calendar.getInstance();
    return cal.get(5);
  }

  public static Date getDateAddDay(int day)
  {
    Date date = new Date();
    date.setTime(date.getTime() + 86400000L * day);
    return date;
  }

  public static String getYmdAddDay(int day)
  {
    return new SimpleDateFormat("yyyy-MM-dd").format(getDateAddDay(day));
  }

  public static Date getDateAddDay(Date date, int day)
  {
    date.setTime(date.getTime() + 86400000L * day);
    return date;
  }

  public static String getYmdAddDay(Date date, int day)
  {
    return new SimpleDateFormat("yyyy-MM-dd").format(getDateAddDay(date, day));
  }

  public static String getYmdAddMonth(String value_ymd, int add_month)
  {
    String[] s_day_ymd = toStr_array(value_ymd, "-", 3);

    int total_month = toInt(s_day_ymd[0]) * 12 + toInt(s_day_ymd[1]) + add_month;
    int e_year = total_month / 12;
    int e_month = total_month % 12;

    if (e_month == 0) {
      e_month = 12;
      e_year--;
    }

    int e_day = toInt(s_day_ymd[2]);
    int e_max_day = getMaxDay(e_year, e_month);

    if (e_day > e_max_day)
      return e_year + "-" + addZero(e_month) + "-" + addZero(e_max_day);
    try
    {
      return toDateFormat(getDateAddDay(toDate(e_year + "-" + addZero(e_month) + "-" + addZero(e_day)), -1), "yyyy-MM-dd"); } catch (Exception e) {
    }
    return "";
  }

  public static String getYmAddMonth(String value_ym, int add_month)
  {
    String[] s_day_ymd = toStr_array(value_ym, "-", 2);

    int total_month = toInt(s_day_ymd[0]) * 12 + toInt(s_day_ymd[1]) + add_month;
    int e_year = total_month / 12;
    int e_month = total_month % 12;

    if (e_month == 0) {
      e_month = 12;
      e_year--;
    }
    try
    {
      return e_year + "-" + addZero(e_month); } catch (Exception e) {
    }
    return "";
  }

  public static String getYmdAddMonth(Date date, int add_month)
  {
    return getYmdAddMonth(toDateFormat(date, "yyyy-MM-dd"), add_month);
  }

  public static int getSubDays(String ymd1, String ymd2)
  {
    try
    {
      return toInt((toLong(toTimestamp(new StringBuilder(String.valueOf(ymd1)).append(" 00:00:00").toString())) - toLong(toTimestamp(new StringBuilder(String.valueOf(ymd2)).append(" 00:00:00").toString()))) / 24L / 60L / 60L / 1000L); } catch (Exception e) {
    }
    return 0;
  }

  public static int getSubDays(Date date1, Date date2) {
    return getSubDays(toDateFormat(date1, "yyyy-MM-dd"), toDateFormat(date2, "yyyy-MM-dd"));
  }
  public static int getSubDays(Date date1, String ymd2) {
    return getSubDays(toDateFormat(date1, "yyyy-MM-dd"), ymd2);
  }
  public static int getSubDays(String ymd1, Date date2) {
    return getSubDays(ymd1, toDateFormat(date2, "yyyy-MM-dd"));
  }

  public static int getSubDays(Date date)
  {
    return getSubDays(toDateFormat(new Date(), "yyyy-MM-dd"), toDateFormat(date, "yyyy-MM-dd"));
  }
  public static int getSubDays(String ymd) {
    return getSubDays(toDateFormat(new Date(), "yyyy-MM-dd"), ymd);
  }

  public static int[] getImgSize(String src)
    throws IOException
  {
    FileInputStream srcls = null;
    int[] size = new int[2];
    try {
      srcls = new FileInputStream(src);
      BufferedImage srcImg = ImageIO.read(srcls);

      size[0] = srcImg.getWidth();
      size[1] = srcImg.getHeight();
    } catch (Exception e) {
      size[0] = 0;
      size[1] = 0;

      if (srcls != null) try { srcls.close(); } catch (IOException localIOException) {
        }  } finally {
      if (srcls != null) try { srcls.close(); } catch (IOException localIOException1) {
        } 
    }
    return size;
  }

  public static int[] getImgReSize(int[] img_max, String img_context)
    throws IOException
  {
    return getImgReSize(img_max[0], img_max[1], img_context);
  }
  public static int[] getImgReSize(int img_max_wsz, int img_max_hsz, String img_context) throws IOException {
    int[] img_sz = { img_max_wsz, img_max_hsz };
    if ((img_context != null) && (img_context.length() > 0)) {
      img_sz = getImgSize(img_context);
    }
    return getImgReSize(img_max_wsz, img_max_hsz, img_sz[0], img_sz[1]);
  }
  public static int[] getImgReSize(int img_max_wsz, int img_max_hsz, int[] img_sz) throws IOException {
    return getImgReSize(img_max_wsz, img_max_hsz, img_sz[0], img_sz[1]);
  }
  public static int[] getImgReSize(int[] img_max, int[] img_sz) throws IOException {
    return getImgReSize(img_max[0], img_max[1], img_sz[0], img_sz[1]);
  }

  public static int[] getImgReSize(int img_max_wsz, int img_max_hsz, int img_wsz, int img_hsz) throws IOException {
    double resize_w = img_wsz;
    double resize_h = img_hsz;
    double rate = 1.0D;
    if (img_max_wsz < resize_w) {
      rate = img_max_wsz / resize_w;
      resize_w = img_max_wsz;
      resize_h *= rate;
    }
    if (img_max_hsz < resize_h) {
      rate = img_max_hsz / resize_h;
      resize_h = img_max_hsz;
      resize_w *= rate;
    }
    img_max_wsz = toInt(decimal(resize_w, "#"));
    img_max_hsz = toInt(decimal(resize_h, "#"));

    return new int[] { img_max_wsz, img_max_hsz };
  }

  public static String getFileType(String file_nm)
  {
    return (file_nm == null) || (file_nm.indexOf(".") == -1) ? "" : file_nm.substring(file_nm.lastIndexOf(".") + 1).toLowerCase();
  }

  public static String getFileName(String file_nm)
  {
    if ((file_nm == null) || (file_nm.indexOf(".") == -1)) {
      return "";
    }
    if (file_nm.lastIndexOf("/") > -1) {
      return file_nm.substring(file_nm.lastIndexOf("/") + 1, file_nm.lastIndexOf("."));
    }
    return file_nm.substring(file_nm.lastIndexOf("\\") + 1, file_nm.lastIndexOf("."));
  }

  public static String getFileFullName(String file_nm)
  {
    if (file_nm == null) {
      return "";
    }
    if (file_nm.lastIndexOf("/") > -1) {
      return file_nm.substring(file_nm.lastIndexOf("/") + 1);
    }
    return file_nm.substring(file_nm.lastIndexOf("\\") + 1);
  }

  public static int getScreen_w_size()
  {
    return (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
  }

  public static int getScreen_h_size()
  {
    return (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
  }
/*
  public static String getUrl_doc_read(String url)
  {
    if (isUrlConnection(url))
    {
      StringBuffer textBuffer = new StringBuffer();
      try {
        InputStream fin = new URL(url).openStream();
        byte[] byte_size = new byte[4096];
        int n;
        while ((n = fin.read(byte_size)) != -1)
        {
          int n;
          textBuffer.append(new String(byte_size, 0, n));
        }
        fin.close(); } catch (Exception localException) {
      }
      return textBuffer.toString();
    }
    return "";
  }
*/
  /*public static String getUrl_doc_read(String url, String encoding) {
    if (isUrlConnection(url))
    {
      StringBuffer textBuffer = new StringBuffer();
      try {
        InputStream input = new URL(url).openStream();
        InputStreamReader reader = new InputStreamReader(input, encoding);
        BufferedReader buffer = new BufferedReader(reader);
        String inStr = "";
        while ((inStr = buffer.readLine()) != null) {
          textBuffer.append(inStr + "\n");
        }
        input.close();
        reader.close();
        buffer.close(); } catch (Exception localException) {
      }
      return textBuffer.toString();
    }
    return "";
  }
*/
  public static String getBrowserVer(HttpServletRequest request)
  {
    String user_agent = request.getHeader("user-agent");

    String browser_ver = "";
    if (user_agent.indexOf("Firefox") > -1)
      browser_ver = "FIREFOX";
    else if (user_agent.indexOf("Opera") > -1)
      browser_ver = "OPERA";
    else if (user_agent.indexOf("Chrome") > -1)
      browser_ver = "CHROME";
    else if (user_agent.indexOf("Safari") > -1)
      browser_ver = "SAFARI";
    else if (user_agent.indexOf("MSIE 10.0") > -1)
      browser_ver = "IE10.0";
    else if (user_agent.indexOf("MSIE 9.0") > -1)
      browser_ver = "IE9.0";
    else if (user_agent.indexOf("MSIE 8.0") > -1)
      browser_ver = "IE8.0";
    else if (user_agent.indexOf("MSIE 7.0") > -1)
      browser_ver = "IE7.0";
    else if (user_agent.indexOf("MSIE 6.0") > -1)
      browser_ver = "IE6.0";
    else if (user_agent.indexOf("bingbot") > -1)
      browser_ver = "BINGBOT";
    else if (user_agent.indexOf("NT") > -1) {
      browser_ver = "IE";
    }
    return browser_ver;
  }

  public static Object setProperty(HttpServletRequest request, Class cls)
    throws Exception
  {
    Object result = cls.newInstance();
    Method[] mts = result.getClass().getMethods();
    Enumeration pnames = request.getParameterNames();

    while (pnames.hasMoreElements()) {
      String pname = (String)pnames.nextElement();
      String pvalue = request.getParameter(pname);
      for (Method method : mts) {
        String mname = method.getName();
        if (!mname.startsWith("set"))
          continue;
        String fieldname = method.getName().substring(3);

        if (pname.equalsIgnoreCase(fieldname)) try {
            method.invoke(result, new Object[] { pvalue });
          } catch (Exception localException) {
          } 
      }
    }
    return result;
  }

  public static Object setProperty(Map<String, Object> map, Class cls) throws Exception {
    Object result = cls.newInstance();
    Method[] mts = result.getClass().getMethods();
    for (Map.Entry keyValue : map.entrySet())
      for (Method method : mts) {
        String mname = method.getName();
        if (!mname.startsWith("set"))
          continue;
        String fieldname = method.getName().substring(3);

        if (((String)keyValue.getKey()).equalsIgnoreCase(fieldname)) try {
            method.invoke(result, new Object[] { keyValue.getValue() });
          }
          catch (Exception localException) {
          } 
      }
    return result;
  }

  public static boolean isDaysNew(long value, int days)
  {
    return isDaysNew(toDate(value), days);
  }

  public static boolean isDaysNew(Date value, int days)
  {
    Date date = new Date();
    String toDay = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss").format(date);
    String startDay = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss").format(value);
    date.setTime(value.getTime() + days * 24L * 60L * 60L * 1000L);
    String endDay = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss").format(date);

    return (toDay.compareTo(startDay) >= 0) && (toDay.compareTo(endDay) <= 0);
  }

  public static boolean isEqual(String value1, String value2)
  {
    if ((value1 == null) || (value1.trim().equals("")) || (value1.equals("null"))) {
      return false;
    }
    return value1.equals(value2);
  }

  public static boolean isIDType(String value)
  {
    if (value.length() == 0)
      return false;
    value = value.toUpperCase();
    if (('A' > value.charAt(0)) || (value.charAt(0) > 'Z'))
      return false;
    for (int i = 1; i < value.length(); i++) {
      if ((('A' > value.charAt(i)) || (value.charAt(i) > 'Z')) && (('0' > value.charAt(i)) || (value.charAt(i) > '9')) && (value.charAt(i) != '_'))
        return false;
    }
    return true;
  }

  public static boolean isEmpty(String value)
  {
    return (value == null) || (value.trim().length() == 0);
  }

  public static boolean isFirefox(HttpServletRequest request)
  {
    return getBrowserVer(request).equals("FIREFOX");
  }

  public static boolean isOpera(HttpServletRequest request)
  {
    return getBrowserVer(request).equals("OPERA");
  }

  public static boolean isChrome(HttpServletRequest request)
  {
    return getBrowserVer(request).equals("CHROME");
  }

  public static boolean isIE6(HttpServletRequest request)
  {
    return getBrowserVer(request).equals("IE6.0");
  }

  public static boolean isIE7(HttpServletRequest request)
  {
    return getBrowserVer(request).equals("IE7.0");
  }

  public static boolean isIE8(HttpServletRequest request)
  {
    return getBrowserVer(request).equals("IE8.0");
  }

  public static boolean isIE9(HttpServletRequest request)
  {
    return getBrowserVer(request).equals("IE9.0");
  }

  public static boolean isIE10(HttpServletRequest request)
  {
    return getBrowserVer(request).equals("IE10.0");
  }

  public static boolean isEqualValue(String value_str, String symbol, String cfvalue)
  {
    boolean validity = false;
    if ((value_str != null) && (symbol != null) && (cfvalue != null)) {
      String[] value_str_array = value_str.split(symbol);

      for (String temp : value_str_array) {
        if (temp.equals(cfvalue)) {
          validity = true;
          break;
        }
      }
    }
    return validity;
  }

  public static String isEqualValueRtnString(String value_str, String symbol, String cfvalue) {
    String validity = "";
    if ((value_str != null) && (symbol != null) && (cfvalue != null)) {
      String[] value_str_array = value_str.split(symbol);

      for (String temp : value_str_array) {
        if (temp.equals(cfvalue)) {
          validity = cfvalue;
          break;
        }
      }
    }
    return validity;
  }

  public static boolean isImageFile(String file_nm)
  {
    return isEqualValue("gif,jpg,png,jpeg", ",", getFileType(file_nm));
  }

  public static boolean isIndexOfValue(String value_str, String symbol, String cfvalue)
  {
    boolean validity = false;
    if ((value_str != null) && (symbol != null) && (cfvalue != null)) {
      String[] value_str_array = value_str.split(symbol);

      for (String temp : value_str_array) {
        if (cfvalue.indexOf(temp) > -1) {
          validity = true;
          break;
        }
      }
    }
    return validity;
  }
/*
  public static boolean isUrlConnection(String url)
  {
    boolean isUrlOk = false;
    try {
      URL urlobj = new URL(url);
      URLConnection con = urlobj.openConnection();
      con.setConnectTimeout(500);
      con.setReadTimeout(500);
      HttpURLConnection exitCode = (HttpURLConnection)con;
      isUrlOk = exitCode.getResponseCode().equals("200");
    } catch (Exception localException) {
    }
    return isUrlOk;
  }
*/
  public static String selected(String orgValue, String cfValue)
  {
    return orgValue.equals(cfValue) ? "selected=\"selected\"" : "";
  }
  public static String selected(int orgValue, String cfValue) {
    return selected(orgValue, cfValue);
  }
  public static String selected(String orgValue, int cfValue) {
    return selected(orgValue, cfValue);
  }
  public static String selected(int orgValue, int cfValue) {
    return selected(orgValue, cfValue);
  }
  public static String selected(String orgValue, String symbol, String cfValue) {
    String[] orgValue_array = orgValue.split(symbol);
    boolean isChk = (isEmpty(orgValue)) || (isEmpty(symbol)) || (isEmpty(cfValue));
    for (int i = 0; i < orgValue_array.length; i++) {
      if (orgValue_array[i].equals(cfValue)) {
        isChk = true;
      }
    }
    return isChk ? "selected=\"selected\"" : "";
  }
  public static String selected(int orgValue, String symbol, String cfValue) {
    return selected(orgValue, symbol, cfValue);
  }
  public static String selected(String orgValue, String symbol, int cfValue) {
    return selected(orgValue, symbol, cfValue);
  }

  public static String checked(String orgValue, String cfValue)
  {
    return orgValue.equals(cfValue) ? "checked=\"checked\"" : "";
  }
  public static String checked(int orgValue, String cfValue) {
    return checked(orgValue, cfValue);
  }
  public static String checked(String orgValue, int cfValue) {
    return checked(orgValue, cfValue);
  }
  public static String checked(int orgValue, int cfValue) {
    return checked(orgValue, cfValue);
  }
  public static String checked(String orgValue, String symbol, String cfValue) {
    String[] orgValue_array = orgValue.split(symbol);
    boolean isChk = (isEmpty(orgValue)) || (isEmpty(symbol)) || (isEmpty(cfValue));
    for (int i = 0; i < orgValue_array.length; i++) {
      if (orgValue_array[i].equals(cfValue)) {
        isChk = true;
      }
    }
    return isChk ? "checked=\"checked\"" : "";
  }
  public static String checked(int orgValue, String symbol, String cfValue) {
    return checked(orgValue, symbol, cfValue);
  }
  public static String checked(String orgValue, String symbol, int cfValue) {
    return checked(orgValue, symbol, cfValue);
  }

  public static String flashWrite(String url, int w, int h, String id, String bg, String win, String title, String imagesrc)
  {
    return flashWrite(url, w, h, id, bg, win, title, imagesrc);
  }
  public static String flashWrite(String url, String w, String h, String id, String bg, String win, String title, String imagesrc) {
    if (toString(title).length() == 0) {
      title = "비주얼 플래시 입니다.";
    }
    return flashWrite(url, w, h, id, bg, win, "playerMode=embedded", title, imagesrc, title);
  }
  /*public static String flashWrite(String url, int w, int h, String id, String bg, String win, String title, String imagesrc, String flash_text) {
    return flashWrite(url, w, h, id, bg, win, "playerMode=embedded", title, imagesrc, flash_text);
  }*/
  public static String flashWrite(String url, String w, String h, String id, String bg, String win, String flash_vars, String title, String imagesrc, String flash_text) {
    if (toString(title).length() == 0) {
      title = "비주얼 플래시 입니다.";
    }
    StringBuffer flashStr = new StringBuffer();

    flashStr.append("<object type=\"application/x-shockwave-flash\" data=\"" + url + "\" width=\"" + w + "\" height=\"" + h + "\" id=\"" + id + "\" align=\"middle\" title=\"" + title + "\" style=\"outline:none\" > \n");
    flashStr.append("\t<param name=\"allowScriptAccess\" value=\"always\" /> \n");
    flashStr.append("\t<param name=\"movie\" value=\"" + url + "\" /> \n");
    flashStr.append("\t<param name=\"FlashVars\" value=\"" + flash_vars + "\" /> \n");
    flashStr.append("\t<param name=\"wmode\" value=\"" + win + "\" /> \n");
    flashStr.append("\t<param name=\"menu\" value=\"false\" /> \n");
    flashStr.append("\t<param name=\"quality\" value=\"high\" />\t\n");

    if ((bg != null) && (bg.length() > 0)) {
      flashStr.append("\t<param name=\"bgcolor\" value=\"" + bg + "\" /> \n");
    }
    flashStr.append("\t" + flash_text + "\n");
    if ((imagesrc != null) && (imagesrc.length() > 0)) {
      flashStr.append("\t<img src=\"" + imagesrc + "\" width=\"" + w + "\" height=\"" + h + "\" alt=\"" + title + "\" />\n");
    }

    flashStr.append("</object> \n");
    return flashStr.toString();
  }

  public static String movieWrite(HttpServletRequest request, String movie_file, int width, int height, String movie_title)
  {
    int AutoStart = 0;
    int AutoSize = 0;
    int AnimationAtStart = 1;
    int DisplaySize = 0;
    boolean EnableContextMenu = false;
    int EnablePositionControls = -1;
    int EnableFullScreenControls = 0;
    int Mute = 0;
    int stretchToFit = 0;
    int ShowCaptioning = 0;
    int ShowControls = 1;
    int ShowAudioControls = 1;
    int ShowDisplay = 0;
    int ShowGotoBar = 0;
    int ShowPositionControls = -1;
    int ShowStatusBar = 1;
    int ShowTracker = -1;
    int Volume = 100;
    int SendMouseClickEvents = -1;

    return movieWrite(request, movie_file, width, height, movie_title, AutoStart, AutoSize, AnimationAtStart, DisplaySize, EnableContextMenu, EnablePositionControls, EnableFullScreenControls, Mute, stretchToFit, ShowCaptioning, ShowControls, ShowAudioControls, ShowDisplay, ShowGotoBar, ShowPositionControls, ShowStatusBar, ShowTracker, Volume, SendMouseClickEvents);
  }
  public static String movieWrite(HttpServletRequest request, String movie_file, int width, int height, String movie_title, int AutoStart, int AutoSize, int AnimationAtStart, int DisplaySize, boolean EnableContextMenu, int EnablePositionControls, int EnableFullScreenControls, int Mute, int stretchToFit, int ShowCaptioning, int ShowControls, int ShowAudioControls, int ShowDisplay, int ShowGotoBar, int ShowPositionControls, int ShowStatusBar, int ShowTracker, int Volume, int SendMouseClickEvents) {
    boolean isFireFox = isFirefox(request);
    boolean isOpera = isOpera(request);
    boolean isChrome = isChrome(request);
    if (movie_file.indexOf("&amp;") == -1) {
      movie_file = movie_file.replaceAll("&", "&amp;");
    }

    HashMap typehash = new HashMap();

    typehash.put("mpg", "application/x-mplayer2");
    typehash.put("mpeg", "application/x-mplayer2");
    typehash.put("avi", "application/x-mplayer2");
    typehash.put("asf", "application/x-mplayer2");
    typehash.put("asx", "application/x-mplayer2");
    typehash.put("wm", "application/x-mplayer2");
    typehash.put("wmv", "application/x-mplayer2");
    typehash.put("wvx", "application/x-mplayer2");
    typehash.put("mp3", "application/x-mplayer2");
    typehash.put("mid", "application/x-mplayer2");
    typehash.put("wav", "application/x-mplayer2");
    typehash.put("wma", "application/x-mplayer2");
    typehash.put("wax", "application/x-mplayer2");
    typehash.put("ram", "application/x-mplayer2");
    typehash.put("rm", "application/x-mplayer2");

    typehash.put("mov", "vidio/quicktime");
    typehash.put("mp4", "vidio/quicktime");

    typehash.put("swf", "application/x-schockwave-flash");
    typehash.put("swc", "application/x-schockwave-flash");

    String fileType = movie_file.substring(movie_file.lastIndexOf(".") + 1).toLowerCase();
    String media_type = toString((String)typehash.get(fileType), "vidio/x-ms-wmv");

    String mms_plugin_file = "http://www.interoperabilitybridges.com/windows-media-player-firefox-plugin-download";
    String mms_menul_link = "http://support.mozilla.org/ko/kb/play-windows-media-files-in-firefox#w_euaeiiyiyi-ecio-ueia-oiyi";
    StringBuffer movieStr = new StringBuffer();

    movieStr.append("<object " + ((isFireFox) || (isOpera) || (isChrome) ? "type=\"" + media_type + "\" data=\"" + movie_file + "\"" : "classid=\"CLSID:22D6f312-B0F6-11D0-94AB-0080C74C7E95\"") + " title=\"" + movie_title + "\" width=\"" + width + "\" height=\"" + height + "\" > \n");
    movieStr.append("\t<param name=\"Filename\" value=\"" + movie_file + "\" /> \n");
    movieStr.append("\t<param name=\"AutoStart\" value=\"" + AutoStart + "\" /> \n");
    movieStr.append("\t<param name=\"AutoSize\" value=\"" + AutoSize + "\" /> \n");
    movieStr.append("\t<param name=\"AnimationAtStart\" value=\"" + AnimationAtStart + "\" /> \n");
    movieStr.append("\t<param name=\"DisplaySize\" value=\"" + DisplaySize + "\" /> \n");
    movieStr.append("\t<param name=\"EnableContextMenu\" value=\"" + EnableContextMenu + "\" /> \n");
    movieStr.append("\t<param name=\"EnablePositionControls\" value=\"" + EnablePositionControls + "\" /> \n");
    movieStr.append("\t<param name=\"EnableFullScreenControls\" value=\"" + EnableFullScreenControls + "\" /> \n");
    movieStr.append("\t<param name=\"Mute\" value=\"" + Mute + "\" /> \n");
    movieStr.append("\t<param name=\"stretchToFit\"  value=\"" + stretchToFit + "\" /> \n");
    movieStr.append("\t<param name=\"ShowCaptioning\" value=\"" + ShowCaptioning + "\" /> \n");
    movieStr.append("\t<param name=\"ShowControls\" value=\"" + ShowControls + "\" /> \n");
    movieStr.append("\t<param name=\"ShowAudioControls\" value=\"" + ShowAudioControls + "\" /> \n");
    movieStr.append("\t<param name=\"ShowDisplay\" value=\"" + ShowDisplay + "\" /> \n");
    movieStr.append("\t<param name=\"ShowGotoBar\" value=\"" + ShowGotoBar + "\" /> \n");
    movieStr.append("\t<param name=\"ShowPositionControls\" value=\"" + ShowPositionControls + "\" /> \n");
    movieStr.append("\t<param name=\"ShowStatusBar\" value=\"" + ShowStatusBar + "\" /> \n");
    movieStr.append("\t<param name=\"ShowTracker\" value=\"" + ShowTracker + "\" /> \n");
    movieStr.append("\t<param name=\"Volume\" value=\"" + Volume + "\" /> \n");
    movieStr.append("\t<param name=\"SendMouseClickEvents\" value=\"" + SendMouseClickEvents + "\" /> \n");

    movieStr.append("\t" + htmlSpecialChars(movie_title) + " \n");
    if ((isFireFox) || (isOpera) || (isChrome)) {
      if (fileType.equals("wmv"))
      {
        movieStr.append(" <p>플레이어 설치 안내</p>\n");
        movieStr.append(" <ol> \n");
        movieStr.append(" \t<li>동영상을 재생이 되지 않을 때에는 <a href=\"" + mms_plugin_file + "\" target=\"_blank\" title=\"새창\"><span style=\"color:#df0000\">mms 플러그인</span></a>을 설치 후 이용 바랍니다.</li> \n");
        if (isOpera)
          movieStr.append(" \t<li>플러그인 설치 후. 상단의 [메뉴(Menu)] 클릭 다음 [설정(Settings)] 다음 [환경설정(Perferences...)] 다음  [고급설정(Advanced)] 탭 선택 다음 왼쪽 [프로그램(Programs)] 선택 다음 [추가(Add..)]버튼 다음 열린 새창에서 [프로토콜(Protocol)]란에 'mms'입력후 [확인(OK)] 버튼 클릭 </li> \n");
        else if (isChrome)
          movieStr.append(" \t<li>플러그인 설치 후. [설정] 클릭 / 화면 아래 [고급 설정 표시]  / [콘텐츠설정] / [개별 플러그인 사용중지...] [Windows Media Player - 버전...] 내용에서 사용 채크 / 브라우저를 닫고 새로 열면 됩니다. </li> \n");
        else if (isFireFox) {
          movieStr.append(" \t<li>설치 메뉴얼 <a href=\"" + mms_menul_link + "\" target=\"_blank\" title=\"새창\">[링크]</a></li> \n");
        }
        movieStr.append(" </ol> \n");
      }
      else {
        movieStr.append("동영상 플레이를 지원하지 않습니다. <a href=\"" + movie_file + "\" target=\"_blank\" title=\"새창\">동영상 다운받기</a>\n");
      }

    }

    movieStr.append("</object> \n");
    return movieStr.toString();
  }

  public static String popupScript(String uri, String subject, int width, int height)
  {
    return popupScript(uri, subject, width, height, "", "");
  }
  public static String popupScript(String uri, String subject, int width, int height, String scrollbars, String resizable) {
    return "window.open(" + ("this.href".equals(uri) ? "this.href" : new StringBuilder("\"").append(uri).append("\"").toString()) + ",\"" + subject + "\",\"width=" + width + ",height=" + height + ",top=\"+((screen.height) ? (screen.height-800)/2 : 0)+\",left=\"+((screen.width) ? (screen.width-800)/2 : 0)+\",scrollbars=" + ((scrollbars == null) || (scrollbars.length() == 0) ? "no" : scrollbars) + ",resizable=" + ((resizable == null) || (resizable.length() == 0) ? "auto" : resizable) + "\");";
  }
  public static String popupScript(String uri, String subject, int width, int height, int topPos, int leftPos, String scrollbars, String resizable) {
    return "window.open(" + ("this.href".equals(uri) ? "this.href" : new StringBuilder("\"").append(uri).append("\"").toString()) + ",\"" + subject + "\",\"width=" + width + ",height=" + height + ",top=" + topPos + ",left=" + leftPos + ",scrollbars=" + ((scrollbars == null) || (scrollbars.length() == 0) ? "auto" : scrollbars) + ",resizable=" + ((resizable == null) || (resizable.length() == 0) ? "no" : resizable) + "\");";
  }

  public static String makeMD5(String str)
    throws Exception
  {
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException ex) {
      throw new Exception("String makeMD5(String)", ex);
    }
    md.update(str.getBytes());
    byte[] md5Code = md.digest();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < md5Code.length; i++) {
      String md5Char = String.format("%02x", new Object[] { Integer.valueOf(0xFF & (char)md5Code[i]) });
      sb.append(md5Char);
    }
    return sb.toString();
  }

  public static String replaceAll(String str, String s1)
  {
    return replaceAll(str, s1, "");
  }
  public static String replaceAll(String str, String s1, String s2) {
    str = toString(str);
    if ((s1 != null) && (s2 != null)) {
      StringBuffer result = new StringBuffer();
      if (s1.length() > 0) {
        String s = str;
        int index1 = 0;
        int index2 = s.indexOf(s1);

        while (index2 >= 0) {
          result.append(str.substring(index1, index2));
          result.append(s2);
          index1 = index2 + s1.length();
          index2 = s.indexOf(s1, index1);
        }
        result.append(str.substring(index1));
      } else {
        result.append(str);
      }
      return result.toString();
    }
    return str;
  }

  public static String replaceAll(String str, String[] s1_arr) {
    return replaceAll(str, s1_arr, null);
  }
  public static String replaceAll(String str, String[] s1_arr, String[] s2_arr) {
    String return_str = str;
    if ((s1_arr != null) && (s1_arr.length > 0)) {
      try {
        for (int i = 0; i < s1_arr.length; i++)
          if ((s2_arr != null) && (i < s2_arr.length))
            return_str = replaceAll(return_str, toString(s1_arr[i]), toString(s2_arr[i]));
          else
            return_str = replaceAll(return_str, toString(s1_arr[i]), "");
      }
      catch (Exception localException)
      {
      }
    }
    return return_str;
  }
  public static String replaceAll(String str, String s1str, String s2str, String split_symbol) {
    return replaceAll(str, toStr_array(s1str, split_symbol), toStr_array(s2str, split_symbol));
  }

  public static String replaceAllLowerCase(String str, String s1)
  {
    return replaceAllLowerCase(str, s1, "");
  }
  public static String replaceAllLowerCase(String str, String s1, String s2) {
    str = toString(str);
    if ((s1 != null) && (s2 != null)) {
      StringBuffer result = new StringBuffer();
      if (s1.length() > 0)
      {
        String s = str.toLowerCase();
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int index1 = 0;
        int index2 = s.indexOf(s1);

        while (index2 >= 0) {
          result.append(str.substring(index1, index2));
          result.append(s2);
          index1 = index2 + s1.length();
          index2 = s.indexOf(s1, index1);
        }
        result.append(str.substring(index1));
      } else {
        result.append(str);
      }
      return result.toString();
    }
    return str;
  }

  public static String replaceAllLowerCase(String str, String[] s1_arr) {
    return replaceAllLowerCase(str, s1_arr, null);
  }
  public static String replaceAllLowerCase(String str, String[] s1_arr, String[] s2_arr) {
    String return_str = str;
    if ((s1_arr != null) && (s1_arr.length > 0)) {
      try {
        for (int i = 0; i < s1_arr.length; i++)
          if ((s2_arr != null) && (i < s2_arr.length))
            return_str = replaceAllLowerCase(return_str, toString(s1_arr[i]), toString(s2_arr[i]));
          else
            return_str = replaceAllLowerCase(return_str, toString(s1_arr[i]), "");
      }
      catch (Exception localException)
      {
      }
    }
    return return_str;
  }

  public static String replaceOne(String str, String s1, String s2)
  {
    if (str == null) {
      return "";
    }

    String before = substringBefore(str, s1);
    String after = substringAfter2(str, s1);
    if (str.equals(before)) {
      return str;
    }
    return before + s2 + after;
  }

  public static Map<String, Object> mapCopy(Map<String, Object> sorceMap, String ojbect_type_str)
  {
    return mapCopy(sorceMap, null, ojbect_type_str);
  }
  public static Map<String, Object> mapCopy(Map<String, Object> sorceMap, Map<String, Object> returnMap, String ojbect_type_str) {
    if (sorceMap == null) {
      return new HashMap();
    }
    if (returnMap == null)
      returnMap = new HashMap();
    try
    {
      if (sorceMap.size() > 0)
        for (String key : sorceMap.keySet()) {
          boolean isCopy = true;
          if ((ojbect_type_str != null) && (ojbect_type_str.length() > 0) && 
            (("," + ojbect_type_str + ",").indexOf("," + sorceMap.get(key).getClass().getCanonicalName() + ",") <= -1)) {
            isCopy = false;
          }

          if (isCopy)
            returnMap.put(key, sorceMap.get(key));
        }
    }
    catch (Exception localException) {
    }
    return returnMap;
  }

  public static Object copyProperties(Object dest, Object orig)
    throws Exception
  {
    BeanUtils.copyProperties(dest, orig);
    Method[] origMthods = orig.getClass().getMethods();
    Method[] destMthods = dest.getClass().getMethods();
    for (Method orgMethod : origMthods)
      if (orgMethod.getName().startsWith("get")) {
        Object orgValue = orgMethod.invoke(orig, new Object[0]);
        for (Method destMthod : destMthods) {
          if ((!destMthod.getName().startsWith("set")) || 
            (!orgMethod.getName().substring(3).equals(destMthod.getName().substring(3)))) continue; try {
            destMthod.invoke(dest, new Object[] { orgValue }); } catch (Exception e) { try { destMthod.invoke(dest, new Object[] { orgValue });
            } catch (Exception localException1)
            {
            } }
        }
      }
    return dest;
  }
  public static Object copyProperties(Object orig) throws Exception {
    return copyProperties(orig.getClass().newInstance(), orig);
  }

  public static String parentLocation(String uri)
  {
    return "<script type=\"text/javascript\">\n//<![CDATA[<!--\ntry{parent.window.location.href=\"" + uri + "\";}catch(e){location.href=\"" + uri + "\";}\n//-->]]>\n</script>";
  }

  public static String location(String uri)
  {
    return "<script type=\"text/javascript\">\n//<![CDATA[<!--\nlocation.href=\"" + uri + "\";\n//-->]]>\n</script>";
  }

  private static String htmlSpecialChars(String s)
  {
    StringBuffer buffer = new StringBuffer();
    StringTokenizer st = new StringTokenizer(s, "&\"<>", true);

    while (st.hasMoreTokens()) {
      String token = st.nextToken();
      switch (token.charAt(0)) { case '&':
        buffer.append("&amp;"); break;
      case '"':
        buffer.append("&quot;"); break;
      case '<':
        buffer.append("&lt;"); break;
      case '>':
        buffer.append("&gt;"); break;
      default:
        buffer.append(token);
      }
    }
    return buffer.toString();
  }
}