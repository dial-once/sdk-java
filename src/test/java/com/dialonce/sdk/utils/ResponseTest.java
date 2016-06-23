package com.dialonce.sdk.utils;

import com.google.gson.Gson;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Dusan Pesic on 23-Jun-16.
 *
 */
public class ResponseTest {

    @Test
    public void testErrorResponseParser() {
        String json = "{\"message\":\"Error message\",\"status\":\"400\"}";
        Response.Error response = new Gson().fromJson(json, Response.Error.class);

        assertEquals(400, response.getStatus());
        assertEquals("Error message", response.getMessage());
    }

    @Test
    public void testLogResponseParser() {
        String json = "{\"success\":\"true\"}";
        Response.Log response = new Gson().fromJson(json, Response.Log.class);

        assertTrue(response.isSuccess());

        json = "{\"success\":\"false\"}";
        response = new Gson().fromJson(json, Response.Log.class);

        assertFalse(response.isSuccess());
    }

    @Test
    public void testIsEligibleResponseParser() {
        String json = "{\"eligible\":\"true\"}";
        Response.IsEligible response = new Gson().fromJson(json, Response.IsEligible.class);

        assertTrue(response.isEligible());

        json = "{\"eligible\":\"false\"}";
        response = new Gson().fromJson(json, Response.IsEligible.class);

        assertFalse(response.isEligible());
    }

    @Test
    public void testIsMobileResponseParser() {
        String json = "{\"mobile\":\"true\"}";
        Response.IsMobile response = new Gson().fromJson(json, Response.IsMobile.class);

        assertTrue(response.isMobile());

        json = "{\"mobile\":\"false\"}";
        response = new Gson().fromJson(json, Response.IsMobile.class);

        assertFalse(response.isMobile());
    }

    @Test
    public void testServiceRequestResponseParser() {
        String json = "{\"success\":\"true\"}";
        Response.ServiceRequest response = new Gson().fromJson(json, Response.ServiceRequest.class);

        assertTrue(response.isSuccess());

        json = "{\"success\":\"false\"}";
        response = new Gson().fromJson(json, Response.ServiceRequest.class);

        assertFalse(response.isSuccess());
    }


}
