package com.company.cookiesManag;

import java.io.File;
import java.util.Map;

public class cookiesMa {
    private Map<String,String> cookies;

    public cookiesMa(){

    }
    public cookiesMa(Map<String,String> cookies){
        this.cookies = cookies;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public void saveCookies(){

    }

}
