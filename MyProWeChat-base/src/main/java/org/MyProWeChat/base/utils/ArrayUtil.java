package org.MyProWeChat.base.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArrayUtil {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> T[] rtrim(T[] arr) {
        List<T> ret = new ArrayList();
        for (int i = arr.length - 1; i >= 0; i--) {
            if ((!StringUtil.isBlank(arr[i])) || (ret.size() > 0)) {
                ret.add(0, arr[i]);
            }
        }
        return (T[]) ret.toArray((Object[]) Array.newInstance(arr.getClass().getComponentType(), 0));
    }

    public static String join(Object o) {
        return join(o, ",");
    }

    public static String join(Object o, String split) {
        if (o == null) {
            return null;
        }
        Object[] arr = asArray(o);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length - 1; i++) {
            sb.append(arr[i]).append(split);
        }
        if (arr.length > 0) {
            sb.append(arr[(arr.length - 1)]);
        }
        return sb.toString();
    }

    public static boolean arrayEquals(Object[] a1, Object[] a2) {
        return arrayEquals(a1, a2, null);
    }

    public static boolean arrayEquals(Object[] a1, Object[] a2, Object wildcardValue) {
        if ((a1 == null) && (a2 == null)) {
            return true;
        }
        if ((a1 == null) || (a2 == null)) {
            return false;
        }
        if (a1.length != a2.length) {
            return false;
        }
        for (int i = 0; i < a1.length; i++) {
            if ((a1[i] != null) || (a2[i] != null)) {
                if ((a1[i] == null) || (!a1[i].equals(wildcardValue))) {
                    if ((a1[i] == null) || (a2[i] == null)) {
                        return false;
                    }
                    if (!a1[i].equals(a2[i])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean notIn(Object o, Object arr) {
        return !in(o, arr);
    }

    public static boolean in(Object o, Object arr) {
        if ((o == null) || (arr == null)) {
            return false;
        }
        Object[] os = asArray(o);
        Object[] os2 = asArray(arr);
        for (int i = 0; i < os.length; i++) {
            for (int j = 0; j < os2.length; j++) {
                if (((os[i] instanceof String)) && ((os2[j] instanceof String))) {
                    if (((String) os[i]).trim().equals(((String) os2[j]).trim())) {
                        return true;
                    }
                } else if (((os[i] instanceof String)) && ((os2[j] instanceof Boolean))) {
                    if (os[i].equals(os2[j].toString())) {
                        return true;
                    }
                } else if (os[i].equals(os2[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Object[] asArray(Object o) {
        return asArray(o, true);
    }

    @SuppressWarnings("rawtypes")
    public static Object[] asArray(Object o, boolean splitString) {
        if (o == null) {
            return new Object[0];
        }
        if ((o instanceof Collection)) {
            Collection<?> c = (Collection) o;
            return c.toArray(new Object[c.size()]);
        }
        if (((o instanceof String[])) && (((String[]) o).length == 1)) {
            if (splitString) {
                return ((String[]) (String[]) o)[0].split(",");
            }
            return (String[]) o;
        }
        if (o.getClass().isArray()) {
            Object[] ret = new Object[Array.getLength(o)];
            for (int i = 0; i < ret.length; i++) {
                ret[i] = Array.get(o, i);
            }
            return ret;
        }
        if (((o instanceof String)) && (splitString)) {
            String s = (String) o;
            return StringUtil.split(s).toArray();
        }
        return new Object[]{o};
    }

    public static int indexOf(Object[] array, Object match) {
        int ret = -1;
        for (int i = 0; i < array.length; i++) {
            if ((array[i] != null) && (array[i].equals(match))) {
                return i;
            }
        }
        return ret;
    }
}
