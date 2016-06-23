package com.dialonce.sdk.exceptions;

import com.dialonce.sdk.utils.Response;

/**
 * Created by Dusan Pesic on 23-Jun-16.
 *
 */
public class IVRException extends Exception {

    public IVRException(Response.Error responseError) {
        super(String.format("%d: %s", responseError.getStatus(), responseError.getMessage()));
    }
}
