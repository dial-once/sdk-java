package com.dialonce.sdk;

import com.dialonce.sdk.utils.Connection;
import com.dialonce.sdk.utils.Response;
import com.dialonce.sdk.utils.Token;
import com.google.gson.Gson;

import java.io.IOException;

/**
 * Created by Dusan Pesic on 23-Jun-16.
 *
 */
public class IVR {

    public class LogType {
        public static final String CALL_START = "call-start";
        public static final String CALL_END = "call-end";
        public static final String ANSWER_GET_SMS = "answer-get-sms";
        public static final String ANSWER_NO_SMS = "answer-no-sms";
    }

    private static final String SERVER = "http://api.dialonce.io";

    private static final String ENDPOINT_INIT = "/oauth/token";
    private static final String ENDPOINT_LOG = "/ivrlogs";
    private static final String ENDPOINT_IS_ELIGIBLE = "/ivr/isEligible";
    private static final String ENDPOINT_IS_MOBILE = "/phoneNumbers/isMobile";
    private static final String ENDPOINT_SERVICE_REQUEST = "/ivr/serviceRequest";

    private Application app;
    private String caller;
    private String called;

    IVR(Application app) {
        this.app = app;
    }

    public IVR(Application app, String caller, String called) {
        this.app = app;
        this.caller = caller;
        this.called = called;
    }

    /**
     * Create a token using api key and secret, using oauth2 protocol.
     * Cache the token for all later usages with the same api key / token.
     *
     * @throws IOException
     */
    public void init() throws Exception {

        Connection connection = Connection
                .post(SERVER + ENDPOINT_INIT)
                .put("grant_type", "client_credentials")
                .put("client_id", app.getApiKey())
                .put("client_secret", app.getApiSecret());

        if (connection.isSuccess()) {
            Token token = Token.parse(connection.getResponse());
            app.setToken(token);
        } else {

            // TODO throw error
        }
    }

    /**
     * TODO add method description
     *
     * @param type one of the {@link LogType} constants
     * @throws Exception
     */
    public boolean log(String type) throws Exception {

        // TODO check if token is valid

        Connection connection = Connection
                .post(SERVER + ENDPOINT_LOG)
                .authorization(app.getToken().getAuthorization())
                .put("type", "call-start")
                .put("called", called)
                .put("caller", caller);

        if (connection.isSuccess()) {
            return new Gson().fromJson(connection.getResponse(), Response.Log.class).isSuccess();
        } else {
            return false;
        }
    }

    /**
     * Method used to know if user must be proposed the service request message.
     */
    public boolean isEligible() throws Exception {

        // TODO check if token is valid

        Connection connection = Connection
                .get(SERVER + ENDPOINT_IS_ELIGIBLE)
                .authorization(app.getToken().getAuthorization())
                .put("caller", caller)
                .put("called", called);

        if (connection.isSuccess()) {
            return new Gson().fromJson(connection.getResponse(), Response.IsEligible.class)
                    .isEligible();
        } else {
            return false;
        }
    }

    /**
     *
     * Method used to know if user is mobile/not mobile. Not so useful for other things.
     * To know if user must be proposed a service interface, you better use isEligible.
     *
     *  @param cultureISO - If number is provided on international format, no need for cultureISO.
     */
    public boolean isMobile(String cultureISO) throws Exception {

        // TODO check if token is valid

        Connection connection = Connection
                .get(SERVER + ENDPOINT_IS_MOBILE)
                .authorization(app.getToken().getAuthorization())
                .put("number", caller);

        if (cultureISO != null && cultureISO.length() > 0) {
            connection.put("cultureISO", cultureISO);
        }

        if (connection.isSuccess()) {
            return new Gson().fromJson(connection.getResponse(), Response.IsMobile.class)
                    .isMobile();
        } else {
            return false;
        }
    }

    /**
     * This method is called when user requested an interface
     * (answered yes to the service proposal message)
     *
     */
    public boolean serviceRequest() throws Exception {

        // TODO check if token is valid

        Connection connection = Connection
                .post(SERVER + ENDPOINT_SERVICE_REQUEST)
                .authorization(app.getToken().getAuthorization())
                .put("type", "call-start")
                .put("caller", caller)
                .put("called", called);

        if(connection.isSuccess()) {
            return new Gson().fromJson(connection.getResponse(), Response.ServiceRequest.class)
                    .isSuccess();
        } else {
            return false;
        }
    }
}
