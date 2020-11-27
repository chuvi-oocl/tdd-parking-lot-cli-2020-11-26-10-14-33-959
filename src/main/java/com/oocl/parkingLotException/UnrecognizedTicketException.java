package com.oocl.parkingLotException;

public class UnrecognizedTicketException extends Exception  {
    public UnrecognizedTicketException(String errorMessage) {
        super(errorMessage);
    }
}
