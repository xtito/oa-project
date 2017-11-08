package com.oa.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.oa.core.LoggerUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * IP管理工具类，MAC地址获取
 * <p/>
 * Created by [张渊]
 * 2017/10/29 17:20
 */
public class IpUtil {

    private IpUtil() {
    }

    /**
     * 获取客户端IP地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    /**
     * 获取IP+[IP所属地址]
     */
    public static String getIpBelongAddress(HttpServletRequest request) {

        String ip = getIpAddress(request);
        String belongIp = getIpBelongAddress(ip);

        return ip + belongIp;
    }


    /**
     * 获取IP所属地址
     */
    public static String getIpBelongAddress(String ip) {

        String ipAddress = "[]";
        try {
            // 淘宝提供的服务地址
            String context = call("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
            JSONObject fromObject = JSONObject.parseObject(context);
            String code = fromObject.getString("code");
            if (code.equals("0")) {
                JSONObject jsonObject = fromObject.getJSONObject("data");
                ipAddress = "[" + jsonObject.get("country") + "/" + jsonObject.get("city") + "]";
            }
        } catch (Exception e) {

            LoggerUtil.error(IpUtil.class, "获取IP所属地址出错", e);
            e.printStackTrace();
        }
        return ipAddress;
    }


    /**
     * 获取Ip所属地址
     */
    public static String call(String urlStr) {

        try {

            URL url = new URL(urlStr);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

            httpCon.setConnectTimeout(3000);
            httpCon.setDoInput(true);
            httpCon.setRequestMethod("GET");

            int code = httpCon.getResponseCode();

            if (code == 200) {
                return streamConvertToSting(httpCon.getInputStream());
            }
        } catch (Exception e) {
            LoggerUtil.error(IpUtil.class, "获取IP所属地址出错", e);
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 描述：将InputStream转换成String
     */
    public static String streamConvertToSting(InputStream is) {

        String tempStr = "";
        try {

            if (is == null) return null;
            ByteArrayOutputStream arrayOut = new ByteArrayOutputStream();
            byte[] by = new byte[1024];
            int len = 0;
            while ((len = is.read(by)) != -1) {
                arrayOut.write(by, 0, len);
            }
            tempStr = new String(arrayOut.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tempStr;
    }


    /**
     * 获取网卡MAC地址
     */
    public static String getMacOnWindow() {
        try {
            String mac = null;
            Process process = Runtime.getRuntime().exec("ipconfig /all");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(process.getInputStream()));
            for (String line = buffer.readLine(); line != null; line = buffer.readLine()) {
                int index = line.indexOf("Physical Address");
                if (index <= 0) {
                    continue;
                }
                mac = line.substring(index + 36);
                break;
            }
            buffer.close();
            process.waitFor();
            return mac;
        } catch (Exception e) {
            LoggerUtil.error(IpUtil.class, "获取本机MAC地址失败！", e);
            return null;
        }
    }

    /**
     * 获取本机IP地址
     */
    public static String getLocalHostName() {

        try {
            InetAddress ia = InetAddress.getLocalHost();
            return ia.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取本机所有网卡IP地址
     * @return ip地址列表
     */
    public static List<String> getLocalIpList() {

        List<String> ipList = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();

            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();

                while (addresses.hasMoreElements()) {
                    InetAddress ip = addresses.nextElement();

                    if (ip != null && ip instanceof Inet4Address) {
                        ipList.add(ip.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        LoggerUtil.info(IpUtil.class, "本机IP地址：" + ipList.toString());
        return ipList;
    }


    /**
     * 获取本机MAC地址-未测试
     * @throws SocketException
     */
    public static String getLocalMac() throws SocketException, UnknownHostException {

        InetAddress ia = InetAddress.getLocalHost();

        //获取网卡，获取地址
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
//        System.out.println("mac数组长度：" + mac.length);

        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append("-");
            }

            //字节转换为整数
            int temp = mac[i] & 0xff;

            String str = Integer.toHexString(temp);
//            System.out.println("每8位:" + str);

            if (str.length() == 1) {
                sb.append("0").append(str);
            } else {
                sb.append(str);
            }
        }

//        System.out.println("本机MAC地址:" + sb.toString().toUpperCase());
        return sb.toString().toUpperCase();
    }

}
