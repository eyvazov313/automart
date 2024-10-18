package az.atl.auto_mart.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super(ErrorMessage.USER_NOT_FOUND.getMessage());
    }
}
