package org.MyProWeChat.demo;

/**
 * == 与 equals
 * @author ShenPengL
 *
 */
public class EqualsDemo {

    public static void main(String[] args) {
        String x = "String";
        String y = "String";
        String z = new String("String");
        System.out.println(x == y);
        System.out.println(x == z);

        System.out.println(x.equals(y));
        System.out.println(x.equals(z));
        
    }
}
