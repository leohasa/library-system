package com.ayd2.librarysystem.exception;

public class DuplicatedEntityException extends ServiceException {
    public DuplicatedEntityException() {

    }

    public DuplicatedEntityException(String message) {
        super(message);
    }
}
