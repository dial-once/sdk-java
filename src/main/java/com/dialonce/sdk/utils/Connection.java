package com.dialonce.sdk.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Dusan Pesic on 23-Jun-16.
 *
 * Boilerplate code around HttpURLConnection to be used by IVR.
 */
public class Connection {

    private static final String CHARSET_UTF8 = "UTF-8";

    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String HEADER_ACCEPT_CHARSET = "Accept-Charset";
    private static final String HEADER_ACCEPT = "Accept";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";

    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";

    private String url;
    private Map<String, Object> params;
    private String authorization;
    private String method;

    private HttpURLConnection connection;

    private String response;

    public static Connection get(String url) {
        return new Connection(url, METHOD_GET);
    }

    public static Connection post(String url) {
        return new Connection(url, METHOD_POST);
    }

    private Connection(String url, String method) {
        this.url = url;
        this.method = method;

        this.params = new LinkedHashMap<String, Object>();
    }

    public Connection put(String key, Object value) {
        params.put(key, value);
        return this;
    }

    public Connection authorization(String value) {
        this.authorization = value;
        return this;
    }

    public boolean isSuccess() {
        return getResponseCode() == HttpURLConnection.HTTP_OK;
    }

    public int getResponseCode() {
        try {
            call();
            return connection.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getResponse() throws Exception {

        if (response == null) {

            InputStream responseStream =
                    isSuccess() ? connection.getInputStream() : connection.getErrorStream();

            BufferedReader input = new BufferedReader(new InputStreamReader(responseStream));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = input.readLine()) != null) {
                responseBuilder.append(line);
            }
            input.close();
            connection.disconnect();

            response = responseBuilder.toString();
        }

        return response;
    }

    private void call() throws Exception {

        if (connection == null) {

            connection = (HttpURLConnection) new URL(composeUrl()).openConnection();
            connection.setRequestProperty(HEADER_ACCEPT_CHARSET, CHARSET_UTF8);
            connection.setRequestProperty(HEADER_ACCEPT, CONTENT_TYPE_JSON);

            if (authorization != null && authorization.length() > 0) {
                connection.setRequestProperty(HEADER_AUTHORIZATION, authorization);
            }

            if (method.equals(METHOD_POST)) {
                connection.setDoOutput(true);
                connection.setRequestProperty(HEADER_CONTENT_TYPE, CONTENT_TYPE_FORM);
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(buildParams(CHARSET_UTF8).getBytes());
                outputStream.flush();
                outputStream.close();
            }
        }
    }

    private String composeUrl() throws Exception {
        if (method.equals(METHOD_GET)) {
            return url + "?" + buildParams(CHARSET_UTF8);
        } else if (method.equals(METHOD_POST)) {
            return url;
        } else {
            throw new Exception("Method '" + method + "' is not supported!");
        }
    }

    private String buildParams(String charset) throws UnsupportedEncodingException {
        StringBuilder data = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (data.length() != 0) data.append('&');
            data.append(URLEncoder.encode(param.getKey(), charset));
            data.append('=');
            data.append(URLEncoder.encode(String.valueOf(param.getValue()), charset));
        }
        return data.toString();
    }
}
