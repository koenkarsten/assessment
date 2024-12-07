package com.karsten.assessment.exception;

public class DetectionException extends RuntimeException {
    public DetectionException(String messageFormat, Object... args) {
        super(String.format(messageFormat, args));
    }
}
