package com.hoang.employer.exception;

public class FeignConnectionFailure extends RuntimeException {
    public FeignConnectionFailure(String message) {
        super(message);
    }
}
