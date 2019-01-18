package com.zou.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.JFileChooser;
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
		songFilter = new FileNameExtensionFilter("音频文件(*.mp3;*.wav)", "mp3", "MP3", "wav", "WAV");
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setFileFilter(songFilter);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

		if (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
			return;
		File[] files = fileChooser.getSelectedFiles();
		File destFile;
		gsonBuilder.setPrettyPrinting();
		gson = gsonBuilder.create();
		for(i=0;i<files.length;i++){
			musicBean = null;
			musicBean = new MusicBean();
			musicBean.setIndex(i+1);
			musicBean.setMusicName(files[i].getName());
			listBean.addMusicBean(musicBean);
			destFile = new File(System.getProperty("user.dir")+"/MusicOutPut/"+String.valueOf(i+1)+files[i].getName().substring(files[i].getName().lastIndexOf(".")));
			if(!destFile.exists()){
				File fileParent = destFile.getParentFile();
				fileParent.mkdirs();
				try {
					destFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				copyFileUsingFileChannels(files[i],destFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		saveConfig(System.getProperty("user.dir")+"/MusicOutPut/"+"MusicList.list",listBean,gson);
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
	public static void saveConfig(String LogFilePath, MusicListBean bean,Gson gson) {
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
		JFileChooser fileChooser;
		FileNameExtensionFilter configFilter;
		MusicListBean listBean;
		Gson gson;
		GsonBuilder gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.create();
		String config = null;
		fileChooser = new JFileChooser();
		configFilter = new FileNameExtensionFilter("列表文件(*.list)", "list");
		fileChooser.setFileFilter(configFilter);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")+"/MusicOutPut"));
		
		if (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
			return null;
		File file = fileChooser.getSelectedFile();
		try {
			if(login)
				config = FileUtils.readFileToString(file,"GB2312");
			else
				config = FileUtils.readFileToString(file,"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		listBean = gson.fromJson(config, MusicListBean.class);
		return listBean;
	}
}
