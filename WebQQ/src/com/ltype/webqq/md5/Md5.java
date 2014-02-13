package com.ltype.webqq.md5;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import com.ltype.webqq.exception.WebQQException;

public class Md5{
	private final String HEXSTRING = "0123456789ABCDEF";
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
	private String hexchar2bin(String md5str) throws WebQQException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(
                md5str.length() / 2);

        for (int i = 0; i < md5str.length(); i = i + 2) {
            String hexstring = "0123456789ABCDEF";
            baos.write((hexstring.indexOf(md5str.charAt(i)) << 4 | hexstring
                    .indexOf(md5str.charAt(i + 1))));
        }
        try {
            return new String(baos.toByteArray(), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        throw new WebQQException();
    }
	public String getMd5V(String Password, String HexUser, String Captcha) throws WebQQException{
		try {
	    	String P = hexchar2bin(md5(Password));
			String U = md5(P + hexchar2bin(HexUser.replace("\\x", "").toUpperCase()));
			System.out.println(HexUser.replace("\\x", "").toUpperCase());
			System.out.println(hexchar2bin(HexUser.replace("\\x", "").toUpperCase()).toCharArray());
			return md5(U + Captcha.toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
        throw new WebQQException();
	}
}