package az.atl.auto_mart.exceptions;

public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException() {
        super(ErrorMessage.USER_ALREADY_EXIST.getMessage());
    }
}
