package com.zou.tools;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
/*
 * 将形如"192.168.1.202:5000"的字符串转换为InetSocketAddress对象
 */
public class IpSwitch {
	public static InetSocketAddress getInetSocketAdress(String ipString) {
		return new InetSocketAddress(ipString.substring(0, ipString.indexOf(":")), stringToInt(ipString.substring(ipString.indexOf(":")+1)));
	}
	private static int stringToInt(String in) {
		        int i = 0;
		        int out = 0;
		        int temp=0;
		        int[] chengshu = new int[]{1,10,100,1000,10000};
		        for (i = 0; i < in.length();i++) {
		            temp=Integer.parseInt(in.substring(in.length() - i - 1, in.length() - i)) * chengshu[i];
		            out+=temp;
		        }
		        return out;
	}
    public static List<String> getNetworkAddress() {
        List<String> result = new ArrayList<String>();
        Enumeration<NetworkInterface> netInterfaces;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> addresses=ni.getInetAddresses();
                while(addresses.hasMoreElements()){
                    ip = addresses.nextElement();
                    if (!ip.isLoopbackAddress() && ip.getHostAddress().indexOf(':') == -1) {
                        result.add(ip.getHostAddress());
                    }
                }
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

}
