package az.atl.auto_mart.mapper;

import az.atl.auto_mart.dto.UserRegisterRequest;
import az.atl.auto_mart.entity.User;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", expression = "java(az.atl.auto_mart.enums.Role.ROLE_USER)")
    User dtoToEntity(UserRegisterRequest userRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserEntity(UserRegisterRequest request, @MappingTarget User user);
}
