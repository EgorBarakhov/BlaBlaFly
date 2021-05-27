package ru.kpfu.itis.barakhov.blablafly.exceptions;

public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }
}
