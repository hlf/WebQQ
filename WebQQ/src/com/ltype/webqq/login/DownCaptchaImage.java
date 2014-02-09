package com.ltype.webqq.login;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import org.eclipse.swt.graphics.Image;

public class DownCaptchaImage{
	public int downImage(String User){
		String urlGetImage = "https://ssl.captcha.qq.com/getimage?aid=1003903&r=0" + getFixLenthString(17) + "&uin=" + User + "@qq.com";
		int bytesum = 0;
    	int byteread = 0;
    	try{
        	URL url = new URL(urlGetImage);
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            FileOutputStream fs = new FileOutputStream("code.jpg");
            byte[] buffer = new byte[1204];
            while ((byteread = inStream.read(buffer)) != -1) {
            bytesum += byteread;
            	fs.write(buffer, 0, byteread);
            }
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
        return 1;
	}

	/* 
	 * 返回长度为【strLength】的随机数，在前面补0 
	 */  
	private static String getFixLenthString(int strLength) {
	    Random rm = new Random();
	    // 获得随机数  
	    double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);
	    // 将获得的获得随机数转化为字符串  
	    String fixLenthString = String.valueOf(pross); 
	    // 返回固定的长度的随机数  
	    return fixLenthString.substring(1, strLength + 1);  
	} 
}