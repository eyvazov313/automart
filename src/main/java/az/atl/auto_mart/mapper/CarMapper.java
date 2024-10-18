package az.atl.auto_mart.mapper;

import az.atl.auto_mart.dto.CarRequest;
import az.atl.auto_mart.dto.CarResponse;
import az.atl.auto_mart.dto.UpdateCarRequest;
import az.atl.auto_mart.entity.Car;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "brand.id", source = "brandId")
    @Mapping(target = "city.id", source = "cityId")
    @Mapping(target = "model.id", source = "modelId")
    Car dtoToEntity(CarRequest request);

    @Mapping(source = "car.brand.name", target = "brandName")
    @Mapping(source = "car.model.name", target = "modelName")
    @Mapping(source = "car.city.name", target = "cityName")
    CarResponse entityToDto(Car car);

    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "city", ignore = true)
    @Mapping(target = "model", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UpdateCarRequest request, @MappingTarget Car car);
}
