package az.atl.auto_mart.exceptions;

import static az.atl.auto_mart.exceptions.ErrorMessage.INVALID_TOKEN;

public class InvalidTokenException extends RuntimeException{

    public InvalidTokenException() {
        super(INVALID_TOKEN.getMessage());
    }
}
