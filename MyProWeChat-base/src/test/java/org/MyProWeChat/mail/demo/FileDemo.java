package org.MyProWeChat.mail.demo;

import java.util.Map;

import org.MyProWeChat.base.mail.FileUtils;

public class FileDemo {

    public static void main(String[] args) throws Exception {
        String filepath = "F:test.xls";
        FileUtils excelReader = new FileUtils(filepath);
        // 对读取Excel表格标题测试
        String[] title = excelReader.readExcelTitle();
        System.out.println("获得Excel表格的标题:");
        for (String s : title) {
            System.out.print(s + " ");
        }

        // 对读取Excel表格内容测试
        Map<Integer, Map<Integer, Object>> map = excelReader.readExcelContent();
        System.out.println("获得Excel表格的内容:");
        for (int i = 1; i <= map.size(); i++) {
            System.out.println(map.get(i));
        }
    }

}
