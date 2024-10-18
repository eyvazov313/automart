package az.atl.auto_mart.mapper;

import az.atl.auto_mart.dto.ModelRequest;
import az.atl.auto_mart.dto.ModelResponse;
import az.atl.auto_mart.entity.Model;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ModelMapper {

    Model dtoToEntity(ModelRequest request);

    @Mapping(source = "model.brand.name", target = "brandName")
    ModelResponse entityToDto(Model model);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ModelRequest request, @MappingTarget Model model);
}
