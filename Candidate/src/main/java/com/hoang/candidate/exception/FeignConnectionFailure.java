package com.hoang.candidate.exception;

public class FeignConnectionFailure extends RuntimeException {
    public FeignConnectionFailure(String message) {
        super(message);
    }
}
