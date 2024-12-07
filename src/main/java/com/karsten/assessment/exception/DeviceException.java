package com.karsten.assessment.exception;

public class DeviceException extends RuntimeException {
    public DeviceException(String messageFormat, Object... args) {
        super(String.format(messageFormat, args));
    }
}
