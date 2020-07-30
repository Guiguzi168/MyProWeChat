package org.MyProWeChat.base.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CommUtil {

    public static Map<String, Object> toMap(Object bean) {
        return CommUtil_.toMap(bean);
    }

    public static List<Map<String, Object>> toListMap(List<?> list) {
        return CommUtil_.toListMap(list);
    }

    public static <T> T nvl(T s, T defaultValue) {
        return (T) CommUtil_.nvl(s, defaultValue);
    }

    public static boolean isNull(Object o) {
        return CommUtil_.isNull(o);
    }

    public static boolean isNotNull(Object o) {
        return CommUtil_.isNotNull(o);
    }

    public static String trim(String s) {
        return CommUtil_.trim(s);
    }

    public static String rtrim(String s) {
        return CommUtil_.rtrim(s);
    }

    public static String ltrim(String s) {
        return CommUtil_.ltrim(s);
    }

    public static boolean like(String s1, String s2) {
        return CommUtil_.like(s1, s2);
    }


    public static boolean in(Object a, Object... a1) {
        return CommUtil_.in(a, a1);
    }

    public static <T> boolean in(T object, List<T> objects) {
        return CommUtil_.in(object, objects);
    }

    public static boolean Between(String a, String start, String end) {
        return CommUtil_.Between(a, start, end);
    }

    public static boolean Between(Integer a, int start, int end) {
        return CommUtil_.Between(a, Integer.valueOf(start), Integer.valueOf(end));
    }

    public static boolean Between(int a, int start, int end) {
        return CommUtil_.Between(Integer.valueOf(a), Integer.valueOf(start), Integer.valueOf(end));
    }

    public static boolean Between(BigDecimal a, BigDecimal start, BigDecimal end) {
        return CommUtil_.Between(a, start, end);
    }

    public static <T extends Comparable<T>> int compare(T o1, T o2) {
        return CommUtil_.compare(o1, o2, false, true);
    }

    public static int compareIgnoreCase(String o1, String o2) {
        return CommUtil_.compare(o1, o2, true, true);
    }

    public static boolean equals(BigDecimal o1, BigDecimal o2) {
        return CommUtil_.compare(o1, o2) == 0;
    }

    public static boolean equals(String str1, String str2) {
        return CommUtil_.compare(str1, str2, false, true) == 0;
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return CommUtil_.compare(str1, str2, true, true) == 0;
    }

    public static boolean equals(String str1, String str2, boolean ignoreCase, boolean ignoreNullAndEmpty) {
        return CommUtil_.compare(str1, str2, ignoreCase, ignoreNullAndEmpty) == 0;
    }

    public static BigDecimal trunc(BigDecimal am) {
        return am == null ? null : Convert.toBigDecimal(am, (BigDecimal) null);
    }

    public static BigDecimal round(BigDecimal amt, int scale) {
        return round(amt, scale, 4);
    }

    private static BigDecimal round(BigDecimal amt, int scale, int roundingMode) {
        return CommUtil_.round(amt, scale, roundingMode);
    }

    public static long floor(Object val) {
        return CommUtil_.floor(val);
    }


    public static enum Operator {
        add("add", "add", "加"), sub("sub", "sub", "减"), mul("mul", "mul", "乘"), div("div", "div", "除");

        @SuppressWarnings("unused")
        private String id;
        @SuppressWarnings("unused")
        private String value;
        @SuppressWarnings("unused")
        private String longName;

        private Operator(String id, String value, String longName) {
            this.id = id;
            this.value = value;
            this.longName = longName;
        }

        public Object add(Object data1, Object data2) {
            return null;
        }

        public Object sub(Object data1, Object data2) {
            return null;
        }

        public Object mul(Object data1, Object data2) {
            return null;
        }

        public Object div(Object data1, Object data2) {
            return null;
        }
    }
}
