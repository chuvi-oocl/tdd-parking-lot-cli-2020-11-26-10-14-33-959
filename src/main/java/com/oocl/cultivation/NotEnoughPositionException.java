package com.oocl.cultivation;

public class NotEnoughPositionException extends Exception {
    public NotEnoughPositionException(String errorMessage) {
        super(errorMessage);
    }
}
