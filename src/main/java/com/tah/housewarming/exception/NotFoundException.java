package com.tah.housewarming.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String text) {
        super(text);
    }

    public NotFoundException(String entity, Integer id) {
        super(String.format("%s %d not found", entity, id));
    }
}
