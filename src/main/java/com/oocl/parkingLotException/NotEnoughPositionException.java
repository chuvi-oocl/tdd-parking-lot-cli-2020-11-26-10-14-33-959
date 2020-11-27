package com.oocl.parkingLotException;

public class NotEnoughPositionException extends Exception {
    public NotEnoughPositionException(String errorMessage) {
        super(errorMessage);
    }
}
