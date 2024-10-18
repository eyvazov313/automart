package az.atl.auto_mart.exceptions;

public class TokenNotFoundException extends RuntimeException{

    public TokenNotFoundException() {
        super(ErrorMessage.TOKEN_NOT_FOUND.getMessage());
    }
}
