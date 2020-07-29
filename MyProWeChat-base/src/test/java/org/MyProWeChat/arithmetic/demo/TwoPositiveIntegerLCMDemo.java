package org.MyProWeChat.arithmetic.demo;

import java.util.Scanner;

/**
 * 求两正整数最小公倍数算法
 * @author ShenPengL
 *
 */
public class TwoPositiveIntegerLCMDemo {

    /**
     * 正整数A和正整数B 的最小公倍数是指 能被A和B整除的最小的正整数值，
     * 设计一个算法，求输入A和B的最小公倍数。
     * @param args
     */
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanr = new Scanner(System.in); 
        while(scanr.hasNext()) {
            int m = scanr.nextInt();
            int n = scanr.nextInt();
            System.out.println(n*m/getResult(n, m));
        }
    }
    
    public static int getResult(int n, int m) {
        //判断n m值大小，若m<n，则两数互换
        if(m < n) {
            int temp = m;
            m = n;
            n = temp;
        }
        int k;
        //判断n是否=0，不等于0时，m值取n参数
        while(n != 0) {
            k = m % n;
            m = n;
            n = k;
        }
        return m;
    }
}
