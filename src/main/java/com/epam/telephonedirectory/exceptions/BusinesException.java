package com.epam.telephonedirectory.exceptions;

public class BusinesException extends RuntimeException {
    public BusinesException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinesException(String message) {
        super(message);
    }
}
