package org.MyProWeChat.base.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class PrintWordsJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //参数练习
        System.out.println(jobExecutionContext.getJobDetail().getJobDataMap().get("jobDetail1"));
        System.out.println(jobExecutionContext.getTrigger().getJobDataMap().get("trigger1"));
        //简易练习
        String printTime = new SimpleDateFormat("yy-MM-dd HH-mm-ss").format(new Date());
        System.out.println("PrintWordsJob start at:" + printTime + ", prints: Hello Job-" + new Random().nextInt(100));
    }
}
