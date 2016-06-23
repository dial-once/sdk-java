package com.dialonce.sdk.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Dusan Pesic on 23-Jun-16.
 *
 */
public class ConnectionTest {

    @Test
    public void testNoProtocol() {
        Connection connection = Connection
                .get("api.dialonce.io");
        assertEquals(-1, connection.getResponseCode());
    }

    @Test
    public void testUnknownHost() {
        Connection connection = Connection
                .get("http://qwerty");
        assertEquals(-1, connection.getResponseCode());
    }

    @Test
    public void testUnauthorized() {
        Connection connection = Connection
                .get("http://api.dialonce.io/unauthorized");
        assertEquals(401, connection.getResponseCode());
    }

    @Test
    public void testNotFound() {
        Connection connection = Connection
                .get("https://github.com/dial-once/gateway-http");
        assertEquals(404, connection.getResponseCode());
    }

    @Test
    public void testValidRequest() {
        Connection connection = Connection
                .get("http://www.google.com");
        assertEquals(200, connection.getResponseCode());
    }

    @Test
    public void testValidParams() {
        Connection connection = Connection
                .post("http://api.dialonce.io/oauth/token")
                .put("grant_type", "client_credentials")
                .put("client_id", "qpvao53b1x10z7u3906wvgzmvexuxwxj")
                .put("client_secret", "56g5jvhlciv9e0l4izccjqkf54okh21jbn4d4yj7");
        assertTrue(connection.isSuccess());
    }

    @Test
    public void testInvalidParams() {
        Connection connection = Connection
                .post("http://api.dialonce.io//oauth/token")
                .put("invalidParam", "value");
        assertFalse(connection.isSuccess());
    }
}
