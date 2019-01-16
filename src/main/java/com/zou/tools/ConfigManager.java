package com.zou.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zy.beans.StationConfigBean;

public class ConfigManager {
	private BufferedWriter configBufferedWriter = null;
	private FileWriter configWriter;
	private GsonBuilder gsonBuilder;
	Gson gson;

	public ConfigManager() {
		gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		gson = gsonBuilder.create();
	}

	public void saveConfig(String LogFilePath, String log) {
		File logFile = new File(LogFilePath);
		File fileParent = logFile.getParentFile();

		if (!logFile.exists()) {
			fileParent.mkdirs();
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			configWriter = new FileWriter(logFile);
			configBufferedWriter = new BufferedWriter(configWriter);
			if (configBufferedWriter != null) {
				configBufferedWriter.write(log);
				configBufferedWriter.flush();
				configBufferedWriter.close();
				configBufferedWriter = null;
			}
			if (configWriter != null) {
				configWriter.close();
				configWriter = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveConfig(String LogFilePath, StationConfigBean bean) {
		File logFile = new File(LogFilePath);
		File fileParent = logFile.getParentFile();
		if (!logFile.exists()) {
			fileParent.mkdirs();
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			configWriter = new FileWriter(logFile);
			configBufferedWriter = new BufferedWriter(configWriter);
			if (configBufferedWriter != null) {
				configBufferedWriter.write(gson.toJson(bean));
				configBufferedWriter.flush();
				configBufferedWriter.close();
				configBufferedWriter = null;
			}
			if (configWriter != null) {
				configWriter.close();
				configWriter = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public StationConfigBean readConfig(String configFilePath) {
		String config = null;
		File logFile = new File(configFilePath);
		File fileParent = logFile.getParentFile();
		if (!logFile.exists()) {
			fileParent.mkdirs();
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			config = FileUtils.readFileToString(logFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(config != null)
			return gson.fromJson(config, StationConfigBean.class);
		else
			return null;
	}
}
