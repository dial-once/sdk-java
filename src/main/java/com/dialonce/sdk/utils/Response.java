package com.dialonce.sdk.utils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dusan Pesic on 23-Jun-16.
 *
 */
public class Response {

    public static class Error {

        @SerializedName("message")
        private String message;
        @SerializedName("status")
        private int status;

        public Error() {}

        public String getMessage() {
            return message;
        }

        public int getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return "Error{" +
                    "message='" + message + '\'' +
                    ", status=" + status +
                    '}';
        }
    }

    public static class Log {

        @SerializedName("success")
        private boolean success;

        public Log() {}

        public boolean isSuccess() {
            return success;
        }

        @Override
        public String toString() {
            return "Log{" +
                    "success=" + success +
                    '}';
        }
    }

    public static class IsEligible {

        @SerializedName("eligible")
        private boolean eligible;

        public IsEligible() {}

        public boolean isEligible() {
            return eligible;
        }

        @Override
        public String toString() {
            return "Eligible{" +
                    "eligible=" + eligible +
                    '}';
        }
    }

    public static class IsMobile {

        @SerializedName("mobile")
        private boolean mobile;

        public IsMobile() {}

        public boolean isMobile() {
            return mobile;
        }

        @Override
        public String toString() {
            return "IsMobile{" +
                    "mobile=" + mobile +
                    '}';
        }
    }

    public static class ServiceRequest {

        @SerializedName("success")
        private boolean success;

        public ServiceRequest( ) {}

        public boolean isSuccess() {
            return success;
        }

        @Override
        public String toString() {
            return "ServiceRequest{" +
                    "success=" + success +
                    '}';
        }
    }
}
