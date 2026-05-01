package br.com.videira.dynamis.kingdomstructure.exception;

public class UserEmailAlreadyExistsException extends RuntimeException {

    public UserEmailAlreadyExistsException() {
        super("User e-mail already exists");
    }

    public UserEmailAlreadyExistsException(String message) {
        super(message);
    }

    public UserEmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}