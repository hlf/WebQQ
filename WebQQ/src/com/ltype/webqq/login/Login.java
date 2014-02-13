package com.ltype.webqq.login;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.util.Random;
import java.util.regex.Pattern;

import org.eclipse.swt.graphics.Image;

import com.ltype.webqq.httpclient.HttpClient;
import com.ltype.webqq.md5.Md5;

public class Login{
	private String User;
	private String Password;
	private String Captcha;
	public Login(String User, String Password, String Captcha){
		this.User = User;
		this.Password = Password;
		this.Captcha = Captcha;
	}
	public int isNeedCaptcha(){
		String checkUrl = "https://ssl.ptlogin2.qq.com/check?uin=" + User + "@qq.com&appid=1003903&js_ver=10067&js_type=0&login_sig=JayduBbXoeopw6o19cjwnxydIhwgJAFI1KY5mDSkg0UJuqNylkqZHznW3--LlmMc&u1=http%3A%2F%2Fweb2.qq.com%2Floginproxy.html&r=0" + getFixLenthString(17);
		HttpClient connect = new HttpClient();
		connect.createCookieStore();
		String checkContent = connect.connectByGet(checkUrl);
		System.out.println(checkContent);
		Pattern patternComma = Pattern.compile("[,']+");
        String[] returnContent = patternComma.split(checkContent);
		try {
	        if (returnContent[1].equals("0")) {
	        	//不需要验证码
				Md5 getV = new Md5();
				String V = getV.getMd5V(Password, returnContent[3], returnContent[2]);
		    	String noCaptchaUrl =  "https://ssl.ptlogin2.qq.com/login?u="+ User +"@qq.com&p="+ V +"&verifycode="+ returnContent[2] +"&webqq_type=10&remember_uin=1&login2qq=1&aid=1003903&u1=http%3A%2F%2Fweb2.qq.com%2Floginproxy.html%3Flogin2qq%3D1%26webqq_type%3D10&h=1&ptredirect=0&ptlang=2052&daid=164&from_ui=1&pttype=1&dumy=&fp=loginerroralert&action=2-25-14373&mibao_css=m_webqq&t=1&g=1&js_type=0&js_ver=10067&login_sig=CJIbUnaQHYZwClTcVFfHP-XWgvtc0nbyj1in7dOIdIB*ZMb9e7hhXKMeBLwpYXXX";
		    	String noCaptchaContent = connect.connectByGet(noCaptchaUrl);
		    	System.out.println(noCaptchaContent);
		        System.out.println(connect.connectByPost("http://d.web2.qq.com/channel/login2"));
	        } else {
	        	//需要验证码
	        	System.out.println("需要验证码");
		        if (Captcha != "") {
		        	//LOGIN
					System.out.println("LOGIN"+Captcha);
		        	Md5 getV = new Md5();
					String V = getV.getMd5V(Password, returnContent[3], Captcha);
			    	String captchaUrl =  "https://ssl.ptlogin2.qq.com/login?u="+ User +"@qq.com&p="+ V +"&verifycode="+ Captcha +"&webqq_type=10&remember_uin=1&login2qq=1&aid=1003903&u1=http%3A%2F%2Fweb2.qq.com%2Floginproxy.html%3Flogin2qq%3D1%26webqq_type%3D10&h=1&ptredirect=0&ptlang=2052&daid=164&from_ui=1&pttype=1&dumy=&fp=loginerroralert&action=2-25-14373&mibao_css=m_webqq&t=1&g=1&js_type=0&js_ver=10067&login_sig=CJIbUnaQHYZwClTcVFfHP-XWgvtc0nbyj1in7dOIdIB*ZMb9e7hhXKMeBLwpYXXX";
			    	String captchaContent = connect.connectByGet(captchaUrl);
					System.out.println(captchaContent);
		        }
	        	DownCaptchaImage downCaptcha = new DownCaptchaImage();
	        	return downCaptcha.downImage(User);
	        }
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return 0;
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