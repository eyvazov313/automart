package az.atl.auto_mart.exceptions;

public class InvalidAuthenticationCredentialsException extends RuntimeException{

    public InvalidAuthenticationCredentialsException() {
        super(ErrorMessage.INVALID_AUTHENTICATION_CREDENTIALS.getMessage());
    }
}
