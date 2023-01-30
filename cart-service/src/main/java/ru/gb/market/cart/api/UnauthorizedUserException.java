package ru.gb.market.cart.api;

public class UnauthorizedUserException extends RuntimeException{
    public UnauthorizedUserException(final String message) {
        super(message);
    }
}
