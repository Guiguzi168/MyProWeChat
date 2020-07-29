package org.MyProWeChat.qrcode.demo;

import org.MyProWeChat.base.qrcode.QRCodeUtil;

/**
 * 二维码生成测试
 * @author ShenPengL
 *
 */
public class QRCodeDemo {

    public static void main(String[] args) throws Exception {
        String text = "https://www.baidu.com";
        //不含Logo
        //QRCodeUtil.encode(text, null, "e:\\", true);
        //含Logo，不指定二维码图片名
        QRCodeUtil.encode(text, "D:\\Temp\\1595999643.jpg", "D:\\Temp\\qrcode", true);
        //含Logo，指定二维码图片名
//        QRCodeUtil.encode(text, "D:\\Temp\\1595999643.jpg", "D:\\Temp\\qrcode", "qrcode", true);
    }

}
