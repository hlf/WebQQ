package com.ltype.webqq.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

public class ResponseUtils {
    public String getResultString(HttpUriRequest httpUriRequest) throws IOException {
        InputStream inputStream = getInputStream(httpUriRequest);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuilder stringBuilder = new StringBuilder("");
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
    public InputStream getInputStream(HttpUriRequest httpUriRequest) throws IOException {
        HttpResponse httpResponse = getResponse(httpUriRequest);
        if (httpResponse.getStatusLine().getStatusCode() != 200)
            throw new IOException("返回码不为200！");
        return httpResponse.getEntity().getContent();
    }
    public HttpResponse getResponse(HttpUriRequest httpUriRequest) throws IOException {
        return HttpClientFactory.getHttpClient().execute(httpUriRequest);
    }
}