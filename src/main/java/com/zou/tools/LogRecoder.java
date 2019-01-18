package com.zou.tools;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class LogRecoder {
	public static void saveLog_h(String LogFilePath, String log) {
		log += "\r\n";
		String temp = "---------------------------------------------------------------------------------------------------------------------"
				+ "-------------------------------------------------------------------------------------------------------------------------\r\n";
		File logFile = new File(LogFilePath);
		if (!logFile.exists()) {
			File fileParent = logFile.getParentFile();
			fileParent.mkdirs();
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//		FileWriter logWriter;
//		BufferedWriter logBufferedWriter = null;
		try {
//			logWriter = new FileWriter(logFile, true);
//			logBufferedWriter = new BufferedWriter(logWriter);
//			if (logBufferedWriter != null) {
//				logBufferedWriter.write(temp);
//				logBufferedWriter.flush();
//				logBufferedWriter.write(log);
//				logBufferedWriter.flush();
//				logBufferedWriter.close();
//				logBufferedWriter = null;
//			}
//			if (logWriter != null) {
//				logWriter.close();
//				logWriter = null;
//			}
			FileUtils.writeStringToFile(logFile, temp + log, "UTF-8", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveLog_m(String LogFilePath, String log) {
		log += "\r\n";
		File logFile = new File(LogFilePath);
		if (!logFile.exists()) {
			File fileParent = logFile.getParentFile();
			fileParent.mkdirs();
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//		FileWriter logWriter;
//		BufferedWriter logBufferedWriter = null;
		try {
//			logWriter = new FileWriter(logFile, true);
//			logBufferedWriter = new BufferedWriter(logWriter);
//			if (logBufferedWriter != null) {
//				logBufferedWriter.write(log);
//				logBufferedWriter.flush();
//				logBufferedWriter.close();
//				logBufferedWriter = null;
//			}
//			if (logWriter != null) {
//				logWriter.close();
//				logWriter = null;
//			}
			FileUtils.writeStringToFile(logFile, log, "UTF-8", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
