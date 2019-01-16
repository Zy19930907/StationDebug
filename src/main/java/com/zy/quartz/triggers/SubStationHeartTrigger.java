package com.zy.quartz.triggers;

import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public class SubStationHeartTrigger {
	private String triggerName;
	private String triggerGroup;
	private Trigger trigger;

	public SubStationHeartTrigger(String triggerName,String triggerGroup) {
		this.triggerName = triggerName;
		this.triggerGroup = triggerGroup;
		trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroup).startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever()).build();
	}
	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerGroup() {
		return triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	public Trigger getTrigger() {
		return trigger;
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}
}
