package net.sprinBackend.springbootBackend.security.exceptions;

public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
