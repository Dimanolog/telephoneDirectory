package com.epam.telephonedirectory.exceptions;

public class BusinesException extends RuntimeException {
    public BusinesException(String message, Throwable cause) {
        super(message, cause);
    }
}
