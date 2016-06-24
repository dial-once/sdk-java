package com.dialonce.sdk;

import com.dialonce.sdk.exceptions.IVRException;
import com.dialonce.sdk.utils.Token;

import java.io.IOException;

/**
 * Created by Dusan Pesic on 23-Jun-16.
 *
 */
public class Application {

    private String apiKey;
    private String apiSecret;
    private Token token;

    public Application(String apiKey, String apiSecret) throws IOException, IVRException {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;

        this.token = new IVR(this).init();
    }

    public Application(String accessToken) {
        this.token = new Token(accessToken);
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

    @Override
    public String toString() {
        return "Application{" +
                "apiKey='" + apiKey + '\'' +
                ", apiSecret='" + apiSecret + '\'' +
                ", token=" + token +
                '}';
    }
}
