package ru.kpfu.itis.barakhov.blablafly.exceptions;

public class AircraftSavingException extends RuntimeException {
    public AircraftSavingException(String message, Throwable ex) {
        super(message, ex);
    }
}
