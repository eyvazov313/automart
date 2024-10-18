package az.atl.auto_mart.service;

import az.atl.auto_mart.dto.BrandDto;
import az.atl.auto_mart.entity.Brand;
import az.atl.auto_mart.exceptions.BrandNotFoundException;
import az.atl.auto_mart.exceptions.ErrorMessage;
import az.atl.auto_mart.mapper.BrandMapper;
import az.atl.auto_mart.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository repository;

    private final BrandMapper mapper;

    @Override
    public void add(BrandDto dto) {
        repository.save(mapper.dtoToEntity(dto));
    }

    @Override
    public Set<BrandDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Brand getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new BrandNotFoundException(ErrorMessage.BRAND_NOT_FOUND.format(id)));
    }

    @Override
    public void update(Long id, BrandDto dto) {
        var brand = getById(id);
        mapper.updateEntity(dto, brand);
        repository.save(brand);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
