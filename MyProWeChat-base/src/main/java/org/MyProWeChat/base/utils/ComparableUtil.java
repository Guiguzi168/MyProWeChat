package org.MyProWeChat.base.utils;

public class ComparableUtil {

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> int compare(T o1, T o2, boolean ignoreCase,
            boolean ignoreNullAndEmpty) {
        if (o1 == o2) {
            return 0;
        }
        if (o1 == null) {
            if ((ignoreNullAndEmpty) && (String.class.isAssignableFrom(o2.getClass())) && ("".equals(o2))) {
                return 0;
            }
            return -1;
        }
        if (o2 == null) {
            if ((ignoreNullAndEmpty) && (String.class.isAssignableFrom(o1.getClass())) && ("".equals(o1))) {
                return 0;
            }
            return 1;
        }
        if ((ignoreCase) && (String.class.isAssignableFrom(o1.getClass()))
                && (String.class.isAssignableFrom(o2.getClass()))) {
            return ((String) o1).compareToIgnoreCase((String) o2);
        }
        if ((o1 != null) && (o1.getClass().isEnum())) {
            o1 = (T) String.valueOf(o1);
        }
        if ((o2 != null) && (o2.getClass().isEnum())) {
            o2 = (T) String.valueOf(o2);
        }
        return o1.compareTo(o2);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static int compare(Object o1, Object o2) {
        if ((o1 != null) && (!(o1 instanceof Comparable))) {
            throw new RuntimeException(String.format("比较的类型必须是Comparable,否则无法进行比较", 
                    o1.toString()));
        }
        if ((o2 != null) && (!(o2 instanceof Comparable))) {
            throw new RuntimeException(String.format("比较的类型必须是Comparable,否则无法进行比较", 
                    o1.toString()));
        }
        return compare((Comparable) o1, (Comparable) o2, false, true);
    }
}
