package com.dialonce.sdk.exceptions;

/**
 * Created by Dusan Pesic on 23-Jun-16.
 *
 */
public class InvalidTokenException extends Exception {

    public InvalidTokenException() {
        super("Token not valid!");
    }
}
