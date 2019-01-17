package com.zy.quartz.jobdetails;

import java.util.HashMap;
import java.util.Map;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;

import com.zy.devices.SubStation;
import com.zy.quartz.quartzjobs.SubStationHeartJob;


public class SubStationJobdetil {
	private JobDetail jobDetail;
	private Map<String, SubStation> stationJobDetailMap = new HashMap<String, SubStation>();
	public JobDetail getJobDetail() {
		return jobDetail;
	}
	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}
	public  SubStationJobdetil(SubStation station) {
		stationJobDetailMap.put("subStation", station);
		jobDetail = JobBuilder.newJob(SubStationHeartJob.class)
				.withIdentity(station.getBean().getIpString(), "subStationGroup").usingJobData(new JobDataMap(stationJobDetailMap)).build();
	}
}
