package com.zou.tools;

import com.zy.beans.StationConfigBean;
import com.zy.beans.SubStationBean;
import com.zy.devices.SubStation;

import StationDebug.App;

public class SubStationManger {
	private StationConfigBean configBean = new StationConfigBean();

	public void addNewSubStation(SubStationBean bean) {
		if (!configBean.beanExist(bean)) {
			configBean.addStation(bean);
			SubStation station = new SubStation(bean);
			App.mainView.AddNewNode(bean.getIpString(), station);
			App.configManager.saveConfig(System.getProperty("user.dir") + "\\Configs\\SubStationConfig.txt",
					configBean);
		}
	}

	public void AddSubStationFromConfig() {
		configBean = App.configManager.readConfig(System.getProperty("user.dir") + "\\Configs\\SubStationConfig.txt");
		if (configBean == null) {
			configBean = new StationConfigBean();
			App.configManager.saveConfig(System.getProperty("user.dir") + "\\Configs\\SubStationConfig.txt",
					configBean);
		} else {
			for (int i = 0; i < configBean.getConfiBeans().size(); i++) {
				SubStation station = new SubStation(configBean.getConfiBeans().get(i));
				App.mainView.AddNewNode(configBean.getConfiBeans().get(i).getIpString(), station);
			}
		}
	}
	
	public void removeStation(SubStation station) {
		configBean.removeStation(station.getBean());
		App.configManager.saveConfig(System.getProperty("user.dir") + "\\Configs\\SubStationConfig.txt",
				configBean);
	}
	
	public void upDateConfig() {
		App.configManager.saveConfig(System.getProperty("user.dir") + "\\Configs\\SubStationConfig.txt",
				configBean);
	}
}
