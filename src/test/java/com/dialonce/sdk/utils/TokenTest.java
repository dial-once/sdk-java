package com.dialonce.sdk.utils;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Dusan Pesic on 23-Jun-16.
 *
 */
public class TokenTest {

    @Test
    public void testTokenValidJsonParsing() {
        String json = "{\"access_token\":\"e39531e4-6a9b-448b-b150-02679d4cc6a4\"," +
                "\"token_type\":\"Bearer\",\"expire_at\":\"2017-06-18T09:34:03.526Z\"}";
        Token token = Token.parse(json);

        assertEquals("e39531e4-6a9b-448b-b150-02679d4cc6a4", token.getAccessToken());
        assertEquals("Bearer", token.getTokenType());
        try {
            assertEquals(
                    new SimpleDateFormat(Token.DATE_FORMAT)
                            .parse("2017-06-18T09:34:03.526Z"), token.getExpireAt());
        } catch (ParseException ignore) { }
    }

    @Test
    public void testTokenInvalidJsonParsing() {
        String json = "{\"token\":\"e39531e4-6a9b-448b-b150-02679d4cc6a4\"," +
                "\"type\":\"Bearer\",\"expire\":\"2017-06-18T09:34:03.526Z\"}";
        Token token = Token.parse(json);

        assertNull(token.getAccessToken());
        assertNull(token.getTokenType());
        assertNull(token.getExpireAt());
        assertNull(token.getAuthorization());
        assertFalse(token.isValid());
    }

    @Test
    public void testGetAuthorization() {
        String json = "{\"access_token\":\"e39531e4-6a9b-448b-b150-02679d4cc6a4\"," +
                "\"token_type\":\"Bearer\",\"expire_at\":\"2017-06-18T09:34:03.526Z\"}";
        Token token = Token.parse(json);
        assertEquals("Bearer e39531e4-6a9b-448b-b150-02679d4cc6a4", token.getAuthorization());
        assertNotEquals("", token.getAuthorization());
    }

    @Test
    public void testTokenValid() {
        String json = "{\"access_token\":\"e39531e4-6a9b-448b-b150-02679d4cc6a4\"," +
                "\"token_type\":\"Bearer\",\"expire_at\":\"2017-06-18T09:34:03.526Z\"}";
        Token token = Token.parse(json);

        assertTrue(token.isValid());
    }

    @Test
    public void testTokenInvalid() {
        String json = "{\"access_token\":\"e39531e4-6a9b-448b-b150-02679d4cc6a4\"," +
                "\"token_type\":\"Bearer\",\"expire_at\":\"2015-06-18T09:34:03.526Z\"}";
        Token token = Token.parse(json);

        assertFalse(token.isValid());
    }
}
