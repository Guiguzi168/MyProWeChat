package org.MyProWeChat.date.demo;

import java.util.Calendar;
import java.util.Date;

import org.MyProWeChat.base.utils.date.DateUtils;

@SuppressWarnings("unused")
public class DateDemo {

    public static void main(String[] args) {
//        Calendar calendar = DateUtils.getCalendar(new Date());
//        System.out.println("将 Date 日期转化为 Calendar 类型日期." + calendar);
        //获取当前季度
        Integer currentSeason = DateUtils.getCurrentSeason();
        System.out.println("获取当前季度：" + currentSeason);
        String date = DateUtils.getDate();
        System.out.println("得到当前日期字符串：" + date);
        String dateTime = DateUtils.getDateTime();
        System.out.println("得到当前日期和时间字符串：" + dateTime);
//        DateUtils.pastDays(date)
        long seconds = 7200;
        String intervalBySeconds = DateUtils.getIntervalBySeconds(seconds);
        System.out.println("将以秒为单位的时间转换为其他单位：" + intervalBySeconds);
        System.out.println("sns 格式 将以秒为单位的时间转换为其他单位：" + DateUtils.snsFormat(seconds));
    }

}
