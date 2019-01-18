package com.zou.tools;

/**
 * Created by 123 on 2018/1/9.
 */

public class CmdMaker {
	private byte[] cmd = new byte[2048];
	private CrcMaker crcMaker = new CrcMaker();
	private int i = 0;
	private final byte getCurInfo = 0x60;
	private final byte getAppVer = 0x72;
	private final byte getMacAdd = (byte) 0x88;
	private final byte listenBoardCast = 0x32;
	private final byte gatewayDebug = (byte) 0xEC;
	private final byte setVoicePort = (byte) 0x69;
	private final byte setVoice = 0x31;
	private final byte getBoardCastInfo = 0x11;
	private final byte setMusicStart = 0x33;
	private final byte getPkPower = (byte) 0xED;

	public CmdMaker() {
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
	}

	public byte[] getInitCmd(long segH, long segL) {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		int j;
		i = 9;
		cmd[i++] = (byte) 0x85;
		cmd[i++] = 0x23;
		cmd[i++] = 0x00;

		for (j = 0; j < 32; j++) {
			if (j < 8)
				cmd[i++] = (byte) (segL >> (8 * j));
			else if (j < 16)
				cmd[i++] = (byte) (segH >> (8 * (j - 8)));
			else
				cmd[i++] = 0;
		}
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] getBoardCastListenCmd(byte boardCastAddr, byte time) {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = listenBoardCast;
		cmd[i++] = 0x06;
		cmd[i++] = 0x00;
		cmd[i++] = boardCastAddr;
		cmd[i++] = 0x01;
		cmd[i++] = time;
		cmd[i++] = 0x00;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] getGatewayDebufCmd(byte debugCmd) {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = gatewayDebug;
		cmd[i++] = 0x02;
		cmd[i++] = 0x00;
		cmd[i++] = debugCmd;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] getSetVoicePortCmd() {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = setVoicePort;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] getDisBoardCastListenCmd(byte boardCastAddr) {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = listenBoardCast;
		cmd[i++] = 0x06;
		cmd[i++] = 0x00;
		cmd[i++] = boardCastAddr;
		cmd[i++] = 0x02;
		cmd[i++] = 0x1E;
		cmd[i++] = 0x00;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] getCurInfoCmd() {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = getCurInfo;
		cmd[i++] = 0x03;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] getAppVerCmd() {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = getAppVer;
		cmd[i++] = 0x03;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] getMacCmd() {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = getMacAdd;
		cmd[i++] = 0x03;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] getPkPowerCmd(byte powerAddr) {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = getPkPower;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = powerAddr;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] getSenserInfoCmd(byte senserAddr) {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = 0x11;
		cmd[i++] = 0x04;
		cmd[i++] = 0x00;
		cmd[i++] = senserAddr;
		cmd[i++] = 0;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] getUpWornCmd(byte senserAddr) {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = 0x14;
		cmd[i++] = 0x04;
		cmd[i++] = 0x00;
		cmd[i++] = senserAddr;
		cmd[i++] = 0;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] getDownWornCmd(byte senserAddr) {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = 0x15;
		cmd[i++] = 0x04;
		cmd[i++] = 0x00;
		cmd[i++] = senserAddr;
		cmd[i++] = 0;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] getGuanlianAddrCmd(byte senserAddr) {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = 0x19;
		cmd[i++] = 0x04;
		cmd[i++] = 0x00;
		cmd[i++] = senserAddr;
		cmd[i++] = 0;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] getGuanlianTypeCmd(byte senserAddr) {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = 0x1C;
		cmd[i++] = 0x04;
		cmd[i++] = 0x00;
		cmd[i++] = senserAddr;
		cmd[i++] = 0;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] getBoardCastInfo(byte boardCastAddr) {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = getBoardCastInfo;
		cmd[i++] = 0x04;
		cmd[i++] = 0x00;
		cmd[i++] = boardCastAddr;
		cmd[i++] = 0x00;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] getMusicStartCmd(byte boardCastAddr) {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = setMusicStart;
		cmd[i++] = 0x04;
		cmd[i++] = 0x00;
		cmd[i++] = boardCastAddr;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] getMusicEndCmd(byte boardCastAddr) {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = setMusicStart;
		cmd[i++] = 0x04;
		cmd[i++] = 0x00;
		cmd[i++] = boardCastAddr;
		cmd[i++] = 0x02;
		cmd[i++] = 0x00;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] GatewayDebugCmd() {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = (byte) 0xEF;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] GetIPDInfoCmd() {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = (byte) 0xEF;
		cmd[i++] = 3;
		cmd[i++] = 0;
		cmd[i++] = 1;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] GetIPDRstCmd() {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = 0x55;
		cmd[i++] = 12;
		cmd[i++] = 0;
		cmd[i++] = 1;
		cmd[i++] = 4;
		cmd[i++] = (byte) 0xFF;
		cmd[i++] = (byte) 0xFF;
		cmd[i++] = 0;
		cmd[i++] = 0;
		cmd[i++] = 0;
		cmd[i++] = 0;
		cmd[i++] = 0;
		cmd[i++] = 0;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] GetIPDOpenCmd() {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = 0x55;
		cmd[i++] = 12;
		cmd[i++] = 0;
		cmd[i++] = 1;
		cmd[i++] = 4;
		cmd[i++] = 0;
		cmd[i++] = 0;
		cmd[i++] = (byte) 0xFF;
		cmd[i++] = (byte) 0xFF;
		cmd[i++] = 0;
		cmd[i++] = 0;
		cmd[i++] = 0;
		cmd[i++] = 0;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] GetIPDCloseCmd() {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = 0x55;
		cmd[i++] = 12;
		cmd[i++] = 0;
		cmd[i++] = 1;
		cmd[i++] = 4;
		cmd[i++] = 0;
		cmd[i++] = 0;
		cmd[i++] = 0;
		cmd[i++] = 0;
		cmd[i++] = (byte) 0xFF;
		cmd[i++] = (byte) 0xFF;
		cmd[i++] = 0;
		cmd[i++] = 0;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] GetIPDSet1Cmd(int LightningCurrent, int SignalCurrent, int LightningBreak, int SignalBreak) {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = 0x55;
		cmd[i++] = 12;
		cmd[i++] = 0;
		cmd[i++] = 1;
		cmd[i++] = 1;
		cmd[i++] = (byte) (LightningCurrent >> 8);
		cmd[i++] = (byte) (LightningCurrent & 0x000000FF);
		cmd[i++] = (byte) (SignalCurrent >> 8);
		cmd[i++] = (byte) (SignalCurrent & 0x000000FF);
		cmd[i++] = (byte) (LightningBreak >> 8);
		cmd[i++] = (byte) (LightningBreak & 0x000000FF);
		cmd[i++] = (byte) (SignalBreak >> 8);
		cmd[i++] = (byte) (SignalBreak & 0x000000FF);
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] GetIPDSet2Cmd(int UnderVolValue, int UnderVolDelay, int OverVolValue, int OverVolDelay) {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = 0x55;
		cmd[i++] = 12;
		cmd[i++] = 0;
		cmd[i++] = 1;
		cmd[i++] = 2;
		cmd[i++] = (byte) (UnderVolValue >> 8);
		cmd[i++] = (byte) (UnderVolValue & 0x000000FF);
		cmd[i++] = (byte) (UnderVolDelay >> 8);
		cmd[i++] = (byte) (UnderVolDelay & 0x000000FF);
		cmd[i++] = (byte) (OverVolValue >> 8);
		cmd[i++] = (byte) (OverVolValue & 0x000000FF);
		cmd[i++] = (byte) (OverVolDelay >> 8);
		cmd[i++] = (byte) (OverVolDelay & 0x000000FF);
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}

