package com.dialonce.sdk.exceptions;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Dusan Pesic on 23-Jun-16.
 *
 */
public class InvalidTokenExceptionTest {

    @Test
    public void testExceptionMessage() {
        assertThat(new InvalidTokenException().getMessage(), is("Token not valid!"));
    }
}
