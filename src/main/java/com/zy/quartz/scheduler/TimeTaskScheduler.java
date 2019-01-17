package com.zy.quartz.scheduler;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.zy.devices.SubStation;
import com.zy.quartz.jobdetails.SubStationJobdetil;
import com.zy.quartz.triggers.SubStationHeartTrigger;

public class TimeTaskScheduler {
	SchedulerFactory sFactory = new StdSchedulerFactory();
	Scheduler scheduler;

	public TimeTaskScheduler() {
		try {
			scheduler = sFactory.getScheduler();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void bindJob(SubStationJobdetil jobDetail,
			SubStationHeartTrigger subStationHeartTrigger) {
		try {
			scheduler.start();
			scheduler.scheduleJob(jobDetail.getJobDetail(), subStationHeartTrigger.getTrigger());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void deletJob(SubStation station) {
		TriggerKey triggerKey = TriggerKey.triggerKey(station.getBean().getIpString(), "subStationGroup");
		try {
			scheduler.pauseTrigger(triggerKey);// 停止触发器
			scheduler.unscheduleJob(triggerKey);// 移除触发器
			scheduler.deleteJob(JobKey.jobKey(station.getBean().getIpString(), "subStationGroup"));
		} catch (SchedulerException e) {
			e.printStackTrace();
		} // 删除任务
	}
}
