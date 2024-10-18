package az.atl.auto_mart.service;

import az.atl.auto_mart.dto.BrandDto;
import az.atl.auto_mart.dto.CityDto;
import az.atl.auto_mart.entity.Brand;
import az.atl.auto_mart.entity.City;
import az.atl.auto_mart.exceptions.BrandNotFoundException;
import az.atl.auto_mart.exceptions.CityNotFoundException;
import az.atl.auto_mart.exceptions.ErrorMessage;
import az.atl.auto_mart.mapper.BrandMapper;
import az.atl.auto_mart.mapper.CityMapper;
import az.atl.auto_mart.repository.BrandRepository;
import az.atl.auto_mart.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository repository;

    private final CityMapper mapper;

    @Override
    public void add(CityDto dto) {
        repository.save(mapper.dtoToEntity(dto));
    }

    @Override
    public Set<CityDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toSet());
    }

    @Override
    public City getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new CityNotFoundException(ErrorMessage.CITY_NOT_FOUND.format(id)));
    }

    @Override
    public void update(Long id, CityDto dto) {
        var city = getById(id);
        mapper.updateEntity(dto, city);
        repository.save(city);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
