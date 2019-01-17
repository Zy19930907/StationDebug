package com.zou.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogRecoder {
	public static void saveLog_n(String LogFilePath, String log) {
		log+="\r\n";
		String temp = "-------------------------------------------------------------------------------------------------------------------------"
				+ DateTool.getTimeHMSS()
				+ "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n";
		String temp1 = "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n";
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
		FileWriter logWriter;
		BufferedWriter logBufferedWriter = null;
		try {
			logWriter = new FileWriter(logFile, true);
			logBufferedWriter = new BufferedWriter(logWriter);
			if (logBufferedWriter != null) {
				logBufferedWriter.write(temp);
				logBufferedWriter.flush();
				logBufferedWriter.write(log);
				logBufferedWriter.flush();
				logBufferedWriter.write(temp1);
				logBufferedWriter.flush();
				logBufferedWriter.close();
				logBufferedWriter = null;
			}
			if (logWriter != null) {
				logWriter.close();
				logWriter = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			try {
				logBufferedWriter.write(e.getMessage());
				logBufferedWriter.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
