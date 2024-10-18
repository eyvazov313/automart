package az.atl.auto_mart.exceptions;

public class BrandNotFoundException extends RuntimeException{
    public BrandNotFoundException(String message) {
        super(message);
    }
}
