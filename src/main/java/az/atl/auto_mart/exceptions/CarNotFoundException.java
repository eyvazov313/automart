package az.atl.auto_mart.exceptions;

public class CarNotFoundException extends RuntimeException{

    public CarNotFoundException(String message) {
        super(message);
    }
}
