package com.ltype.webqq.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClient{
	private String url;
	public HttpClient(String url){
		this.url = url;
	}
	public String httpClient(){
		BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();
        HttpGet httpGet = new HttpGet(url);
		try {
			CloseableHttpResponse responseCheck = httpClient.execute(httpGet);
			HttpEntity entity = responseCheck.getEntity();
            String getContent = EntityUtils.toString(entity);
            return getContent;
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return url;
	}
}