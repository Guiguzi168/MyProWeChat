package org.MyProWeChat.demo;

/**
 * 如何将字符串反转？
 * @author ShenPengL
 *
 */
public class StringReverse {

    public static void main(String[] args) {
        // TODO 如何将字符串反转？
        // StringBuffer reverse
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("abcdefg");
        System.out.println(stringBuffer.reverse()); // gfedcba
        // StringBuilder reverse
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("abcdefg");
        System.out.println(stringBuilder.reverse()); // gfedcba
    }

}
