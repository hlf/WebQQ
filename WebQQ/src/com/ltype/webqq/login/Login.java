package com.ltype.webqq.login;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Random;
import java.util.regex.Pattern;

import com.ltype.webqq.httpclient.HttpClient;

public class Login{
	private String User;
	private String Password;
	private String Captcha;
	private String V;
	private final String HEXSTRING = "0123456789ABCDEF";
	public Login(String User, String Password, String Captcha){
		this.User = User;
		this.Password = Password;
		this.Captcha = Captcha;
	}
	public void isNeedCaptcha(){
		String checkUrl = "https://ssl.ptlogin2.qq.com/check?uin=" + User + "@qq.com&appid=1003903&js_ver=10067&js_type=0&login_sig=JayduBbXoeopw6o19cjwnxydIhwgJAFI1KY5mDSkg0UJuqNylkqZHznW3--LlmMc&u1=http%3A%2F%2Fweb2.qq.com%2Floginproxy.html&r=0" + getFixLenthString(17);
		HttpClient connect = new HttpClient();
		connect.createCookieStore();
		String checkContent = connect.getResponseContent(checkUrl);
		System.out.println(checkContent);
		Pattern patternIsCheck = Pattern.compile("[,']+");
        String[] returnContent = patternIsCheck.split(checkContent);
        if (returnContent[1].equals("0")) {
        	//不需要验证码
			try {
	        	String P = hexchar2bin(md5(Password));
	    		String U = md5(P + hexchar2bin(returnContent[3].replace("\\x", "").toUpperCase()));
	    		V = md5(U + returnContent[2].toUpperCase());
	    		String noCaptchaUrl =  "https://ssl.ptlogin2.qq.com/login?u="+ User +"@qq.com&p="+ V +"&verifycode="+ returnContent[2] +"&webqq_type=10&remember_uin=1&login2qq=1&aid=1003903&u1=http%3A%2F%2Fweb2.qq.com%2Floginproxy.html%3Flogin2qq%3D1%26webqq_type%3D10&h=1&ptredirect=0&ptlang=2052&daid=164&from_ui=1&pttype=1&dumy=&fp=loginerroralert&action=2-25-14373&mibao_css=m_webqq&t=1&g=1&js_type=0&js_ver=10067&login_sig=CJIbUnaQHYZwClTcVFfHP-XWgvtc0nbyj1in7dOIdIB*ZMb9e7hhXKMeBLwpYXXX";
	    		String noCaptchaContent = connect.getResponseContent(noCaptchaUrl);
	    		System.out.println(noCaptchaContent);
			} catch (UnsupportedEncodingException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
        } else {
        	//需要验证码
        	System.out.println(returnContent[1]);
        }
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
	private String md5(String originalText) {
        String result;
        try {
            byte buf[] = originalText.getBytes("ISO-8859-1");
            StringBuilder hexString = new StringBuilder();
            String digit;


            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(buf);

            byte[] digest = algorithm.digest();

            for (byte aDigest : digest) {
                digit = Integer.toHexString(0xFF & aDigest);

                if (digit.length() == 1) {
                    digit = "0" + digit;
                }

                hexString.append(digit);
            }

            result = hexString.toString();
        } catch (Exception ex) {
            result = "";
        }

        return result.toUpperCase();
    }
	private String hexchar2bin(String md5str)
            throws UnsupportedEncodingException 
    {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream(
	                    md5str.length() / 2);
	    for (int i = 0; i < md5str.length(); i = i + 2)
	    {
	            baos.write((HEXSTRING.indexOf(md5str.charAt(i)) << 4 | HEXSTRING
	                            .indexOf(md5str.charAt(i + 1))));
	    }
	    return new String(baos.toByteArray(), "ISO-8859-1");
	}
}