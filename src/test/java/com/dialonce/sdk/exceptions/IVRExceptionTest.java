package com.dialonce.sdk.exceptions;

import com.dialonce.sdk.utils.Response;
import com.google.gson.Gson;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Dusan Pesic on 24-Jun-16.
 *
 */
public class IVRExceptionTest {

    @Test
    public void testExceptionMessage() {
        String json = "{\"message\":\"Error message\",\"status\":\"400\"}";
        Response.Error errorResponse = new Gson().fromJson(json, Response.Error.class);

        assertThat(new IVRException(errorResponse).getMessage(), is("400: Error message"));
    }
}
