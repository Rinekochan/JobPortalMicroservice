package com.hoang.jobapplication.exception;

public class FeignConnectionFailure extends RuntimeException {
    public FeignConnectionFailure(String message) {
        super(message);
    }
}
