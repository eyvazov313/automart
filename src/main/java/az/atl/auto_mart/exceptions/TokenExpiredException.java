package az.atl.auto_mart.exceptions;

public class TokenExpiredException extends RuntimeException{

    public TokenExpiredException() {
        super(ErrorMessage.TOKEN_EXPIRED.getMessage());
    }
}
