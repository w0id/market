package ru.gb.market.order.api;

public class UnauthorizedUserException extends RuntimeException{
    public UnauthorizedUserException(final String message) {
        super(message);
    }
}