	public byte[] GetIPDSet3Cmd(int LeakageResistance, int LeakageDelay, int CH4Delay, int DevAddr) {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = 0x55;
		cmd[i++] = 12;
		cmd[i++] = 0;
		cmd[i++] = 1;
		cmd[i++] = 3;
		cmd[i++] = (byte) (LeakageResistance >> 8);
		cmd[i++] = (byte) (LeakageResistance & 0x000000FF);
		cmd[i++] = (byte) (LeakageDelay >> 8);
		cmd[i++] = (byte) (LeakageDelay & 0x000000FF);
		cmd[i++] = (byte) (CH4Delay >> 8);
		cmd[i++] = (byte) (CH4Delay & 0x000000FF);
		cmd[i++] = (byte) (DevAddr >> 8);
		cmd[i++] = (byte) (DevAddr & 0x000000FF);
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}
	

	public boolean crcCheck(byte[] buf, int len) {
		return crcMaker.CRC16Check(buf, len);
	}
	
	public byte[] getHandCtrCmd(byte addr,byte ctr,byte duandian) {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x12;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = 0x40;
		cmd[i++] = 6;
		cmd[i++] = 0;
		cmd[i++] = addr;
		cmd[i++] = ctr;
		cmd[i++] = duandian;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}
	
	public byte[] getBoardCastGroupConfigCmd(long[][] config) {
		byte[] out = null;
		long configL,configH;
		i = 0;
		cmd[i++] = 0x55;
		cmd[i++] = (byte) 0xAA;
		cmd[i++] = 0x55;
		cmd[i++] = (byte) 0xAA;
		cmd[i++] = 0;
		cmd[i++] = 0;
		cmd[i++] = 0x07;
		cmd[i++] = (byte) 128;
		cmd[i++] = 0;
		for(int j=0;j<8;j++) {
			configL = config[j][0];
			configH = config[j][1];
			for(int k=0;k<8;k++) {
				cmd[i+(2*j)*8+k] = (byte) ((configL >> (k*8)) & 0xFF);
				cmd[i+(2*j+1)*8+k] = (byte) ((configH >> (k*8)) & 0xFF);	
			}
		}
		i+=130;
		cmd[4] = (byte) (i & 0xFF);
		cmd[5] = (byte) ((i>>8) & 0xFF);
		crcMaker.setCrc(cmd, i);
		out = new byte[i];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}
	
