package az.atl.auto_mart.mapper;

import az.atl.auto_mart.dto.CityDto;
import az.atl.auto_mart.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CityMapper {

    City dtoToEntity(CityDto dto);


    CityDto entityToDto(City city);

    void updateEntity(CityDto dto, @MappingTarget City city);
}
