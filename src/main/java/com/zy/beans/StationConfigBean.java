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
		confiBeans.add(bean);
	}
	
	public void removeStation(SubStationBean bean) {
		confiBeans.remove(bean);
	}
	
	public boolean beanExist(SubStationBean bean) {
		for(int i=0;i<confiBeans.size();i++) {
			if(bean.getIpString().equals(confiBeans.get(i).getIpString()))
				return true;
		}
		return false;
	}
}
