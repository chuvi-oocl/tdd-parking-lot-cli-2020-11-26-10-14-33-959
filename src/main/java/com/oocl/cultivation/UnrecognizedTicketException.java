package com.oocl.cultivation;

public class UnrecognizedTicketException extends Exception  {
    public UnrecognizedTicketException(String errorMessage) {
        super(errorMessage);
    }
}
