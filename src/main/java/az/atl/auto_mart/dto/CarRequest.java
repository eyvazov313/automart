package az.atl.auto_mart.dto;

import az.atl.auto_mart.enums.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {

    @NotNull(message = "engine is required")
    @Min(value = 0, message = "engine is not identified correctly")
    BigDecimal engine;

    @NotNull(message = "horse power is required")
    @Min(value = 1, message = "horse power is not identified correctly")
    Integer horsePower;

    @NotNull(message = "release year is required")
    @Min(value = 1886, message = "min value for release year is 1886")
    @Max(value = 2024, message = "max value for release year is 2024")
    Integer releaseYear;

    @NotNull(message = "mileage is required")
    @Min(value = 0, message = "mileage can not be minus")
    Integer mileage;

    @NotNull(message = "price is required")
    @Min(value = 0, message = "price can not be minus")
    BigDecimal price;

    String addInfo;

    @NotNull(message = "body type is required")
    BodyType bodyType;

    @NotNull(message = "fuel type is required")
    FuelType fuelType;

    @NotNull(message = "gear box is required")
    GearBox gearBox;

    @NotNull(message = "transmitter is required")
    Transmitter transmitter;

    @NotNull(message = "color is required")
    Color color;

    @NotNull(message = "brand id is required")
    Long brandId;

    @NotNull(message = "model id is required")
    Long modelId;

    @NotNull(message = "city id is required")
    Long cityId;
}
