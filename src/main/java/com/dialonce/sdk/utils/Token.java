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

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("expire_at")
    private Date expireAt;

    private boolean isFromCredentials = false;

    public Token() {
        this.isFromCredentials = true;
    }

    public Token(String accessToken) {
        this.accessToken = accessToken;
        this.tokenType = "Bearer";
        this.isFromCredentials = false;
    }

    public static Token parse(String jsonString) {
        Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT).create();
        return gson.fromJson(jsonString, Token.class);
    }

    public String getAuthorization() {
        if (tokenType != null && accessToken != null) {
            return tokenType + " " + accessToken;
        }
        return null;
    }

    public boolean isValid() {
        return getAuthorization() != null &&
                (!isFromCredentials || !isTokenExpired());
    }

    private boolean isTokenExpired() {
        return expireAt == null || expireAt.getTime() < System.currentTimeMillis();
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

    public void setFromCredentials(boolean fromCredentials) {
        isFromCredentials = fromCredentials;
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
