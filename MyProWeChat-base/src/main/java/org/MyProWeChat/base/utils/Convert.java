package org.MyProWeChat.base.utils;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Convert {

//    private static final String HEX_PREFIX = "0x";
//    private static final int HEX_RADIX = 16;
    @SuppressWarnings("rawtypes")
    private static final Class[] CONSTR_ARGS = {String.class};

    public static Boolean toBoolean(Object value, Boolean defaultVal) {
        if (value == null) {
            return defaultVal;
        }
        if ((value instanceof Boolean)) {
            return (Boolean) value;
        }
        if ((value instanceof String)) {
            return "true".equalsIgnoreCase((String) value) ? Boolean.TRUE : Boolean.FALSE;
        }
        return defaultVal;
    }

    public static Boolean toBoolean(Object value) {
        return toBoolean(value, Boolean.FALSE);
    }

    public static java.util.Date toDate(Object value, String pattern) throws IllegalArgumentException {
        if (value == null) {
            return null;
        }
        if ((value instanceof java.util.Date)) {
            return (java.util.Date) value;
        }
        if ((value instanceof Calendar)) {
            return ((Calendar) value).getTime();
        }
        if ((value instanceof String)) {
            String strVal = (String) value;
            if (strVal.trim().length() == 0) {
                return null;
            }
            try {
                DateFormat _formater = new SimpleDateFormat(pattern);
                java.util.Date _date = _formater.parse((String) value);
                if (strVal.equals(_formater.format(_date))) {
                    return _date;
                }
                throw new IllegalArgumentException("模式:[" + pattern + "]与时间串:[" + value + "]不符");
            } catch (Exception e) {
                throw new IllegalArgumentException("不能使用模式:[" + pattern + "]格式化时间串:[" + value + "]");
            }
        }
        throw new IllegalArgumentException(
                "不能使用模式:[" + pattern + "]格式化未知对象:[" + value + "]" + value.getClass().getName());
    }

    public static java.util.Date toDate(Object value, String pattern, java.util.Date defaultVal) {
        java.util.Date ret = null;
        try {
            ret = toDate(value, pattern);
        } catch (IllegalArgumentException e) {
            ret = defaultVal;
        }
        return ret == null ? defaultVal : ret;
    }

    public static java.util.Date toDate(Object value) {
        return toDate(value, "yyyyMMdd", null);
    }

    public static Calendar toCalendar(Object value, String pattern) throws IllegalArgumentException {
        if (value == null) {
            return null;
        }
        if ((value instanceof Calendar)) {
            return (Calendar) value;
        }
        if ((value instanceof java.util.Date)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime((java.util.Date) value);
            return calendar;
        }
        if ((value instanceof String)) {
            String strVal = (String) value;
            if (strVal.trim().length() == 0) {
                return null;
            }
            try {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new SimpleDateFormat(pattern).parse(strVal));
                return calendar;
            } catch (ParseException e) {
                throw new IllegalArgumentException("The value " + value + " can't be converted to a Calendar");
            }
        }
        throw new IllegalArgumentException("The value " + value + " can't be converted to a Calendar");
    }

    public static Timestamp toTimestamp(Object value) {
        if (value == null) {
            return null;
        }
        if ((value instanceof Timestamp)) {
            return (Timestamp) value;
        }
        java.util.Date _date = toDate(value, "yyyy-MM-dd HH:mm:ss", null);
        if (_date == null) {
            return null;
        }
        return new Timestamp(_date.getTime());
    }

    public static java.sql.Date toSqlDate(Object value) {
        if (value == null) {
            return null;
        }
        if ((value instanceof java.sql.Date)) {
            return (java.sql.Date) value;
        }
        if ((value instanceof Long)) {
            return new java.sql.Date(((Long) value).longValue());
        }
        java.util.Date _date = toDate(value, "yyyy-MM-dd", null);
        if (_date == null) {
            if ((value instanceof String)) {
                try {
                    long lDate = Long.parseLong((String) value);
                    return new java.sql.Date(lDate);
                } catch (Exception localException) {
                }
            }
            return null;
        }
        return new java.sql.Date(_date.getTime());
    }

    public static Time toSqlTime(Object value) {
        if (value == null) {
            return null;
        }
        if ((value instanceof Time)) {
            return (Time) value;
        }
        java.util.Date _date = toDate(value, "yyyy-MM-dd HH:mm:ss", null);
        if (_date == null) {
            return null;
        }
        return new Time(_date.getTime());
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    static Number toNumber(Object value, Class targetClass) throws IllegalArgumentException {
        if (value == null) {
            return null;
        }
        if ((value instanceof Number)) {
            return (Number) value;
        }
        String str = value.toString();
        if (str.startsWith("0x")) {
            try {
                return new BigInteger(str.substring("0x".length()), 16);
            } catch (NumberFormatException nex) {
                throw new IllegalArgumentException(
                        "Could not convert '" + str + "' to " + targetClass.getName() + "! Invalid hex number.");
            }
        }
        try {
            Constructor constr = targetClass.getConstructor(CONSTR_ARGS);
            return (Number) constr.newInstance(new Object[]{str});
        } catch (InvocationTargetException itex) {
            throw new IllegalArgumentException("Could not convert '" + str + "' to " + targetClass.getName());
        } catch (Exception ex) {
            throw new IllegalArgumentException(
                    "Conversion error when trying to convert " + str + " to " + targetClass.getName());
        }
    }

    public static Byte toByte(Object value) throws IllegalArgumentException {
        if (value == null) {
            return null;
        }
        Number n = toNumber(value, Byte.class);
        if ((n instanceof Byte)) {
            return (Byte) n;
        }
        return new Byte(n.byteValue());
    }

    public static Byte toByte(Object value, Byte defaultVal) {
        Byte ret = null;
        try {
            ret = toByte(value);
        } catch (Exception e) {
            ret = defaultVal;
        }
        return ret == null ? defaultVal : ret;
    }

    public static Short toShort(Object value) throws IllegalArgumentException {
        if (value == null) {
            return null;
        }
        Number n = toNumber(value, Short.class);
        if ((n instanceof Short)) {
            return (Short) n;
        }
        return new Short(n.shortValue());
    }

    public static Short toShort(Object value, Short defaultVal) {
        Short ret = null;
        try {
            ret = toShort(value);
        } catch (Exception e) {
            ret = defaultVal;
        }
        return ret == null ? defaultVal : ret;
    }

    public static Float toFloat(Object value) throws IllegalArgumentException {
        if (value == null) {
            return null;
        }
        Number n = toNumber(value, Float.class);
        if ((n instanceof Float)) {
            return (Float) n;
        }
        return new Float(n.floatValue());
    }

    public static Float toFloat(Object value, Float defaultVal) {
        Float ret = null;
        try {
            ret = toFloat(value);
        } catch (Exception e) {
            ret = defaultVal;
        }
        return ret == null ? defaultVal : ret;
    }

    public static Long toLong(Object value) throws IllegalArgumentException {
        if (value == null) {
            return null;
        }
        Number n = toNumber(value, Long.class);
        if ((n instanceof Long)) {
            return (Long) n;
        }
        return new Long(n.longValue());
    }

    public static Long toLong(Object value, Long defaultVal) {
        Long ret = null;
        try {
            ret = toLong(value);
        } catch (Exception e) {
            ret = defaultVal;
        }
        return ret == null ? defaultVal : ret;
    }

    public static Integer toInteger(Object value) throws IllegalArgumentException {
        if (value == null) {
            return null;
        }
        Number n = toNumber(value, Integer.class);
        if ((n instanceof Integer)) {
            return (Integer) n;
        }
        return new Integer(n.intValue());
    }

    public static Integer toInteger(Object value, Integer defaultVal) {
        Integer ret = null;
        try {
            ret = toInteger(value);
        } catch (Exception e) {
            ret = defaultVal;
        }
        return ret == null ? defaultVal : ret;
    }

    public static Double toDouble(Object value) throws IllegalArgumentException {
        if (value == null) {
            return null;
        }
        Number n = toNumber(value, Double.class);
        if ((n instanceof Double)) {
            return (Double) n;
        }
        return new Double(n.doubleValue());
    }

    public static Double toDouble(Object value, Double defaultVal) {
        Double ret = null;
        try {
            ret = toDouble(value);
        } catch (Exception e) {
            ret = defaultVal;
        }
        return ret == null ? defaultVal : ret;
    }

    public static BigDecimal toBigDecimal(Object value) throws IllegalArgumentException {
        if (value == null) {
            return null;
        }
        Number n = toNumber(value, BigDecimal.class);
        if ((n instanceof BigDecimal)) {
            return (BigDecimal) n;
        }
        return new BigDecimal(n.doubleValue());
    }

    public static BigDecimal toBigDecimal(Object value, BigDecimal defaultVal) {
        BigDecimal ret = null;
        try {
            ret = toBigDecimal(value);
        } catch (Exception e) {
            ret = defaultVal;
        }
        return ret == null ? defaultVal : ret;
    }

    public static BigDecimal toBigDecimal(Object value, String pattern) throws IllegalArgumentException {
        if (value == null) {
            return null;
        }
        if ((value instanceof BigDecimal)) {
            return (BigDecimal) value;
        }
        if ((value instanceof Number)) {
            return new BigDecimal(((Number) value).doubleValue());
        }
        String str = value.toString();
        DecimalFormat df = new DecimalFormat(pattern);
        Number num;
        try {
            num = df.parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException("不能使用模式:[" + pattern + "]解析数字串:[" + value + "]");
        }
        BigDecimal ret;
        if ((num instanceof BigDecimal)) {
            ret = (BigDecimal) num;
        } else {
            ret = new BigDecimal(num.doubleValue());
        }
        return ret;
    }

    public static BigDecimal toBigDecimal(Object value, String pattern, BigDecimal defaultVal) {
        BigDecimal ret = null;
        try {
            ret = toBigDecimal(value, pattern);
        } catch (Exception e) {
            ret = defaultVal;
        }
        return ret == null ? defaultVal : ret;
    }

    public static BigDecimal toAmount(Object value) {
        return toBigDecimal(value, "#,##0.##", null);
    }

    public static byte[] charsToBytes(char[] source, int srclen) {
        if (source == null) {
            return null;
        }
        int len = source.length;
        if (len > srclen) {
            len = srclen;
        }
        byte[] dest = new byte[len];
        for (int i = 0; i < len; i++) {
            dest[i] = ((byte) source[i]);
        }
        return dest;
    }

    public static char[] bytesToChars(byte[] source, int srclen) {
        if (source == null) {
            return null;
        }
        int len = source.length;
        if (len > srclen) {
            len = srclen;
        }
        char[] destChar = new char[len];
        for (int i = 0; i < len; i++) {
            if (source[i] >= 0) {
                destChar[i] = ((char) source[i]);
            } else {
                destChar[i] = ((char) (256 + source[i]));
            }
        }
        return destChar;
    }

    @SuppressWarnings("rawtypes")
    public static Locale toLocale(Object value) throws IllegalArgumentException {
        if (value == null) {
            return null;
        }
        if ((value instanceof Locale)) {
            return (Locale) value;
        }
        if ((value instanceof String)) {
            List elements = StringUtil.split((String) value, '_');
            int size = elements.size();
            if ((size >= 1)
                    && ((((String) elements.get(0)).length() == 2) || (((String) elements.get(0)).length() == 0))) {
                String language = (String) elements.get(0);
                String country = (String) (size >= 2 ? elements.get(1) : "");
                String variant = (String) (size >= 3 ? elements.get(2) : "");

                return new Locale(language, country, variant);
            }
            throw new IllegalArgumentException("The value " + value + " can't be converted to a Locale");
        }
        throw new IllegalArgumentException("The value " + value + " can't be converted to a Locale");
    }

    public static Color toColor(Object value) throws IllegalArgumentException {
        if (value == null) {
            return null;
        }
        if ((value instanceof Color)) {
            return (Color) value;
        }
        if (((value instanceof String)) && (!StringUtil.isBlank((String) value))) {
            String color = ((String) value).trim();

            int[] components = new int[3];

            int minlength = components.length * 2;
            if (color.length() < minlength) {
                throw new IllegalArgumentException("The value " + value + " can't be converted to a Color");
            }
            if (color.startsWith("#")) {
                color = color.substring(1);
            }
            try {
                for (int i = 0; i < components.length; i++) {
                    components[i] = Integer.parseInt(color.substring(2 * i, 2 * i + 2), 16);
                }
                int alpha;
                if (color.length() >= minlength + 2) {
                    alpha = Integer.parseInt(color.substring(minlength, minlength + 2), 16);
                } else {
                    alpha = Color.black.getAlpha();
                }
                return new Color(components[0], components[1], components[2], alpha);
            } catch (Exception e) {
                throw new IllegalArgumentException("The value " + value + " can't be converted to a Color");
            }
        }
        throw new IllegalArgumentException("The value " + value + " can't be converted to a Color");
    }
}
