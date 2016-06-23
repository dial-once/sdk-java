package com.dialonce.sdk.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Dusan Pesic on 23-Jun-16.
 *
 */
public class Token {

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

//    response: {"access_token":"e39531e4-6a9b-448b-b150-02679d4cc6a4","token_type":"Bearer","expire_at":"2017-06-18T09:34:03.526Z"}

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("expire_at")
    private Date expireAt;

    public Token() { }

    public static Token parse(String jsonString) {
        Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT).create();
        return gson.fromJson(jsonString, Token.class);
    }

    public String getAuthorization() {
        return tokenType + " " + accessToken;
    }

    public boolean isValid() {
        return expireAt != null &&
                expireAt.getTime() > System.currentTimeMillis();
    }

    public String getAccessToken() {
        return accessToken;
    }
    public String getTokenType() {
        return tokenType;
    }
    public Date getExpireAt() {
        return expireAt;
    }

    @Override
    public String toString() {
        return "Token{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expireAt=" + expireAt +
                '}';
    }
}
