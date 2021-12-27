package com.mid.base.io.network;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @Author jiangshaoyue
 * @Date 2019/6/26 14:09
 */
public class NetworkInterfaceTest {
    public static void main(String args[]) throws SocketException {
        Enumeration<NetworkInterface> networkInterface = NetworkInterface.getNetworkInterfaces();
        while (networkInterface.hasMoreElements()){
            NetworkInterface eachNetworkInterface = networkInterface.nextElement();
            System.out.println("获得网络设备名称  --  "+eachNetworkInterface.getName());
            System.out.println("获得网络设备显示名称  --  "+eachNetworkInterface.getDisplayName());
            System.out.println("获得网络接口的索引  --  "+eachNetworkInterface.getIndex());
            System.out.println("是否已经开启并运行  --  "+eachNetworkInterface.isUp());
            System.out.println("是否为回调接口  --  "+eachNetworkInterface.isLoopback());
            System.out.println("最大传输包大小  --  "+eachNetworkInterface.getMTU());
            System.out.println("网卡物理地址  --  "+eachNetworkInterface.getHardwareAddress());

            Enumeration<InetAddress> enumInetAddress = eachNetworkInterface.getInetAddresses();
            while(enumInetAddress.hasMoreElements()){
                InetAddress inetAddress = enumInetAddress.nextElement();
                System.out.println("获取此IP地址完全限定域名  --  "+inetAddress.getCanonicalHostName());
                System.out.println("获取此IP地址字符串  --  "+inetAddress.getHostAddress());
            }
            System.out.println("-----------------------------------------------");
        }
    }
}
