package az.atl.auto_mart.dto;

import az.atl.auto_mart.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {

    BigDecimal engine;

    Integer horsePower;

    Integer releaseYear;

    Integer mileage;

    BigDecimal price;

    String addInfo;

    Boolean isActive;

    BodyType bodyType;

    FuelType fuelType;

    GearBox gearBox;

    Transmitter transmitter;

    Color color;

    String brandName;

    String modelName;

    String cityName;
}
