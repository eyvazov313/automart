package az.atl.auto_mart.mapper;

import az.atl.auto_mart.dto.BrandDto;
import az.atl.auto_mart.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    Brand dtoToEntity(BrandDto dto);


    BrandDto entityToDto(Brand brand);


    void updateEntity(BrandDto dto, @MappingTarget Brand brand);
}
