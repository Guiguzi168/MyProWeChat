package org.MyProWeChat.base.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Iterator;
import java.util.List;


public class StringUtil {
    static final char LIST_ESC_CHAR = '\\';
    public static final String FOLDER_SEPARATOR = "/";
    public static final String WINDOWS_FOLDER_SEPARATOR = "\\";
    public static final String CURRENT_PATH = ".";
    public static final String TOP_PATH = "..";

    public static String nullable(Object obj, String defaultValue) {
        return isEmpty(obj) ? defaultValue : obj.toString();
    }

    public static String nullable(Object obj) {
        return nullable(obj, "");
    }

    public static boolean isBlank(Object obj) {
        return isEmptyOrBlank(obj, true);
    }

    public static boolean isEmpty(Object obj) {
        return isEmptyOrBlank(obj, false);
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmptyOrBlank(obj, false);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static boolean isEmptyOrBlank(Object obj, boolean trim) {
        if (obj == null) {
            return true;
        }
        if ((obj instanceof String)) {
            String ss = (String) obj;
            return (trim ? ss.trim() : ss).length() == 0;
        }
        if ((obj instanceof Object[])) {
            Object[] oo = (Object[]) obj;
            for (int i = 0; i < oo.length; i++) {
                if (!isEmptyOrBlank(oo[i], trim)) {
                    return false;
                }
            }
            return true;
        }
        if ((obj instanceof Collection)) {
            Collection<Object> oo = (Collection) obj;
            for (Iterator<Object> i = oo.iterator(); i.hasNext();) {
                if (!isEmptyOrBlank(i.next(), trim)) {
                    return false;
                }
            }
            return true;
        }
        if ((obj instanceof Map)) {
            return ((Map) obj).isEmpty();
        }
        return false;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static String[] splitIntoLines(String str) {
        if (str == null) {
            return null;
        }
        BufferedReader br = new BufferedReader(new StringReader(str));

        ArrayList<String> linesList = new ArrayList();
        try {
            String line = br.readLine();
            while (line != null) {
                linesList.add(line);
                line = br.readLine();
            }
        } catch (IOException localIOException) {
        }
        return (String[]) linesList.toArray(new String[linesList.size()]);
    }

    public static List<String> split(String s, char delimiter, boolean trim) {
        return split(s, String.valueOf(delimiter), trim);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List<String> split(String s, String delimiter, boolean trim) {
        List<String> ret = new ArrayList();
        if (s == null) {
            return ret;
        }
        int lastIdx = 0;
        int idx = s.indexOf(delimiter);
        while (idx >= 0) {
            String s1 = s.substring(lastIdx, idx);
            if (trim) {
                s1 = s1.trim();
            }
            ret.add(s1);

            lastIdx = idx + delimiter.length();
            idx = s.indexOf(delimiter, lastIdx);
        }
        String s1 = s.substring(lastIdx);
        if (trim) {
            s1 = s1.trim();
        }
        ret.add(s1);

        return ret;
    }

    public static List<String> split(String s, char delimiter) {
        return split(s, delimiter, true);
    }

    public static List<String> split(String input) {
        return split(input, "[\\s,]+");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List<String> split(String input, String sep) {
        if (input == null) {
            return null;
        }
        int index = 0;
        List<String> matchList = new ArrayList();
        Matcher m = Pattern.compile(sep).matcher(input);
        while (m.find()) {
            if (index < m.start()) {
                String match = input.subSequence(index, m.start()).toString();
                matchList.add(match);
            }
            index = m.end();
        }
        if (index < input.length()) {
            matchList.add(input.subSequence(index, input.length()).toString());
        }
        return matchList;
    }

    public static final String replace(String input, String matchString, String newString) {
        int i = 0;
        if ((i = input.indexOf(matchString, i)) >= 0) {
            char[] line2 = input.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = matchString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = input.indexOf(matchString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return input;
    }

    public static final String escapeHTMLTags(String input) {
        if ((input == null) || (input.length() == 0)) {
            return input;
        }
        StringBuffer buf = new StringBuffer(input.length());
        char ch = ' ';
        for (int i = 0; i < input.length(); i++) {
            ch = input.charAt(i);
            if (ch == '<') {
                buf.append("&lt;");
            } else if (ch == '>') {
                buf.append("&gt;");
            } else if (ch == '"') {
                buf.append("&quot;");
            } else if (ch == '\'') {
                buf.append("&apos;");
            } else if (ch == '&') {
                buf.append("&amp;");
            } else if (ch == '\\') {
                buf.append('\\');
                buf.append(ch);
            } else if (ch == '\r') {
                buf.append("\\r");
            } else if (ch == '\n') {
                buf.append("\\n");
            } else {
                buf.append(ch);
            }
        }
        return buf.toString();
    }

    public static final String viewHTMLTags(String input) {
        if ((input == null) || (input.length() == 0)) {
            return input;
        }
        StringBuffer buf = new StringBuffer(input.length());
        char ch = ' ';
        for (int i = 0; i < input.length(); i++) {
            ch = input.charAt(i);
            if (ch == '<') {
                buf.append("&lt;");
            } else if (ch == '>') {
                buf.append("&gt;");
            } else if (ch == '"') {
                buf.append("&quot;");
            } else if (ch == '\'') {
                buf.append("&apos;");
            } else if (ch == '&') {
                buf.append("&amp;");
            } else if (ch == '\\') {
                buf.append(ch);
            } else if (ch == '\r') {
                buf.append("<br>");
            } else if (ch == '\n') {
                buf.append("");
            } else {
                buf.append(ch);
            }
        }
        return buf.toString();
    }

    private static MessageDigest digest = null;
    public static final synchronized String hash(String data) {
        if (digest == null) {
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException nsae) {
                throw new RuntimeException("Failed to load the MD5 MessageDigest. SCU will be unable to function normally.");
            }
        }
        digest.update(data.getBytes());
        return toHex(digest.digest());
    }

    public static final String toHex(byte[] hash) {
        StringBuffer buf = new StringBuffer(hash.length * 2);
        String stmp = "";
        for (int i = 0; i < hash.length; i++) {
            stmp = Integer.toHexString(hash[i] & 0xFF);
            if (stmp.length() == 1) {
                buf.append(0).append(stmp);
            } else {
                buf.append(stmp);
            }
        }
        return buf.toString();
    }

    public static final byte[] hexToBytes(String hex) {
        if (null == hex) {
            return new byte[0];
        }
        int len = hex.length();
        byte[] bytes = new byte[len / 2];
        String stmp = null;
        try {
            for (int i = 0; i < bytes.length; i++) {
                stmp = hex.substring(i * 2, i * 2 + 2);
                bytes[i] = ((byte) Integer.parseInt(stmp, 16));
            }
        } catch (Exception e) {
            return new byte[0];
        }
        return bytes;
    }

    public static final String escapeForXML(String input) {
        if ((input == null) || (input.length() == 0)) {
            return input;
        }
        char[] sArray = input.toCharArray();
        StringBuffer buf = new StringBuffer(sArray.length);
        for (int i = 0; i < sArray.length; i++) {
            char ch = sArray[i];
            if (ch == '<') {
                buf.append("&lt;");
            } else if (ch == '>') {
                buf.append("&gt;");
            } else if (ch == '"') {
                buf.append("&quot;");
            } else if (ch == '&') {
                buf.append("&amp;");
            } else {
                buf.append(ch);
            }
        }
        return buf.toString();
    }

    public static final String unescapeFromXML(String input) {
        input = replace(input, "&lt;", "<");
        input = replace(input, "&gt;", ">");
        input = replace(input, "&quot;", "\"");
        return replace(input, "&amp;", "&");
    }

    public static final String compactSizeFormat(String number) {
        String[] end = { "B", "kB", "MB", "GB" };
        double num = 0.0D;
        int i = 0;
        try {
            num = Integer.parseInt(number);
        } catch (Exception e) {
            num = 0.0D;
        }
        while ((num > 1024.0D) && (i < end.length)) {
            num /= 1024.0D;
            i++;
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        return nf.format(num) + " " + end[i];
    }

    public static String substr(String src, int beginIndex, int endIndex) {
        String dest = "";
        if (src == null) {
            return dest;
        }
        byte[] srcByte = src.getBytes();
        byte[] destByte = null;
        int srclen = srcByte.length;
        if ((srclen <= beginIndex) || (beginIndex >= endIndex)) {
            return "";
        }
        if (srclen >= endIndex) {
            destByte = new byte[endIndex - beginIndex];
            System.arraycopy(srcByte, beginIndex, destByte, 0, endIndex - beginIndex);
            dest = new String(destByte);
            return dest;
        }
        destByte = new byte[srclen - beginIndex];
        System.arraycopy(srcByte, beginIndex, destByte, 0, srclen - beginIndex);
        dest = new String(destByte);
        return dest;
    }

    public static String gbsubstr(String src, int beginIndex, int endIndex, boolean ifAdd) {
        String dest = "";
        dest = substr(src, beginIndex, endIndex);
        if (dest.length() == 0) {
            if (ifAdd) {
                dest = substr(src, beginIndex, endIndex + 1);
            } else {
                dest = substr(src, beginIndex, endIndex - 1);
            }
        }
        return dest;
    }

    public static String gbsubstr(String src, int beginIndex, int endIndex) {
        return gbsubstr(src, beginIndex, endIndex, false);
    }

    public static int gbStrLen(String str) {
        if (str == null) {
            return 0;
        }
        byte[] strByte;
        try {
            strByte = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            strByte = str.getBytes();
        }
        return strByte.length;
    }

    public static String replicateStr(char ch, int len) {
        String tmpstr = null;
        char[] tmparr = null;
        if (len <= 0) {
            return "";
        }
        tmparr = new char[len];
        for (int i = 0; i < len; i++) {
            tmparr[i] = ch;
        }
        tmpstr = new String(tmparr);

        return tmpstr;
    }

    public static String lFillStr(String src, char ch, int len) {
        String dest = src;

        int srclen = gbStrLen(src);
        if (srclen > len) {
            dest = gbsubstr(src, 0, len);
            srclen = gbStrLen(dest);
        }
        dest = dest + replicateStr(ch, len - srclen);

        return dest;
    }

    public static String rFillStr(String src, char ch, int len) {
        return rFillStr(src, ch, len, false);
    }

    public static String rFillStr(String src, char ch, int len, boolean gb) {
        String dest = src;
        int srclen = gb ? gbStrLen(src) : src.length();
        if (srclen > len) {
            dest = gbsubstr(src, 0, len);
            srclen = gb ? gbStrLen(dest) : dest.length();
        }
        dest = replicateStr(ch, len - srclen) + dest;

        return dest;
    }

    public static String maxstr(String s, int maxlength) {
        return maxstr(s, "UTF-8", maxlength);
    }

    public static String maxstr(String s, String encoding, int maxlength) {
        if (s == null) {
            return "";
        }
        try {
            byte[] bytes = encoding == null ? s.getBytes() : s.getBytes(encoding);
            if (bytes.length <= maxlength) {
                return s;
            }
            int sublength = maxlength - 3;

            String ret = new String(bytes, 0, sublength, encoding);
            if (ret.getBytes(encoding).length > sublength) {
                ret = _maxstr(s, encoding, sublength);
            }
            return ret + "...";
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    private static String _maxstr(String s, String encoding, int maxlength) {
        try {
            char[] srcStrChars = s.toCharArray();
            int cnt = 0;
            Charset cs = Charset.forName(encoding);
            CharBuffer cb = CharBuffer.allocate(1);
            ByteBuffer resultBuff = ByteBuffer.allocate(maxlength);
            for (int i = 0; i < srcStrChars.length; i++) {
                char c = srcStrChars[i];
                cb.put(c);
                cb.flip();
                ByteBuffer bb = cs.encode(cb);
                cnt += bb.array().length;
                if (cnt > maxlength) {
                    break;
                }
                resultBuff.put(bb);
                cb.clear();
            }
            return new String(resultBuff.array(), 0, maxlength, encoding);
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    public static boolean equals(String val1, String val2) {
        if (val1 == null) {
            if (val2 == null) {
                return true;
            }
            return false;
        }
        return val1.equals(val2);
    }

    public static String getDefaultCharacterEncoding() {
        String charEnc = System.getProperty("file.encoding");
        if (charEnc != null) {
            return charEnc;
        }
        charEnc = new OutputStreamWriter(new ByteArrayOutputStream()).getEncoding();

        return charEnc != null ? charEnc : "<unknown charset encoding>";
    }

    public static String capitalize(String name) {
        if ((name == null) || (name.length() == 0)) {
            return name;
        }
        char[] chars = name.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }

    public static String unCapitalize(String name) {
        if ((name == null) || (name.length() == 0)) {
            return name;
        }
        char[] chars = name.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }

//    public static String replaceProperties(String value, Map staticProp, BeanUtil.PropertySource[] dynamicProp) {
//        StringBuffer sb = new StringBuffer();
//        int prev = 0;
//        int pos;
//        while ((pos = value.indexOf("$", prev)) >= 0) {
//            if (pos > 0) {
//                sb.append(value.substring(prev, pos));
//            }
//            if (pos == value.length() - 1) {
//                sb.append('$');
//                prev = pos + 1;
//                break;
//            }
//            if (value.charAt(pos + 1) != '{') {
//                sb.append('$');
//
//                prev = pos + 1;
//            } else {
//                int endName = value.indexOf('}', pos);
//                if (endName < 0) {
//                    sb.append(value.substring(pos));
//                    prev = value.length();
//                } else {
//                    String n = value.substring(pos + 2, endName);
//                    String v = null;
//                    if ((n != null) && (staticProp != null) && (staticProp.get(n) != null)) {
//                        v = staticProp.get(n).toString();
//                    }
//                    if ((n != null) && (v == null) && (dynamicProp != null)) {
//                        for (int i = 0; i < dynamicProp.length; i++) {
//                            v = dynamicProp[i].getProperty(n);
//                            if (v != null) {
//                                break;
//                            }
//                        }
//                    }
//                    if (v == null) {
//                        v = "${" + n + "}";
//                    }
//                    sb.append(v);
//                    prev = endName + 1;
//                }
//            }
//        }
//        if (prev < value.length()) {
//            sb.append(value.substring(prev));
//        }
//        return sb.toString();
//    }

    public static int getStrLenByEncoding(String str, String dbEncoding) {
        if (str == null) {
            return 0;
        }
        byte[] strByte;
        try {
            strByte = str.getBytes(dbEncoding);
        } catch (UnsupportedEncodingException e) {
            strByte = str.getBytes();
        }
        return strByte.length;
    }

    public static int getStrLen(String str) {
        if (str == null) {
            return 0;
        }
        return str.length();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List<String> getProperties(String str) {
        List<String> propertiesList = new ArrayList();
        if (str == null) {
            return propertiesList;
        }
        int prev = 0;
        int pos;
        while (((pos = str.indexOf("$", prev)) >= 0) && (pos != str.length() - 1)) {
            if (str.charAt(pos + 1) != '{') {
                prev = pos + 1;
            } else {
                if (pos == str.length() - 2) {
                    break;
                }
                int endName = str.indexOf('}', pos);
                if (endName >= 0) {
                    String n = str.substring(pos + 2, endName);
                    if (!propertiesList.contains(n)) {
                        propertiesList.add(n);
                    }
                    prev = endName + 1;
                }
            }
        }
        return propertiesList;
    }

    public static String format(String messagePattern, Object... arguments) {
        StringBuilder seusable = new StringBuilder();
        format(seusable, messagePattern, arguments);
        return seusable.toString();
    }
    
    public static void format(StringBuilder destSB, String messagePattern, Object... arguments) {
        if ((arguments == null) || (arguments.length == 0)) {
            destSB.append(messagePattern);
            return;
        }
        int argsIndex = 0;
        for (int i = 0; i < messagePattern.length(); i++) {
            char c = messagePattern.charAt(i);
            if (('%' == c) && (messagePattern.charAt(i + 1) == 's')) {
                destSB.append(String.valueOf(arguments[argsIndex]));
                argsIndex++;
                i++;
            } else {
                if (('%' == c) && (messagePattern.charAt(i + 1) != 's')) {
                    destSB.setLength(0);
                    destSB.append(String.format(messagePattern, arguments));
                    return;
                }
                destSB.append(c);
            }
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static final ThreadLocal<List<Object>> cache = new ThreadLocal();
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static final ThreadLocal<Integer> cacheCount = new ThreadLocal();
    public static <K, V> String mapToString(Map<K, V> map) {
        return mapToString(map, null);
    }

    private static boolean contains(List<Object> cache, Object value) {
        for (Object o : cache) {
            if (o == value) {
                return true;
            }
        }
        return false;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <K, V> String mapToString(Map<K, V> map, Map<K, V> parent) {
        List<Object> list = (List) cache.get();
        if (list == null) {
            cache.set(list = new ArrayList());
        }
        if (list.size() == 0) {
            cacheCount.set(Integer.valueOf(0));
        }
        if (contains(list, map)) {
            return "[^" + map.hashCode() + "]";
        }
        if ((cacheCount.get() != null) && (((Integer) cacheCount.get()).intValue() > 50)) {
            return "<TOO MANY OBJECTS:" + cacheCount.get() + ">";
        }
        list.add(map);
        cacheCount.set(Integer.valueOf(cacheCount.get() == null ? 1 : ((Integer) cacheCount.get()).intValue() + 1));
        try {
            return toString(map, parent);
        } finally {
            list.remove(map);

            cacheCount.set(Integer.valueOf(((Integer) cacheCount.get()).intValue() - 1));
        }
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static <K, V> String toString(Map<K, V> map, Map<K, V> parent) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        Iterator<Map.Entry<K, V>> i = parent == null ? map.entrySet().iterator() : parent.entrySet().iterator();
        boolean hasNext = i.hasNext();
        while (hasNext) {
            Map.Entry<K, V> e = (Map.Entry) i.next();
            Object key = e.getKey();
            toString(sb, e.getKey());
            sb.append("=");
            Object value = null;
            if (parent == null) {
                value = e.getValue();
            } else {
                value = map.keySet().contains(key) ? map.get(key) : e.getValue();
            }
            if ((value instanceof BigDecimal)) {
                value = ((BigDecimal) value).toPlainString();
            }
            toString(sb, value);
            hasNext = i.hasNext();
            if (hasNext) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
    
    private static void toString(StringBuilder sb, Object value) {
        Object[] v = ArrayUtil.asArray(value, false);
        if ((v != null) && (v.length == 1)) {
            sb.append(value);
        } else if ((v != null) && (v.length > 20)) {
            sb.append(Arrays.asList(Arrays.copyOfRange(v, 0, 20)));
            sb.append("[...]");
        } else {
            sb.append(value);
        }
    }
}
