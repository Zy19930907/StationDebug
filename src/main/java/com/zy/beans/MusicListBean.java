package com.zy.beans;

import java.util.ArrayList;
import java.util.List;

public class MusicListBean {
	private List<MusicBean> musicBeans = new ArrayList<MusicBean>();

	public List<MusicBean> getMusicBeans() {
		return musicBeans;
	}

	public void setMusicBeans(List<MusicBean> musicBeans) {
		this.musicBeans = musicBeans;
	}
	public void addMusicBean(MusicBean musicBean)
	{
		if(!musicBeans.contains(musicBean))
		{
			musicBeans.add(musicBean);
		}
	}
	public void removeMusicBean(MusicBean musicBean) {
		if(musicBeans.contains(musicBean)) {
			musicBeans.remove(musicBean);
		}
	}
}