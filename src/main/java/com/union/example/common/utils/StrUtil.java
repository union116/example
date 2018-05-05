package com.union.example.common.utils;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.text.StringCharacterIterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtil
{
  public static final String ESC_STRING = "`'{}";
  public static final String ESC_CODE_BQ = "60";
  public static final String ESC_CODE_SQ = "27";
  public static final String ESC_CODE_BKO = "7B";
  public static final String ESC_CODE_BKC = "7D";
  public static final String SYS_ENC = "8859_1";
  public static final String USER_ENC = "ksc5601";

  public static String itoa(int n)
  {
    return Integer.toString(n);
  }
  public static int atoi(String s) {
    return Integer.valueOf(s).intValue();
  }
  public static String itoa(int n, int radix) {
    return Integer.toString(n, radix);
  }
  public static int atoi(String s, int radix) {
    return Integer.valueOf(s, radix).intValue();
  }
  public static String ltoa(long n) {
    return Long.toString(n);
  }
  public static long atol(String s) {
    return Long.valueOf(s).longValue();
  }
  public static String ltoa(long n, int radix) {
    return Long.toString(n, radix);
  }
  public static long atol(String s, int radix) {
    return Long.valueOf(s, radix).longValue();
  }

  public static String lpad(String s, char c, int length)
  {
    int count = length - s.length();
    do { s = c + s; count--; } while (count >= 0);
    return s;
  }

  public static String rpad(String s, char c, int length)
  {
    int count = length - s.length();
    do { s = s + c; count--; } while (count >= 0);
    return s;
  }

  public static String stringOf(char c, int length)
  {
    String s = "";
    do { s = s + c; length--; } while (length >= 0);
    return s;
  }

  public static String asc2ksc(String str)
  {
    String result = null;
    try {
      if (str == null) return null;
      result = new String(str.getBytes("8859_1"), "ksc5601");
    }
    catch (UnsupportedEncodingException e) {
      result = new String(str);
    }
    return result;
  }

  public static String ksc2asc(String str)
  {
    String result = null;
    try {
      if (str == null) return null;
      result = new String(str.getBytes("ksc5601"), "8859_1");
    }
    catch (UnsupportedEncodingException e) {
      result = new String(str);
    }
    return result;
  }
  public static String quote(String s) {
    return "'" + addSlashes(s) + "'";
  }
  public static String singleQuote(String s) {
    return "'" + s + "'";
  }
  public static String doubleQuote(String s) {
    return "\"" + s + "\"";
  }

  public static String nl2br(String s)
  {
    if ((s == null) || (s.equals(""))) {
      return "";
    }
    StringBuffer buffer = new StringBuffer();
    StringTokenizer st = new StringTokenizer(s, "\n");
    while (st.hasMoreTokens()) buffer.append(st.nextToken()).append("<br>");
    return buffer.toString();
  }

  public static String stripTags(String s)
  {
    StringBuffer buffer = new StringBuffer();
    StringTokenizer st = new StringTokenizer(s, "<>", true);

    boolean inTag = false;
    while (st.hasMoreTokens()) {
      String token = st.nextToken();
      switch (token.charAt(0)) { case '<':
        inTag = true; break;
      case '>':
        inTag = false; break;
      case '=':
      default:
        if (inTag) continue; buffer.append(token);
      }

    }

    return buffer.toString();
  }

  public static String htmlSpecialChars(String s)
  {
    if ((s == null) || (s.length() == 0)) {
      return "";
    }
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

  public static String addSlashes(String s)
  {
    if ((s == null) || (s.length() == 0)) {
      return "";
    }
    StringBuffer buffer = new StringBuffer();
    StringTokenizer st = new StringTokenizer(s, "'\"\\", true);

    while (st.hasMoreTokens()) {
      String token = st.nextToken();
      switch (token.charAt(0)) { case '\'':
        buffer.append("\\'"); break;
      case '"':
        buffer.append("\\\""); break;
      case '\\':
        buffer.append("\\\\"); break;
      default:
        buffer.append(token);
      }
    }
    return buffer.toString();
  }

  public static String stripSlashes(String s)
  {
    if ((s == null) || (s.length() == 0)) {
      return "";
    }
    StringBuffer buffer = new StringBuffer();
    StringTokenizer st = new StringTokenizer(s, "'\"\\", true);

    boolean escapeFlag = false;
    while (st.hasMoreTokens()) {
      String token = st.nextToken();
      switch (token.charAt(0)) { case '\'':
        buffer.append("'");
        escapeFlag = false;
        break;
      case '"':
        buffer.append("\"");
        escapeFlag = false;
        break;
      case '\\':
        if (escapeFlag) {
          buffer.append("\\");
          escapeFlag = false;
        }
        else {
          escapeFlag = true;
        }
        break;
      default:
        buffer.append(token);
      }
    }
    return buffer.toString();
  }

  public static String replace(String s, String s1, String s2)
  {
    if ((s == null) || (s.length() == 0) || (s1 == null) || (s1.length() == 0)) {
      return "";
    }
    StringBuffer result = new StringBuffer();
    int index1 = 0;
    int index2 = s.indexOf(s1);
    while (index2 >= 0) {
      result.append(s.substring(index1, index2));
      result.append(s2);
      index1 = index2 + s1.length();
      index2 = s.indexOf(s1, index1);
    }
    result.append(s.substring(index1));
    return result.toString();
  }

  public static String replaceAll(String str, String s1, String s2)
  {
    if ((str == null) || (str.length() == 0) || (s1 == null) || (s1.length() == 0)) {
      return "";
    }
    String s = str.toLowerCase();
    s1 = s1.toLowerCase();
    s2 = s2.toLowerCase();

    StringBuffer result = new StringBuffer();

    int index1 = 0;
    int index2 = s.indexOf(s1);

    while (index2 >= 0)
    {
      result.append(str.substring(index1, index2));
      result.append(s2);
      index1 = index2 + s1.length();
      index2 = s.indexOf(s1, index1);
    }
    result.append(str.substring(index1));
    return result.toString();
  }

  public static String[] toArray(String str, String delim)
  {
    StringTokenizer st = new StringTokenizer(str, delim);
    String[] result = new String[st.countTokens()];
    int i = 0;
    while (st.hasMoreTokens()) {
      result[(i++)] = st.nextToken();
    }
    return result;
  }

  public static String fillChar(String s, char c, int len, int flag)
  {
    int slen = s.length();
    int tlen = len > slen ? len - slen : slen - len;
    String t = "";

    for (int i = 0; i < tlen; i++) t = t + c;

    if (flag == 0) return t + s;
    return s + t;
  }

  public static String escape(String s)
  {
    if ((s == null) || (s.equals(""))) return "";

    StringBuffer buffer = new StringBuffer();
    StringTokenizer st = new StringTokenizer(s, "`'{}", true);

    while (st.hasMoreTokens())
    {
      String token = st.nextToken();

      switch (token.charAt(0)) { case '`':
        buffer.append("`60"); break;
      case '\'':
        buffer.append("`27"); break;
      case '{':
        buffer.append("`7B"); break;
      case '}':
        buffer.append("`7D"); break;
      default:
        buffer.append(token);
      }
    }

    return buffer.toString();
  }

  public static String unescape(String s)
  {
    if ((s == null) || (s.equals("")) || (s.equals("NULL"))) return "";

    StringBuffer buffer = new StringBuffer();
    StringTokenizer st = new StringTokenizer(s, "`");

    boolean first = true;
    while (st.hasMoreTokens())
    {
      if (first) {
        String token = st.nextToken();
        first = false;
        if (!st.hasMoreTokens())
        {
          return token;
        }
        buffer.append(token);
      }

      String token = st.nextToken();
      int len = token.length();

      if (len >= 2)
      {
        String code = token.substring(0, 2);

        if (code.equals("60")) {
          buffer.append("`");
          if (len <= 2) continue; buffer.append(token.substring(2));
        }
        else if (code.equals("27")) {
          buffer.append("'");
          if (len <= 2) continue; buffer.append(token.substring(2));
        }
        else if (code.equals("7B")) {
          buffer.append("{");
          if (len <= 2) continue; buffer.append(token.substring(2));
        }
        else if (code.equals("7D")) {
          buffer.append("}");
          if (len <= 2) continue; buffer.append(token.substring(2));
        } else {
          buffer.append(token);
        }
      } else {
        buffer.append(token);
      }
    }
    return buffer.toString();
  }

  public static String nvl(String s)
  {
    if (s == null) return "";
    return s.trim();
  }

  public static String nvl(String s, String ifNull)
  {
    if (s == null) return ifNull;
    return s.trim();
  }

  public static String nvl2(String s, String ifEmpty)
  {
    if ((s == null) || (s.trim().equals(""))) return ifEmpty;
    return s.trim();
  }

  public static String cutString(String val, int len, String tail)
  {
    if ((val == null) || (val.equals(""))) return "";

    if (val.length() <= len) return val;

    String retStr = val;

    StringCharacterIterator sci = new StringCharacterIterator(retStr);
    StringBuffer buffer = new StringBuffer();
    buffer.append(sci.first());
    for (int i = 1; i < len; i++) {
      if (i < len - 1) {
        char tempChar = sci.next();
        buffer.append(tempChar);
      } else {
        char c = sci.next();
        if ((c != ' ') && (c != '\034')) {
          buffer.append(c);
        }
      }
    }
    buffer.append(tail);
    return buffer.toString();
  }

  public static String cutString(String val)
  {
    return cutString(val, 10, "...");
  }

  public static String format(String fmtStr, String arg0, String arg1)
  {
    Object[] objs = new Object[2];
    objs[0] = arg0;
    objs[1] = arg1;
    return MessageFormat.format(fmtStr, objs);
  }

  public static String format(String fmtStr, String arg0, String arg1, String arg2)
  {
    Object[] objs = new Object[3];
    objs[0] = arg0;
    objs[1] = arg1;
    objs[2] = arg2;
    return MessageFormat.format(fmtStr, objs);
  }

  public static String decode(String value, String cmp, String mov)
  {
    return decode(value, cmp, mov, value);
  }

  public static String decode(String value, String cmp, String mov, String nt)
  {
    value = nvl(value, "");

    if (value.trim().equals(cmp))
      return mov;
    return nt;
  }

  public static String tagClean(String s)
  {
    if (s == null) {
      return "";
    }

    StringBuffer textbuff = new StringBuffer();
    try
    {
      String[] html_array = s.toString().split(">");
      for (String str : html_array) {
        if (str.indexOf("<") > -1) {
          str = str + ">";
        }
        Matcher m = Patterns.IMAGEALT.matcher(str);
        if (m.find()) {
          str = str + m.group(1);
        }

        m = Patterns.IMAGEALT.matcher(str);
        str = m.replaceAll("");
        m = Patterns.SCRIPTS.matcher(str);
        str = m.replaceAll("");
        m = Patterns.STYLE.matcher(str);
        str = m.replaceAll("");
        m = Patterns.TAGS.matcher(str);
        str = m.replaceAll("");
        m = Patterns.ENTITY_REFS.matcher(str);
        str = m.replaceAll("");
        m = Patterns.WHITESPACE.matcher(str);
        str = m.replaceAll(" ");

        textbuff.append(str);
      }
    } catch (Exception localException) {
    }
    return textbuff.toString();
  }

  public static boolean allow_tag(String value)
  {
    if ((value == null) || (value.length() == 0)) {
      return true;
    }
    String[] not_allow_tag = { "<jsp", "${", "<html", "</html", "<head", "</head", "<body", "</body", "<function", "<form", "action=", "<script", "</script", "<textarea", "<input", "<select", "<table", "</table", "<tr", "</tr", "<td", "</td", "<%", "%>", "iframe", "onmousedown", "onkeydown", "onmouseup", "onkeyup", "onclick", "onover", "onabort", "onchange", "ondblclick", "ondragdrop", "onkeyup", "onload", "onmousemove", "onmouseout", "onmouseup", "onreset", "onresize", "onselect", "onsubmit", "onunload", "onkeypress", "onmouseover", "onfocus", "onmouseout", "onblur" };
    String temp = value;
    temp = temp.toLowerCase();
    boolean isAllow = true;
    for (int i = 0; i < not_allow_tag.length; i++) {
      if (temp.indexOf(not_allow_tag[i]) > -1) {
        isAllow = false;
        break;
      }
    }
    return isAllow;
  }

  public static boolean allow_tag_simple(String value) {
    if ((value == null) || (value.length() == 0)) {
      return true;
    }
    String[] not_allow_tag = { "<jsp", "${", "<html", "</html", "<head", "</head", "<body", "</body", "<function", "<form", "action=", "<script", "</script", "<textarea", "<input", "<select", "<%", "%>", "iframe", "onmousedown", "onkeydown", "onmouseup", "onkeyup", "onover", "onabort", "onchange", "ondblclick", "ondragdrop", "onkeyup", "onload", "onmousemove", "onmouseout", "onmouseup", "onreset", "onresize", "onselect", "onsubmit", "onunload", "onmouseover", "onfocus", "onmouseout", "onblur" };
    String temp = value;
    temp = temp.toLowerCase();
    boolean isAllow = true;
    for (int i = 0; i < not_allow_tag.length; i++) {
      if (temp.indexOf(not_allow_tag[i]) > -1) {
        isAllow = false;
        break;
      }
    }
    return isAllow;
  }

  public static boolean is_tag(String value) {
    if ((value == null) || (value.length() == 0)) {
      return false;
    }
    String[] tag = { "<jsp", "${", "<html", "</html", "<head", "</head", "<body", "</body", "<function", "<form", "action=", "<script", "</script", "<textarea", "<input", "<select", "<%", "%>", "iframe", "onmousedown", "onkeydown", "onmouseup", "onkeyup", "onover", "onabort", "onchange", "ondblclick", "ondragdrop", "onkeyup", "onload", "onmousemove", "onmouseout", "onmouseup", "onreset", "onresize", "onselect", "onsubmit", "onunload", "onmouseover", "onfocus", "onmouseout", "onblur", "<table", "</table>", "<tr", "</tr>", "<sapn", "</span", "<div", "</div", "<p", "</p", "<br", "</br", "<head", "</head", "<meta", "</meta", "style=" };
    String temp = value;
    temp = temp.toLowerCase();
    boolean isTag = false;
    for (int i = 0; i < tag.length; i++) {
      if (temp.indexOf(tag[i]) > -1) {
        isTag = true;
        break;
      }
    }
    return isTag;
  }

  public static String content_standard(String value) {
    if (value == null)
      return "";
    if (!allow_tag_simple(value))
      return tagClean(value);
    if (is_tag(value)) {
      return value;
    }
    return value.replaceAll("\n", "<br />");
  }

  public static String content_tag(String content)
  {
    if (content == null)
      return "";
    if (!allow_tag_simple(content))
      try {
        return htmlSpecialChars(content);
      } catch (Exception e) {
        return htmlSpecialChars(tagClean(content));
      }
    if (is_tag(content)) {
      return content;
    }
    return content.replaceAll("\n", "<br />");
  }

  public static String title_tag(String title) {
    if ((title == null) || (title.length() == 0))
      return "";
    if (allow_tag(title)) {
      return title;
    }
    return htmlSpecialChars(tagClean(title));
  }

  public static String toCellNoStr(String str) {
    String cell_no = "";
    if (str != null) {
      cell_no = extractNumber(str);

      if (cell_no.length() == 10)
        cell_no = cell_no.substring(0, 3) + "-" + cell_no.substring(3, 6) + "-" + cell_no.substring(6);
      else if (cell_no.length() >= 7) {
        cell_no = cell_no.substring(0, 3) + "-" + cell_no.substring(3, 7) + "-" + cell_no.substring(7);
      }
    }
    return cell_no;
  }
  public static String toTelNoStr(String str) {
    String tell_no = "";
    if (str != null) {
      tell_no = extractNumber(str);
      if (tell_no.length() >= 6) {
        if (tell_no.substring(0, 2).equals("02")) {
          if (tell_no.length() == 9)
            tell_no = tell_no.substring(0, 2) + "-" + tell_no.substring(2, 5) + "-" + tell_no.substring(5);
          else if (tell_no.length() >= 6) {
            tell_no = tell_no.substring(0, 2) + "-" + tell_no.substring(2, 6) + "-" + tell_no.substring(6);
          }

        }
        else if (tell_no.length() == 10)
          tell_no = tell_no.substring(0, 3) + "-" + tell_no.substring(3, 6) + "-" + tell_no.substring(6);
        else if (tell_no.length() >= 7) {
          tell_no = tell_no.substring(0, 3) + "-" + tell_no.substring(3, 7) + "-" + tell_no.substring(7);
        }
      }
    }

    return tell_no;
  }

  public static String extractNumber(String str)
  {
    StringBuffer out = new StringBuffer();
    if ((str != null) && (str.length() > 0)) {
      String patternStr = "\\d";
      Pattern pattern = Pattern.compile(patternStr);
      Matcher matcher = pattern.matcher(str);
      while (matcher.find()) {
        out.append(matcher.group(0));
      }
    }
    return out.toString();
  }

  public static String tagCleanExc(String s) {
    if (s == null) {
      return "";
    }

    StringBuffer textbuff = new StringBuffer();
    try
    {
      String[] html_array = s.toString().split(">");
      for (String str : html_array) {
        if (str.indexOf("<") > -1) {
          str = str + ">";
        }
        Matcher m = Patterns.IMAGEALT.matcher(str);
        if (m.find()) {
          str = str + m.group(1);
        }

        m = Patterns.IMAGEALT.matcher(str);
        str = m.replaceAll("");
        m = Patterns.SCRIPTS.matcher(str);
        str = m.replaceAll("");
        m = Patterns.STYLE.matcher(str);
        str = m.replaceAll("");
        m = Patterns.ENTITY_REFS.matcher(str);
        str = m.replaceAll("");
        m = Patterns.WHITESPACE.matcher(str);
        str = m.replaceAll(" ");

        textbuff.append(str);
      }
    } catch (Exception localException) {
    }
    return textbuff.toString();
  }

  private static abstract interface Patterns
  {
    public static final Pattern SCRIPTS = Pattern.compile("<(no)?script[^>]*>.*?</(no)?script>", 32);
    public static final Pattern STYLE = Pattern.compile("<style[^>]*>.*</style>", 32);

    public static final Pattern TAGS = Pattern.compile("<(\"[^\"]*\"|'[^']*'|[^'\">])*>");

    public static final Pattern nTAGS = Pattern.compile("<\\w+\\s+[^<]*\\s*>");

    public static final Pattern ENTITY_REFS = Pattern.compile("&[^;]+;");

    public static final Pattern WHITESPACE = Pattern.compile("\\s\\s+");

    public static final Pattern IMAGEALT = Pattern.compile("<img[^>]*alt=[\"']?([^>\"']+)[\"']?[^>]*>");
  }
}