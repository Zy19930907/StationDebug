package com.zou.tools;

import java.util.ArrayList;
import java.util.List;

import com.zy.beans.StationConfigBean;
import com.zy.beans.SubStationBean;
import com.zy.devices.SubStation;

import StationDebug.App;

public class SubStationManger {
	private StationConfigBean configBean = new StationConfigBean();
	private List<SubStation> subStations = new ArrayList<SubStation>();
	
	public void addNewSubStation(SubStationBean bean) {
		configBean.addStation(bean);
		SubStation station = new SubStation(bean);
		App.mainView.AddNewNode(bean.getIpString(), station);
	}
}
