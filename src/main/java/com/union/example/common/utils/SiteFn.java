package com.union.example.common.utils;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SiteFn
{
  /*public static String getThumbNailImageName(String fullPath)
    throws IOException
  {
    return getThumbNailImageNameBySize(fullPath, 475, 285);
  }*/
/*
  public static String getThumbNailImageNameView(String fullPath)
  {
    String saveFullPath = "/web-data/2016_www" + File.separator + fullPath;
    Path p = Paths.get(saveFullPath, new String[0]);

    String fileName = p.getFileName().toString();
    String rtnThumbName = "";
    try
    {
      BufferedImage serverImage = ImageIO.read(new File(saveFullPath));

      int width = serverImage.getWidth();
      int height = serverImage.getHeight();

      if (serverImage.getWidth() > 859) {
        width = 860;
        height = (int)(height * (width / serverImage.getWidth()));
      }

      rtnThumbName = "thumb_" + width + "_" + height + "_" + fileName;
      String thumbSaveFullPath = saveFullPath.replace(fileName, rtnThumbName);

      File destFile = new File(thumbSaveFullPath);
      if (!destFile.isFile())
      {
        serverImage = ImageIO.read(new File(saveFullPath));
        ResampleOp resampleOp = new ResampleOp(width, height);
        resampleOp.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Soft);
        BufferedImage rescaledImage = resampleOp.filter(serverImage, null);
        ImageIO.write(rescaledImage, Util.getExtension(fileName), destFile);
      }
    } catch (IOException e) {
      return fullPath;
    }
    BufferedImage serverImage;
    return fullPath.replace(fileName, rtnThumbName);
  }
*/
  /*public static String getThumbNailImageNameBySize(String fullPath, int width, int height)
  {
    String saveFullPath = "/web-data/2016_www" + File.separator + fullPath;
    Path p = Paths.get(saveFullPath, new String[0]);

    String fileName = p.getFileName().toString();

    String rtnThumbName = "thumb_" + width + "_" + height + "_" + fileName;
    try
    {
      if ((width == 0) || (height == 0))
      {
        BufferedImage serverImage = ImageIO.read(new File(saveFullPath));
        width = 1000;
        height = (int)(serverImage.getHeight() * (width / serverImage.getWidth()));
        rtnThumbName = "thumb_" + width + "_" + height + "_" + fileName;
      }

      String thumbSaveFullPath = saveFullPath.replace(fileName, rtnThumbName);

      File destFile = new File(thumbSaveFullPath);

      if (!destFile.isFile())
      {
        BufferedImage serverImage = ImageIO.read(new File(saveFullPath));
        ResampleOp resampleOp = new ResampleOp(width, height);
        resampleOp.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Soft);
        BufferedImage rescaledImage = resampleOp.filter(serverImage, null);
        ImageIO.write(rescaledImage, Util.getExtension(fileName), destFile);
      }
    }
    catch (IOException e) {
      return fullPath;
    }

    return fullPath.replace(fileName, rtnThumbName);
  }
*/
  public static String removeTag(String value)
    throws Exception
  {
    return Util.removeTag(value);
  }

  public static String convertHtmlImg(String value)
    throws Exception
  {
    return Util.convertHtmlimg(value);
  }

  public static String getToday(String type)
    throws Exception
  {
    String strToday = "";
    Date date = new Date();
    if (!Fn.isEmpty(type)) {
      SimpleDateFormat sdf = new SimpleDateFormat(type);
      strToday = sdf.format(date);
    } else {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEE");
      strToday = sdf.format(date);
    }
    return strToday;
  }

  public static boolean isNowBetween(String startDate, String endDate)
    throws Exception
  {
    return Util.isBetweenDate(null, Fn.toDateFormat(startDate, "yyyy-MM-dd"), Fn.toDateFormat(endDate, "yyyy-MM-dd"));
  }

  public static boolean isPast(String date)
    throws Exception
  {
    return Util.isPast(Fn.toDateFormat(date, "yyyy-MM-dd"));
  }

  public static boolean isCellPhone(String phone)
    throws Exception
  {
    return Pattern.matches("^01(?:0|1[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", phone);
  }

  public static HashMap<String, String> etcDeptMap()
  {
    HashMap map = new HashMap();
    map.put("4889900", "강남 이-러닝센터");
    map.put("4889910", "강남 문화원");
    map.put("4889920", "도시관리공단");
    map.put("4889930", "구민회관");
    map.put("4889940", "기타");
    map.put("4889989", "감사의글");
    map.put("4889999", "강남구외");
    return map;
  }

  public static int toInt(Object value)
  {
    return Fn.toInt(toString(value));
  }

  /*public static int toIntRevalue(Object value, int revalue)
  {
    return Fn.toInt(value, revalue);
  }*/

  public static int[] toIntArray(Object[] value_array)
  {
    return Fn.toIntArray(value_array);
  }

  public static int toIntRequest(HttpServletRequest request, String value)
  {
    return Fn.toInt(request, value);
  }

  /*public static int toIntRequestRevalue(HttpSession request, String value, int revalue)
  {
    return Fn.toInt(request, value, revalue);
  }*/

  public static int toIntSession(HttpSession session, String value)
  {
    return Fn.toInt(session, value);
  }

  /*public static int toIntSessionRevalue(HttpSession session, String value, int revalue)
  {
    return Fn.toInt(session, value, revalue);
  }*/

  public static double toDouble(Object value)
  {
    return Fn.toDouble(value);
  }

  public static double toDoubleRevalue(String value, double revalue)
  {
    return Fn.toDouble(value, revalue);
  }

  public static double toDoubleRequest(HttpServletRequest request, String value)
  {
    return Fn.toDouble(request, value);
  }

  public static double toDoubleRequestRevalue(HttpServletRequest request, String value, double revalue)
  {
    return Fn.toDouble(request, value, revalue);
  }

  /*public static String toHangle(Object str)
  {
    return Fn.toHangle(str);
  }*/

  public static String toString(Object value)
  {
    return Fn.toString(value);
  }
  public static String toStringHtml(Object value) {
    return Fn.toStringHtml(value);
  }
  public static String toStringYmd(Object value) {
    return Fn.toStringYmd(value);
  }

  public static String toStringRevalue(Object value, Object revalue)
  {
    return Fn.toString(value, revalue);
  }
  public static String toStringRevalueHtml(Object value, Object revalue) {
    return Fn.toStringHtml(value, revalue);
  }
  public static String toStringRevalueYmd(Object value, Object revalue) {
    return Fn.toString(value, revalue);
  }

  public static String toStringRequest(HttpServletRequest request, String value)
  {
    return Fn.toString(request, value);
  }
  public static String toStringRequestHtml(HttpServletRequest request, String value) {
    return Fn.toStringHtml(request, value);
  }
  public static String toStringRequestYmd(HttpServletRequest request, String value) {
    return Fn.toStringYmd(request, value);
  }

  public static String toStringRequestRevalue(HttpServletRequest request, String value, String revalue)
  {
    return Fn.toString(request, value, revalue);
  }
  public static String toStringRequestRevalueHtml(HttpServletRequest request, String value, String revalue) {
    return Fn.toStringHtml(request, value, revalue);
  }
  public static String toStringRequestRevalueYmd(HttpServletRequest request, String value, String revalue) {
    return Fn.toStringYmd(request, value, revalue);
  }

  public static String toStringRequestChkvalue(HttpServletRequest request, String value, String revalue, String between_str)
  {
    return Fn.toString(request, value, revalue, between_str);
  }
  public static String toStringRequestChkvalueHtml(HttpServletRequest request, String value, String revalue, String between_str) {
    return Fn.toStringHtml(request, value, revalue, between_str);
  }
  public static String toStringRequestYmdBetween(HttpServletRequest request, String value, String revalue, String schkvalue, String echkvalue) {
    return Fn.toStringYmdBetween(request, value, revalue, schkvalue, echkvalue);
  }

  public static String toStringSession(HttpSession session, String value)
  {
    return Fn.toString(session, value);
  }
  public static String toStringSessionHtml(HttpSession session, String value) {
    return Fn.toStringHtml(session, value);
  }

  public static String toStringSessionRevalue(HttpSession session, String value, String revalue)
  {
    return Fn.toString(session, value, revalue);
  }
  public static String toStringSessionRevalueHtml(HttpSession session, String value, String revalue) {
    return Fn.toStringHtml(session, value, revalue);
  }

  public static String toStringStr(HashMap<String, String> hashmap, String key_str, String key_symbol, String value_symbol)
  {
    return Fn.toStringStr(hashmap, key_str, key_symbol, value_symbol);
  }

  public static HashMap<String, String> toHashMapStringString(Object value)
  {
    return Fn.toHashMapStringString(value);
  }
  public static HashMap<String, String> toHashMapStringStringRevalue(Object value, Object revalue) {
    return Fn.toHashMapStringString(value, revalue);
  }

  public static HashMap<String, Integer> toHashMapStringInteger(Object value)
  {
    return Fn.toHashMapStringInteger(value);
  }
  public static HashMap<String, Integer> toHashMapStringIntegerRevalue(Object value, Object revalue) {
    return Fn.toHashMapStringInteger(value, revalue);
  }

  public static HashMap<String, Double> toHashMapStringDouble(Object value)
  {
    return Fn.toHashMapStringDouble(value);
  }
  public static HashMap<String, Double> toHashMapStringDoubleRevalue(Object value, Object revalue) {
    return Fn.toHashMapStringDouble(value, revalue);
  }

  public static HashMap<String, Object> toHashMapStringObject(Object value)
  {
    return Fn.toHashMapStringObject(value);
  }
  public static HashMap<String, Object> toHashMapStringObjectRevalue(Object value, Object revalue) {
    return Fn.toHashMapStringObject(value, revalue);
  }

  public static String toParamStr(HttpServletRequest request, String addSymbol, String names)
  {
    return Fn.toParamStr(request, addSymbol, names);
  }
  public static String toParamStrHtml(HttpServletRequest request, String addSymbol, String names) {
    return Fn.toParamStrHtml(request, addSymbol, names);
  }
  public static String toParamStrStartSymbol(HttpServletRequest request, String addSymbol, String names, String start_symbol) {
    return Fn.toParamStr(request, addSymbol, names, start_symbol);
  }
  public static String toParamStrStartSymbolHtml(HttpServletRequest request, String addSymbol, String names, String start_symbol) {
    return Fn.toParamStrHtml(request, addSymbol, names, start_symbol);
  }

  public static String toParamHidden(HttpServletRequest request, String names)
  {
    return Fn.toParamHidden(request, names);
  }

  public static String toParamAllHidden(HttpServletRequest request, String exclude_param)
  {
    return Fn.toParamAllHidden(request, exclude_param);
  }

  public static String toParamValueStr(HttpServletRequest request, String names, String valueSymbol)
  {
    return toParamValueStr(request, names, valueSymbol);
  }

  public static String toParamTelNo(HttpServletRequest request, String names)
  {
    return Fn.toParamTelNo(request, names);
  }

  public static String toParamAddSymbolStr(HttpServletRequest request, String symbol, String names)
  {
    return Fn.toParamAddSymbolStr(request, symbol, names);
  }

  public static String toParamValuesAddSymbolStr(HttpServletRequest request, String name, String addSymbol)
  {
    return Fn.toParamValuesAddSymbolStr(request, name, addSymbol);
  }

  public static String[] toParameterValues(HttpServletRequest request, String name)
  {
    return Fn.toParameterValues(request, name);
  }

  public static HashMap<String, String> toParamHash(HttpServletRequest request)
  {
    return Fn.toParamHash(request);
  }

  public static String toStringCharSetEncodeParam(HttpServletRequest request, String value, String send_server_charset)
  {
    return Fn.toStringCharSetEncodeParam(request, value, send_server_charset);
  }

  public static long toLongFromDate(Date value)
  {
    return Fn.toLong(value);
  }

  public static long toLongFromTimestamp(Timestamp value)
  {
    return value.getTime();
  }

  public static Date toDateFromLong(long value)
  {
    return Fn.toDate(value);
  }

  public static Date toDateFromTimestamp(Timestamp value)
  {
    return Fn.toDate(value);
  }

  public static Date toDate(String value)
    throws Exception
  {
    return Fn.toDate(value);
  }

  public static Timestamp toTimestampFromLong(long value)
  {
    return Fn.toTimestamp(value);
  }

  public static Timestamp toTimestampFromDate(Date value)
  {
    return Fn.toTimestamp(value);
  }

  public static Timestamp toTimestamp(String value)
    throws Exception
  {
    return Fn.toTimestamp(value);
  }

  public static String toDateFormatFromDate(Date value, String type)
  {
    return Fn.toDateFormat(value, type);
  }

  public static String toDateFormatFromLong(Long value, String type)
  {
    return Fn.toDateFormat(value, type);
  }

  public static String toDateFormatFromString(String value, String type)
  {
    return Fn.toDateFormat(value, type);
  }

  public static String toKor(String value)
  {
    return Fn.toKor(value);
  }

  public static String toEng(String value)
  {
    return Fn.toEng(value);
  }

  public static String toTelNo(String tel_no)
  {
    return Fn.toTel_no_format(tel_no);
  }

  public static String[] toTelNoArray(String tel_no)
  {
    return Fn.toTel_no_array(tel_no);
  }

  public static String[] toStrArrayMaxSize(String value, String split_symbol, int max_array_size)
  {
    return Fn.toStr_array(value, split_symbol, max_array_size);
  }

  public static String[] toStrArray(String value, String split_symbol)
  {
    return Fn.toStr_array(value, split_symbol);
  }

  public static HashMap<String, String> toHash(String value_str, String symbol_1, String symbol_2)
  {
    return Fn.toHash(value_str, symbol_1, symbol_2);
  }
/*
  public static String changeString(Object input_str, Object output_str, Object value)
  {
    return Fn.changeString(input_str, output_str, value);
  }

  public static int changeInt(Object input_str, Object output_str, Object value) {
    return Fn.toInt(Fn.changeString(input_str, output_str, value));
  }
*/
  public static double round(double value, int digits)
  {
    return Fn.round(value, digits);
  }

  public static double roundDown(double value, int digits)
  {
    return Fn.roundDown(value, digits);
  }

  public static double roundUp(double value, int digits)
  {
    return Fn.roundUp(value, digits);
  }

  public static String amount(double value)
  {
    return Fn.amount(value);
  }

  public static String decimal(double value, String format)
  {
    return Fn.decimal(value, format);
  }

  public static String decimalFromString(String value, String point_format)
  {
    return Fn.decimal(value, point_format);
  }

  public static String shortSubstring(String value, int length)
  {
    return Fn.shortSubstring(value, length);
  }

  public static String shortByteString(String value, int maxbyte) throws Exception {
    return Fn.shortByteString(value, maxbyte);
  }

  public static String sortInteger(String value, String symbol, int order)
  {
    return Fn.sortInteger(value, symbol, order);
  }
  public static String sortString(String value, String symbol, int order) {
    return Fn.sortString(value, symbol, order);
  }

  public static String[] sortIntegerArray(String key_str, String value_str, String symbol, int order)
  {
    return Fn.sortIntegerArray(key_str, value_str, symbol, order);
  }
  public static String[] sortStringArray(String key_str, String value_str, String symbol, int order) {
    return Fn.sortStringArray(key_str, value_str, symbol, order);
  }

  public static String limitByteAndEnterAddBrTag(String value, int maxbyte)
    throws Exception
  {
    return Fn.limitByteAndEnterAddBrTag(value, maxbyte);
  }

  public static String limitByteAddBrTag(String value, int maxbyte)
    throws Exception
  {
    return Fn.limitByteAddBrTag(value, maxbyte);
  }

  public static String lpad(String value, int len, String symbol)
  {
    return Fn.lpad(value, len, symbol);
  }

  public static String rpad(String value, int len, String symbol)
  {
    return Fn.rpad(value, len, symbol);
  }

  public static String substringBefore(String value, String search)
  {
    return Fn.substringBefore(value, search);
  }

  public static String substringBefore2(String value, String search)
  {
    return Fn.substringBefore2(value, search);
  }

  public static String substringAfter(String value, String search)
  {
    return Fn.substringAfter(value, search);
  }

  public static String substringAfter2(String value, String search)
  {
    return Fn.substringAfter2(value, search);
  }

  public static String encode(String value, String encodeing)
  {
    return Fn.encode(value, encodeing);
  }

  public static String decode(String value, String enconding)
  {
    return Fn.decode(value, enconding);
  }

  public static String base64Encode(String str)
  {
    return Fn.base64Encode(str);
  }

  public static String base64Decode(String str)
  {
    return Fn.base64Decode(str);
  }

  public static String addSymbol(double value, String symbol)
  {
    return Fn.addSymbol(value, symbol);
  }

  public static String addSymbolFromString(String value, String symbol, int space_cnt)
  {
    return Fn.addSymbol(value, symbol, space_cnt);
  }

  public static String addZero(Object value)
  {
    return Fn.addZero(Fn.toInt(value));
  }

  public static int getByteLength(String value)
  {
    return Fn.getByteLength(value);
  }

  public static int getMaxDay(int year, int month)
  {
    return Fn.getMaxDay(year, month);
  }
  public static int getMaxDayFromYm(String ymd) {
    return Fn.getMaxDay(ymd);
  }

  public static int getTotalWeekTime(int year, int month)
  {
    return Fn.getTotalWeekTime(year, month);
  }
  public static int getTotalWeekTimeFromYm(String ymd) {
    return Fn.getTotalWeekTime(ymd);
  }

  public static int getWeekTime(String ymd)
  {
    return Fn.getWeekTime(ymd);
  }

  public static int getWeekDayFromYm(String ym, int weektime, int weekno)
  {
    return Fn.getWeekDay(ym, weektime, weekno);
  }
  public static int getWeekDay(int year, int month, int weektime, int weekno) {
    return Fn.getWeekDay(year, month, weektime, weekno);
  }

  public static String getWeekYmdFromYm(String ym, int weektime, int weekno)
    throws Exception
  {
    return Fn.getWeekYmd(ym, weektime, weekno);
  }

  public static String getWeekYmd(int year, int month, int weektime, int weekno) throws Exception {
    return Fn.getWeekYmd(year, month, weektime, weekno);
  }

  public static int getWeek(int year, int month, int day)
  {
    return Fn.getWeek(year, month, day);
  }
  public static int getWeekFromYmd(String ymd) {
    return Fn.getWeek(ymd);
  }

  public static int getWeekFirstDay(int year, int month, int weektime)
  {
    return Fn.getWeekFirstDay(year, month, weektime);
  }

  public static int getWeekFirstDayFromYm(String ym, int weektime) {
    return Fn.getWeekFirstDay(ym, weektime);
  }

  public static int getTotalWeek(int year, int month)
  {
    return Fn.getTotalWeek(year, month);
  }
  public static int getTotalWeekFromYmd(String ymd) {
    return Fn.getTotalWeek(ymd);
  }

  public static String getWeekKor(int year, int month, int day)
  {
    return Fn.getWeekKor(year, month, day);
  }
  public static String getWeekKorFromYmd(String ymd) {
    return Fn.getWeekKor(ymd);
  }

  public static int getYearNow()
  {
    return Fn.getYear();
  }

  public static int getMonthNow()
  {
    return Fn.getMonth();
  }

  public static int getDayNow()
  {
    return Fn.getDay();
  }

  public static Date getDateAddDayNow(int day)
  {
    return Fn.getDateAddDay(day);
  }

  public static String getYmdAddDayNow(int day)
  {
    return Fn.getYmdAddDay(day);
  }

  public static Date getDateAddDay(Date date, int day)
  {
    return Fn.getDateAddDay(date, day);
  }

  public static String getYmdAddDay(Date date, int day)
  {
    return Fn.getYmdAddDay(date, day);
  }

  public static String getYmdAddMonth(String value_ymd, int add_month)
  {
    return Fn.getYmdAddMonth(value_ymd, add_month);
  }

  public static String getYmAddMonth(String value_ym, int add_month)
  {
    return Fn.getYmAddMonth(value_ym, add_month);
  }

  public static String getYmdAddMonthFromDate(Date date, int add_month)
  {
    return Fn.getYmdAddMonth(date, add_month);
  }

  public static int getSubDaysFromString(String ymd1, String ymd2)
  {
    return Fn.getSubDays(ymd1, ymd2);
  }
  public static int getSubDaysFromDate(Date date1, Date date2) {
    return Fn.getSubDays(date1, date2);
  }

  public static int getSubDaysNowFromDate(Date date)
  {
    return Fn.getSubDays(date);
  }
  public static int getSubDaysNowFromYmd(String ymd) {
    return Fn.getSubDays(ymd);
  }

  public static int[] getImgSize(String src)
    throws IOException
  {
    return Fn.getImgSize(src);
  }

  public static int[] getImgReSize(int img_max_wsz, int img_max_hsz, String img_context)
    throws IOException
  {
    return Fn.getImgReSize(img_max_wsz, img_max_hsz, img_context);
  }

  public static String getFileType(String file_nm)
  {
    return Fn.getFileType(file_nm);
  }

  public static String getBrowserVer(HttpServletRequest request)
  {
    return Fn.getBrowserVer(request);
  }

  public static boolean isDaysNew(Date value, int days)
  {
    return Fn.isDaysNew(value, days);
  }

  public static boolean isEqual(String value1, String value2)
  {
    return Fn.isEqual(value1, value2);
  }

  public static boolean isIDType(String value)
  {
    return Fn.isIDType(value);
  }

  public static boolean isEmpty(String value)
  {
    return Fn.isEmpty(value);
  }

  public static boolean isFirefox(HttpServletRequest request)
  {
    return Fn.isFirefox(request);
  }

  public static boolean isOpera(HttpServletRequest request)
  {
    return Fn.isOpera(request);
  }

  public static boolean isChrome(HttpServletRequest request)
  {
    return Fn.isChrome(request);
  }

  public static boolean isIE6(HttpServletRequest request)
  {
    return Fn.isIE6(request);
  }

  public static boolean isIE7(HttpServletRequest request)
  {
    return Fn.isIE7(request);
  }

  public static boolean isIE8(HttpServletRequest request)
  {
    return Fn.isIE8(request);
  }

  public static boolean isIE9(HttpServletRequest request)
  {
    return Fn.isIE9(request);
  }

  public static boolean isIE10(HttpServletRequest request)
  {
    return Fn.isIE10(request);
  }

  public static boolean isEqualValue(String value_str, String symbol, String cfvalue)
  {
    return Fn.isEqualValue(value_str, symbol, cfvalue);
  }

  public static boolean isImageFile(String file_nm)
  {
    return Fn.isImageFile(file_nm);
  }

  public static boolean isIndexOfValue(String value_str, String symbol, String cfvalue)
  {
    return Fn.isIndexOfValue(value_str, symbol, cfvalue);
  }
/*
  public static String selected(Object orgValue, Object cfValue)
  {
    return Fn.selected(orgValue, cfValue);
  }

  public static String checked(Object orgValue, Object cfValue)
  {
    return Fn.checked(orgValue, cfValue);
  }

  public static String flashWrite(String url, int w, int h, String id, String bg, String win, String title, String imagesrc, String flash_text)
  {
    return Fn.flashWrite(url, w, h, id, bg, win, title, imagesrc, flash_text);
  }
*/
  public static String movieWrite(HttpServletRequest request, String movie_file, int width, int height, String movie_title)
  {
    return movieWrite(request, movie_file, width, height, movie_title);
  }

  public static String makeMD5(String str)
    throws Exception
  {
    return Fn.makeMD5(str);
  }

  public static String replaceAll(String str, String s1, String s2)
  {
    if ((str != null) && (str.length() > 0) && (s1 != null) && (s1.length() > 0) && (s2 != null) && (s2.length() > 0)) {
      return Fn.replaceAll(str, s1, s2);
    }
    return "";
  }

  public static String replaceOne(String str, String s1, String s2)
  {
    if ((str != null) && (str.length() > 0) && (s1 != null) && (s1.length() > 0) && (s2 != null) && (s2.length() > 0)) {
      return Fn.replaceOne(str, s1, s2);
    }
    return "";
  }

  public static String tagClean(String s)
  {
    return StrUtil.tagClean(s);
  }
  public static String content_standard(String value) {
    return StrUtil.content_standard(value);
  }
  public static String content_tag(String content) {
    return StrUtil.content_tag(content);
  }
  public static String title_tag(String title) {
    return StrUtil.title_tag(title);
  }

  public static String extractNumber(String str) {
    return StrUtil.extractNumber(str);
  }
  public static String htmlSpecialChars(String s) {
    return StrUtil.htmlSpecialChars(s);
  }

  public static String makeDateString(String date)
  {
    if (date.length() == 8) {
      return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6);
    }
    return date;
  }
}