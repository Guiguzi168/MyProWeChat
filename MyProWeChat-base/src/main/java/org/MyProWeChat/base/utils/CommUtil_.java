package org.MyProWeChat.base.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CommUtil_ {

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(Object bean) {
        if (bean == null) {
            return null;
        }
        if ((bean instanceof Map)) {
            return (Map<String, Object>) bean;
        }
        throw new RuntimeException(String.format("", bean.getClass().getName()));
    }

    public static String createGUID() {
        RandomGUID rg = new RandomGUID();
        return rg.getValueAfterMD5();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List<Map<String, Object>> toListMap(List<?> list) {
        if (list == null) {
            return null;
        }
        List<Map<String, Object>> ret = new ArrayList();
        Object o;
        for (Iterator localIterator = list.iterator(); localIterator.hasNext(); ret.add(toMap(o))) {
            o = localIterator.next();
        }
        return ret;
    }

    public static <T> T nvl(T s, T defaultValue) {
        return isNull(s) ? defaultValue : s;
    }

    public static boolean isNull(Object obj) {
        if (StringUtil.isEmpty(obj)) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(Object o) {
        return !isNull(o);
    }

    /**
     * 去掉字符串前面和后面的空格
     * 
     * @param s
     * @return
     */
    public static String trim(String s) {
        return s == null ? null : s.trim();
    }

    /**
     * 去掉字符串中的空格
     * 
     * @param s
     * @return
     */
    public static String rtrim(String s) {
        if (s == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer(s);
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (sb.charAt(i) != ' ') {
                break;
            }
            sb.deleteCharAt(i);
        }
        return sb.toString();
    }

    /**
     * 截取空格后字符
     * 
     * @param s
     * @return
     */
    public static String ltrim(String s) {
        if (s == null) {
            return null;
        }
        String ret = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                ret = s.substring(i);
                break;
            }
        }
        return ret;
    }

    public static boolean like(String s1, String s2) {
        if ((s1 == null) || (s2 == null)) {
            throw new IllegalArgumentException("like函数参数值无效!");
        }
        int len = s2.length();
        boolean startWith = false;
        boolean endWith = false;
        if (s2.charAt(0) == '%') {
            startWith = true;
        }
        int p = s2.indexOf('%', 1);
        if ((p > 0) && (p < s2.length() - 1)) {
            throw new IllegalArgumentException("like函数不支持中间匹配!");
        }
        if (s2.charAt(len - 1) == '%') {
            endWith = true;
        }
        s2 = s2.replace("%", "");
        if ((startWith) && (endWith)) {
            return s1.indexOf(s2) >= 0;
        }
        if (startWith) {
            return s1.startsWith(s2);
        }
        if (endWith) {
            return s1.endsWith(s2);
        }
        return s1.equals(s2);
    }

    public static boolean in(Object a, Object... a1) {
        if (a == null) {
            return false;
        }
        if ((a1 == null) || (a1.length <= 0)) {
            throw new IllegalArgumentException("In函数参数值无效!");
        }
        for (int i = 0; i < a1.length; i++) {
            if (!a.getClass().isAssignableFrom(a1[i].getClass())) {
                throw new IllegalArgumentException("In函数参数类型必须相同!");
            }
            if (compare(a, a1[i]) == 0) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean in(T object, List<T> objects) {
        return objects.indexOf(object) >= 0;
    }

    public static boolean Between(Object a, Object start, Object end) {
        if ((a == null) || (start == null) || (end == null)) {
            throw new IllegalArgumentException("Between函数参数值无效!");
        }
        if ((!a.getClass().isAssignableFrom(start.getClass())) || (!a.getClass().isAssignableFrom(end.getClass()))) {
            throw new IllegalArgumentException("Between函数参数类型必须相同!");
        }
        if ((compare(a, start) >= 0) && (compare(a, end) <= 0)) {
            return true;
        }
        return false;
    }

    public static <T extends Comparable<? super T>> int compare(T o1, T o2, boolean ignoreCase,
            boolean ignoreNullAndEmpty) {
        return ComparableUtil.compare(o1, o2, ignoreCase, ignoreNullAndEmpty);
    }

    public static int compare(Object o1, Object o2) {
        return ComparableUtil.compare(o1, o2);
    }

    public static BigDecimal trunc(BigDecimal am) {
        return am == null ? null : Convert.toBigDecimal(am, (BigDecimal) null);
    }

    public static BigDecimal round(BigDecimal amt, int scale) {
        return round(amt, scale, 4);
    }

    public static BigDecimal round(BigDecimal amt, int scale, int roundingMode) {
        if (amt == null) {
            return null;
        }
        if (scale < 0) {
            throw new IllegalArgumentException("round精度不能为负数!");
        }
        return amt.setScale(scale, roundingMode);
    }

    public static long floor(Object val) {
        if (val == null) {
            throw new IllegalArgumentException("参数不能为null!");
        }
        if ((val instanceof BigDecimal)) {
            return ((BigDecimal) val).longValue();
        }
        if (((val instanceof Double)) || ((val instanceof Float))) {
            return new Double(Math.floor(((Double) val).doubleValue())).longValue();
        }
        if ((val instanceof Long)) {
            return ((Long) val).longValue();
        }
        return 0L;
    }

}
