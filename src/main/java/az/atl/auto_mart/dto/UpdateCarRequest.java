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
public class UpdateCarRequest {

    @Min(value = 0, message = "engine is not identified correctly")
    BigDecimal engine;

    @Min(value = 1, message = "horse power is not identified correctly")
    Integer horsePower;

    @Min(value = 1886, message = "min value for release year is 1886")
    @Max(value = 2024, message = "max value for release year is 2024")
    Integer releaseYear;

    @Min(value = 0, message = "mileage can not be minus")
    Integer mileage;

    @Min(value = 0, message = "price can not be minus")
    BigDecimal price;

    String addInfo;

    BodyType bodyType;

    FuelType fuelType;

    GearBox gearBox;

    Transmitter transmitter;

    Color color;

    Long brandId;

    Long modelId;

    Long cityId;
}
