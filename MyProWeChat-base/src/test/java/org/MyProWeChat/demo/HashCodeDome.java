package org.MyProWeChat.demo;

/**
 * hashCode相同，equals未必相同
 * 
 * @author ShenPengL
 *
 */
public class HashCodeDome {

    public static void main(String[] args) {
        String x = "string";
        String y = "string";
        System.out.println(String.format("对比元素x：%s | 对比元素y：%s", x, y));
        System.out.println(String.format("str1：%d | str2：%d", x.hashCode(), y.hashCode()));
        System.out.println(x.equals(y));

        String str1 = "通话";
        String str2 = "重地";
        System.out.println(String.format("对比元素：%s | %s", str1, str2));
        System.out.println(String.format("str1：%d | str2：%d", str1.hashCode(), str2.hashCode()));
        System.out.println(str1.equals(str2));
    }

}
