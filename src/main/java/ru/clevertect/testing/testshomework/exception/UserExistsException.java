package ru.clevertect.testing.testshomework.exception;

public class UserExistsException extends RuntimeException {

    public UserExistsException(String message) {
        super(message);
    }

    public UserExistsException() {
        super();
    }
}
