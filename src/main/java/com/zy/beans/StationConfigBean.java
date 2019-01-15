package com.zy.beans;

import java.util.ArrayList;
import java.util.List;

public class StationConfigBean {
	private List<SubStationBean> confiBeans = new ArrayList<SubStationBean>();
	
	public List<SubStationBean> getConfiBeans() {
		return confiBeans;
	}

	public void setConfiBeans(List<SubStationBean> confiBeans) {
		this.confiBeans = confiBeans;
	}

	public void addStation(SubStationBean bean) {
		if(!confiBeans.contains(bean))
			confiBeans.add(bean);
	}
	
	public void removeStation(SubStationBean bean) {
		if(confiBeans.contains(bean))
			confiBeans.remove(bean);
	}
}
