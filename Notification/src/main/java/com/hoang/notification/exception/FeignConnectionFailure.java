package com.hoang.notification.exception;

public class FeignConnectionFailure extends RuntimeException {
    public FeignConnectionFailure(String message) {
        super(message);
    }
}
