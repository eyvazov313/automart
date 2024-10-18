package az.atl.auto_mart.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    USER_NOT_FOUND("User not found"),

    USER_ALREADY_EXIST("User already exist") ,

    BRAND_NOT_FOUND("Brand not found with id: %s"),

    MODEL_NOT_FOUND("Model not found with id: %s"),

    CITY_NOT_FOUND("City not found with id: %s"),

    CAR_NOT_FOUND("Car not found with id: %s"),

    BRAND_MODEL_RELATION_NOT_FOUND("Given model and brand relation not exists."),

    INVALID_AUTHENTICATION_CREDENTIALS("Email or password is incorrect"),

    TOKEN_EXPIRED("Token expired"),

    TOKEN_NOT_FOUND("Token not found"),

    INVALID_TOKEN("Invalid token");

    private final String message;

    public String format(Object... args) {
        return String.format(message, args);
    }
}
