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
        try {
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
        try {
            FileUtils.writeStringToFile(logFile, log, "UTF-8", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