	public byte[] getSinglePlayCmd(byte boardCastAddr, int index) {
		byte[] out = null;
		i = 0;
		cmd[i++] = 0x55;
		cmd[i++] = (byte) 0xAA;
		cmd[i++] = 0x55;
		cmd[i++] = (byte) 0xAA;
		cmd[i++] = 0;
		cmd[i++] = 0;
		cmd[i++] = 0x03;
		cmd[i++] = 3;
		cmd[i++] = 0;
		cmd[i++] = boardCastAddr;
		cmd[i++] = (byte) (index & 0xFF);
		cmd[i++] = (byte) ((index>>8) & 0xFF);
		i+=2;
		cmd[4] = (byte) (i & 0xFF);
		cmd[5] = (byte) ((i>>8) & 0xFF);
		crcMaker.setCrc(cmd, i);
		out = new byte[i];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}
	
	public byte[] getSetVolumCmd(byte boardCastAddr, byte volum) {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = setVoice;
		cmd[i++] = 0x05;
		cmd[i++] = 0x00;
		cmd[i++] = boardCastAddr;
		cmd[i++] = volum;
		cmd[i++] = 0x00;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}
	
	public byte[] getExcuteInfoCmd() {
		byte[] out = null;
		for (i = 0; i < 4; i++)
			cmd[i] = (byte) 0xfe;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[i++] = 0x01;
		i = 9;
		cmd[i++] = (byte) 0xEA;
		cmd[i++] = 0x00;
		cmd[i++] = 0x00;
		cmd[6] = (byte) (i + 2);
		cmd[7] = 0;
		crcMaker.setCrc(cmd, i + 2);
		out = new byte[i + 2];
		System.arraycopy(cmd, 0, out, 0, out.length);
		return out;
	}
}
