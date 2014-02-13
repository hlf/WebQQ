package com.ltype.webqq.httpclient.cookie;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;

public class CookieStoreFactory {
    private static CookieStore cookieStore;

    public static CookieStore getCookieStore() {
        if (cookieStore == null) {
            synchronized (CookieStoreFactory.class) {
                if (cookieStore == null) {
                    cookieStore = new BasicCookieStore();
                }
            }
        }
        return cookieStore;
    }
}