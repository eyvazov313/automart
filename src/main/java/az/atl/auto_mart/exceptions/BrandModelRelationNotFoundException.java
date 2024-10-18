package az.atl.auto_mart.exceptions;

public class BrandModelRelationNotFoundException extends RuntimeException{
    public BrandModelRelationNotFoundException() {
        super(ErrorMessage.BRAND_MODEL_RELATION_NOT_FOUND.getMessage());
    }
}
