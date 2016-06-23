package com.dialonce.sdk;

import com.dialonce.sdk.utils.Token;

/**
 * Created by Dusan Pesic on 23-Jun-16.
 *
 */
public class Application {

    private String apiKey;
    private String apiSecret;
    private Token token;

    public Application(String apiKey, String apiSecret) throws Exception {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;

        if (token == null) {
            new IVR(this).init();
        }
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Application{" +
                "apiKey='" + apiKey + '\'' +
                ", apiSecret='" + apiSecret + '\'' +
                ", token=" + token +
                '}';
    }
}
