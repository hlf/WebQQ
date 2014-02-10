package com.ltype.webqq.httpclient;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClient{
	private String getContent;
	private CloseableHttpClient httpClient;
	private BasicCookieStore cookieStore;
	private String ptWebqq;
	public void createCookieStore(){
		this.cookieStore = new BasicCookieStore();
        this.httpClient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();
	}
	public String connectByGet(String url){
        HttpGet httpGet = new HttpGet(url);
		try {
	        httpGet.addHeader("Referer", "https://ui.ptlogin2.qq.com/cgi-bin/login?daid=164&target=self&style=5&mibao_css=m_webqq&appid=1003903&enable_qlogin=0&no_verifyimg=1&s_url=http%3A%2F%2Fweb2.qq.com%2Floginproxy.html&f_url=loginerroralert&strong_login=1&login_state=10&t=20131202001");
            CloseableHttpResponse response = this.httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
            this.getContent = EntityUtils.toString(entity);
            /*
            System.out.println("Login form get: " + response.getStatusLine());
            

            System.out.println("Post logon cookies:");
            List<Cookie> cookies = cookieStore.getCookies();
            if (cookies.isEmpty()) {
                System.out.println("None");
            } else {
                for (int i = 0; i < cookies.size(); i++) {
                    System.out.println("- " + cookies.get(i).toString());
                }
            }
            */
            response.close();
			httpGet.abort();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			return getContent;
		}
	}
	public String connectByPost(String url){
        HttpPost httpPost = new HttpPost(url);
        System.out.println(url);
		try {
			//httpPost.addHeader("Referer", "https://ui.ptlogin2.qq.com/cgi-bin/login?daid=164&target=self&style=5&mibao_css=m_webqq&appid=1003903&enable_qlogin=0&no_verifyimg=1&s_url=http%3A%2F%2Fweb2.qq.com%2Floginproxy.html&f_url=loginerroralert&strong_login=1&login_state=10&t=20131202001");
			List<Cookie> cookies = cookieStore.getCookies();
            for(Cookie cookie:cookies){
                	if(cookie.getName().equals("ptwebqq")) {
                    this.ptWebqq = cookie.getValue();
            	}
            }
            System.out.println(ptWebqq);
            httpPost.addHeader("clientid", "48847830");
			httpPost.addHeader("psessionid", "null");
			httpPost.addHeader("r", "{\"status\":\"online\",\"ptwebqq\":\"" + this.ptWebqq + "\",\"passwd_sig\":\"\",\"clientid\":\"48847830\",\"psessionid\":null}");
			CloseableHttpResponse response = this.httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
            this.getContent = EntityUtils.toString(entity);
            response.close();
            httpPost.abort();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			return getContent;
		}
	}
}