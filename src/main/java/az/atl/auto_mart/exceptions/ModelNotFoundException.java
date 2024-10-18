package az.atl.auto_mart.exceptions;

public class ModelNotFoundException extends RuntimeException{

    public ModelNotFoundException(String message) {
        super(message);
    }
}
