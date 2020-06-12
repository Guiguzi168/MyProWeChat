package com.timing.task.test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class MyScheduleTrigger {

    public static void main(String[] args) throws SchedulerException, InterruptedException {

        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        JobDetail jobDetail = JobBuilder.newJob(PrintWordsJob.class).usingJobData("jobDetail1", "这个Job用来测试的")
                .withIdentity("job1", "group1").build();
        Date startDate = new Date();
        startDate.setTime(startDate.getTime() + 5000);
        Date endDate = new Date();
        endDate.setTime(startDate.getTime() + 5000);
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
                .usingJobData("trigger1", "这是jobDetail1的trigger").startNow()// 立即生效
                .startAt(startDate).endAt(endDate)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1)// 每隔1s执行一次
                        .repeatForever())
                .build();// 一直执行
        // 4、执行
        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("--------scheduler start ! ------------");
        scheduler.start();
        // 睡眠
        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
        System.out.println("--------scheduler shutdown ! ------------");

    }

}
