package ru.clevertect.testing.testshomework.exception;

import java.text.MessageFormat;
import java.util.UUID;

public class NoSuchUserException extends RuntimeException {

    private NoSuchUserException(String message) {
        super(message);
    }

    public static NoSuchUserException byId(UUID id) {
        return new NoSuchUserException(MessageFormat.format("User with id {0} not found", id));
    }
}
