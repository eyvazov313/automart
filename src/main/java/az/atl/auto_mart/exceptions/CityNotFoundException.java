package az.atl.auto_mart.exceptions;

public class CityNotFoundException extends RuntimeException{

    public CityNotFoundException(String message) {
        super(message);
    }
}
