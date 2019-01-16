package com.zy.quartz.quartzjobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zy.devices.SubStation;

public class SubStationHeartJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		((SubStation)(context.getJobDetail().getJobDataMap().get("subStation"))).SendHeart();
	}
}
