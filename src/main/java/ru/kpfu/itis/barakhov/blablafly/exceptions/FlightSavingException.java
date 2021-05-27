package ru.kpfu.itis.barakhov.blablafly.exceptions;

public class FlightSavingException extends RuntimeException {
    public FlightSavingException(String message, Throwable ex) {
        super(message, ex);
    }
}
