package ru.gb.market.user.api;

public class UnauthorizedUserException extends RuntimeException{
    public UnauthorizedUserException(final String message) {
        super(message);
    }
}
