package com.zy.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

import StationDebug.App;

public class UdpSender {
	MulticastSocket ms = null;
	DatagramPacket dataPacket = null;
	InetAddress address;
	DatagramSocket socket;

	public UdpSender() {
		try {
			ms = new MulticastSocket();
			ms.setTimeToLive(32);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new ListenUdpPack().start();
	}

	public void SendUdp(byte[] msg, String destIp) {
		try {
			address = InetAddress.getByName(destIp);
			// 将本机的IP（这里可以写动态获取的IP）地址放到数据包里，其实server端接收到数据包后也能获取到发包方的IP的
			dataPacket = new DatagramPacket(msg, msg.length, address, 5005);
			ms.send(dataPacket);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class ListenUdpPack extends Thread {
		byte[] msg = new byte[4096];
		byte[] data;

		@Override
		public void run() {
			try {
				socket = new DatagramSocket(ms.getLocalPort()+1);
				DatagramPacket packet = new DatagramPacket(msg, msg.length);
				while (true) {
					socket.receive(packet);
					data = null;
					data = new byte[packet.getLength()];
					System.arraycopy(msg, 0, data, 0, data.length);
					App.stationListView.UdpRecv(packet, data);
				}
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
