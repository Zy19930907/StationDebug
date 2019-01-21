package com.zou.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zy.beans.MusicBean;
import com.zy.beans.MusicListBean;

public class FileTool {

    public static void ChooseDir() {
        JFileChooser fileChooser;
        FileNameExtensionFilter songFilter;
        Gson gson;
        GsonBuilder gsonBuilder = new GsonBuilder();
        MusicListBean listBean = new MusicListBean();
        MusicBean musicBean;
        int i;
        fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        if (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
            return;
        delFolder(System.getProperty("user.dir") + "/MusicOutPut/");
        File[] files = fileChooser.getSelectedFile().listFiles();
        File destFile;
        gsonBuilder.setPrettyPrinting();
        gson = gsonBuilder.create();
        for (i = 0; i < files.length; i++) {
            if (files[i].getName().endsWith("mp3") || files[i].getName().endsWith("MP3") || files[i].getName().endsWith("wav")
                    || files[i].getName().endsWith("WAV")) {
                musicBean = null;
                musicBean = new MusicBean();
                musicBean.setIndex(i + 1);
                musicBean.setMusicName(files[i].getName());
                listBean.addMusicBean(musicBean);
                destFile = new File(System.getProperty("user.dir") + "/MusicOutPut/" + String.valueOf(i + 1)
                        + files[i].getName().substring(files[i].getName().lastIndexOf(".")));
                if (!destFile.exists()) {
                    File fileParent = destFile.getParentFile();
                    fileParent.mkdirs();
                    try {
                        destFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    copyFileUsingFileChannels(files[i], destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        saveConfig(System.getProperty("user.dir") + "/MusicOutPut/" + "MusicList.txt", listBean, gson);
    }

    @SuppressWarnings("resource")
    private static void copyFileUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }

    public static void saveConfig(String LogFilePath, MusicListBean bean, Gson gson) {
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
            FileUtils.writeStringToFile(logFile, gson.toJson(bean), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MusicListBean readMusicListBean(boolean login) {
//		JFileChooser fileChooser;
//		FileNameExtensionFilter configFilter;
        MusicListBean listBean = null;
        Gson gson;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        String config = null;
//		fileChooser = new JFileChooser();
//		configFilter = new FileNameExtensionFilter("列表文件(*.list)", "list");
//		fileChooser.setFileFilter(configFilter);
//		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")+"/MusicOutPut"));
//		
//		if (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
//			return null;
        File file = new File(System.getProperty("user.dir") + "\\MusicOutPut\\MusicList.txt");
        if (file.exists()) {
            try {
                if (login)
                    config = FileUtils.readFileToString(file, "GB2312");
                else
                    config = FileUtils.readFileToString(file, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            listBean = gson.fromJson(config, MusicListBean.class);
        } else {
            JOptionPane.showMessageDialog(null, "音乐列表文件不存在", "音乐列表不存在", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (listBean == null)
            JOptionPane.showMessageDialog(null, "音乐列表文件错误", "音乐列表错误", JOptionPane.ERROR_MESSAGE);
        return listBean;
    }

    // 删除文件夹
    // param folderPath 文件夹完整绝对路径

    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 删除指定文件夹下所有文件
    // param path 文件夹完整绝对路径
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }
}
