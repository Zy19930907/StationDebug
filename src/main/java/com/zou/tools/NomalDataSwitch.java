package com.zou.tools;


public class NomalDataSwitch {

	public static String byteToDecimalString(byte in) {
		int i, temp = 0;
		if (in < 0)
			i = in + 256;
		else
			i = in;
		temp += (i >> 4) * 16;
		temp += i & 0x0f;
		return String.valueOf(temp);
	}

	public static int stringToInt(String in) {
		int i = 0;
		int out = 0;
		int temp = 0;
		int[] chengshu = new int[] { 1, 10, 100, 1000, 10000 };
		for (i = 0; i < in.length(); i++) {
			temp = Integer.parseInt(in.substring(in.length() - i - 1, in.length() - i)) * chengshu[i];
			out += temp;
		}
		return out;
	}

	public static byte stringToByte(String in) {
		int i = 0;
		byte out = 0;
		int temp = 0;
		int[] chengshu = new int[] { 1, 10, 100, 1000, 10000 };
		for (i = 0; i < in.length(); i++) {
			temp = Byte.parseByte(in.substring(in.length() - i - 1, in.length() - i)) * chengshu[i];
			out += temp;
		}
		return out;
	}

	public static String bytesToHexString(byte[] inBytes) {

		String outString = "";
		int i = 0, j = 0;
		for (i = 0; i < inBytes.length; i++) {
			outString += byteToHexString(inBytes[i]);
			outString += " ";
			j++;
			if (j >= 64) {
				outString += "\r\n";
				j = 0;
			}
		}
		return outString;
	}

	public static String byteToHexString(byte inByte) {
		String[] temp = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
		int i;
		if (inByte < 0)
			i = inByte + 256;
		else
			i = inByte;
		return (temp[i >> 4] + temp[i & 0x0f]);
	}

	public static String getEmp(String preString) {
		String out = "";
		int cnt = 10;
		cnt = 10 - preString.length();
		while ((cnt--) > 0) {
			out += "  ";
		}
		return out;
	}

	public static String getEmp1(String preString) {
		String out = "";
		int cnt = 10;
		cnt = 10 - preString.length();
		while ((cnt--) > 0) {
			out += " ";
		}
		return out;
	}

	public static String getEmp2(String preString) {
		String out = "";
		int cnt = 16;
		cnt = 16 - preString.length();
		while ((cnt--) > 0) {
			out += " ";
		}
		return out;
	}

	public static String getEmp3(String preString) {
		String out = "";
		int cnt = 60;
		if(preString.length()<60)
			cnt = 60 - preString.length();
		while ((cnt--) > 0) {
			out += " ";
		}
		return out;
	}
	
	public static String getEmp4(String preString) {
		String out = "",temp="";
		int cnt = 8;
		if(preString.length()< 8)
			cnt = 8 - preString.length();
		if(preString.length() < 5)
			temp = "-";
		else 
			temp ="--";
		while ((cnt--) > 0) {
			out += temp;
		}
		return out;
	}
	public static int abs(byte in) {
		if (in < 0)
			return ((int) (in + 256));
		return (int) in;
	}

	public static String bytesToDouble(byte byteL, byte byteH) {
		double out = 0;
		int i, j = 1;
		String fuhao = "";
		if ((byteL != (byte) 0xFF) && (byteH != (byte) 0xFF)) {
			i = abs(byteL) + (abs(byteH) * 256);
			switch ((i >> 13) & 0x00000003) {
			case 0x00:
				j = 1;
				break;
			case 0x01:
				j = 10;
				break;
			case 0x02:
				j = 100;
				break;
			case 0x03:
				j = 1000;
				break;
			}
			if ((i >> 15) == 1)
				fuhao = "-";
			out = ((double) (i & 0x0FFF)) / j;
			return fuhao+String.valueOf(out);
		} else
			return "----------";
	}
	
	public static int getValue(byte valueH, byte valueL) {
		return (NomalDataSwitch.abs(valueH) << 8) | NomalDataSwitch.abs(valueL);
	}
	
	public static String Rs485DataToString(byte[] data)
	{
		String out = "";
		String temp = "";
		int CanId;
		CanId = (abs(data[0]) << 24)+(abs(data[1]) << 16)+(abs(data[2]) << 8)+abs(data[3]);
		if(CanId == 0x55AA55AA)
			temp = "AB�?�?";
		else 
			temp = "帧头错误";
		temp += getEmp4(temp);
		out+= temp;
		
		if((CanId == 0x55AA55AA)&&( data[5] == 0x01))
			temp = "网关下发";
		else if (CanId == 0x55AA55AA &&( data[5] == 0x10))
			temp = "广播应答";
		
		temp += getEmp4(temp);
		out+= temp;
		
		temp = "广播地址�?"+String.valueOf(NomalDataSwitch.abs(data[4]));
		out+=temp;
		return out;
	}
	
}
