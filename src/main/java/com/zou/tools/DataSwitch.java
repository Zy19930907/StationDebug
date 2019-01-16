package com.zou.tools;

public class DataSwitch {
	
	public static byte[] StringToHexBytes(String in) {
		
		int len = in.length() >> 1;
		int i;
		byte[] out = new byte[len];
		byte temp;
		for(i=0;i<len;i++) {
			temp = (byte) String2Int(in.substring(2*i, 2*i+1));
			temp <<= 4;
			temp |= (byte)String2Int(in.substring(2*i+1,2*i+2));
			out[i] = temp;
		}
		
		return out;
		
	}
	public static String byteToDecimalString(byte in) {
			        int i,temp=0;
			        if(in<0)
			            i = in+256;
			        else
			            i = in;
			        temp += (i>>4)*16;
			        temp += i &0x0f;
			        return String.valueOf(temp);
	}

	public static int stringToInt(String in) {
		int i = 0;
		int out = 0;
		int temp = 0;
		int[] chengshu = new int[] { 1, 10, 100, 1000, 10000,100000,1000000 };
		for (i = 0; i < in.length(); i++) {
			temp = Integer.parseInt(in.substring(in.length() - i - 1, in.length() - i)) * chengshu[i];
			out += temp;
		}
		return out;
	}
	public static int String2Int(String s) {
		if(s.equals("0")) return 0x00;
		else if(s.equals("1")) return 0x01;
		else if(s.equals("2")) return 0x02;
		else if(s.equals("3")) return 0x03;
		else if(s.equals("4")) return 0x04;
		else if(s.equals("5")) return 0x05;
		else if(s.equals("6")) return 0x06;
		else if(s.equals("7")) return 0x07;
		else if(s.equals("8")) return 0x08;
		else if(s.equals("9")) return 0x09;
		else if(s.equals("A")) return 0x0A;
		else if(s.equals("B")) return 0x0B;
		else if(s.equals("C")) return 0x0C;
		else if(s.equals("D")) return 0x0D;
		else if(s.equals("E")) return 0x0E;
		else if(s.equals("F")) return 0x0F;
		else if(s.equals("a")) return 0x0A;
		else if(s.equals("b")) return 0x0B;
		else if(s.equals("c")) return 0x0C;
		else if(s.equals("d")) return 0x0D;
		else if(s.equals("e")) return 0x0E;
		else if(s.equals("f")) return 0x0F;
		else return 0x00;
	}
	public static byte stringToByte(String in) {
		    int i = 0;
		        byte out = 0;
		        int temp=0;
		        int[] chengshu = new int[]{1,10,100,1000,10000};
		        for (i = 0; i < in.length();i++) {
		            temp=Byte.parseByte(in.substring(in.length() - i - 1, in.length() - i)) * chengshu[i];
		            out+=temp;
		        }
		        return out;
	    }
	    
	public static String bytesToHexString(byte[] inBytes) {
		 
		   String outString = "";
		   int i=0,j=0;
		   for(i=0;i<inBytes.length;i++) {
			outString+=byteToHexString(inBytes[i]);
			outString+=" ";
			j++;
			if(j>=16) {
				outString+="\r\n";
				j = 0;
			}
		   }
		   return outString;
	    }
	    
	    public static String byteToHexString(byte inByte) {
		    String[] temp = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"}; 
		    int i;
		        if(inByte<0)
		            i = inByte+256;
		        else
		            i = inByte;
		        return (temp[i>>4]+temp[i&0x0f]);
	    }
	    
	    public static String getEmp(String preString) {
		    String out="";
		    int cnt = 10;
		    cnt = 10 - preString.length();
		    while((cnt--)>0) {
			    out+="  ";
		    }
		    return out;
	    }
	    public static String getEmp1(String preString) {
		    String out="";
		    int cnt = 10;
		    cnt = 10 - preString.length();
		    while((cnt--)>0) {
			    out+=" ";
		    }
		    return out;
	    }
	    
	    public static int abs(byte in) {
		    if(in<0)
			    return((int)(in+256));
		    return (int)in;
	    }
}
