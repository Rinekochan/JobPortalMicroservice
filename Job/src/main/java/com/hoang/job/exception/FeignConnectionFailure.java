package com.hoang.job.exception;

public class FeignConnectionFailure extends RuntimeException {
    public FeignConnectionFailure(String message) {
        super(message);
    }
}
