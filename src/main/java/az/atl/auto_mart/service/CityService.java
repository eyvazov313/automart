package az.atl.auto_mart.service;

import az.atl.auto_mart.dto.CityDto;
import az.atl.auto_mart.entity.City;

import java.util.Set;

public interface CityService {

    void add(CityDto dto);

    Set<CityDto> getAll();

    City getById(Long id);

    void update(Long id, CityDto dto);

    void delete(Long id);
}
