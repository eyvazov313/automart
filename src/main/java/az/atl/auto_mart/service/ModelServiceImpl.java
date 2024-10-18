package az.atl.auto_mart.service;

import az.atl.auto_mart.dto.ModelRequest;
import az.atl.auto_mart.dto.ModelResponse;
import az.atl.auto_mart.entity.Model;
import az.atl.auto_mart.exceptions.ErrorMessage;
import az.atl.auto_mart.exceptions.ModelNotFoundException;
import az.atl.auto_mart.mapper.ModelMapper;
import az.atl.auto_mart.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService{

    private final ModelRepository repository;

    private final ModelMapper mapper;

    private final BrandService brandService;

    @Override
    public void add(ModelRequest request) {
        var brand = brandService.getById(request.getBrandId());
        var model = mapper.dtoToEntity(request);
        model.setBrand(brand);
        repository.save(model);
    }

    @Override
    public Set<ModelResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Model getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ModelNotFoundException(ErrorMessage.MODEL_NOT_FOUND.format(id)));
    }


    @Override
    public void update(Long id, ModelRequest request) {
        var model = getById(id);
        mapper.updateEntity(request, model);
        if (request.getBrandId() != null) {
            var brand = brandService.getById(request.getBrandId());
            model.setBrand(brand);
        }
        repository.save(model);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
