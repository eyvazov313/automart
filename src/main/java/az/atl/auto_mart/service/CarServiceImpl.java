package az.atl.auto_mart.service;

import az.atl.auto_mart.dto.CarRequest;
import az.atl.auto_mart.dto.CarResponse;
import az.atl.auto_mart.dto.UpdateCarRequest;
import az.atl.auto_mart.exceptions.CarNotFoundException;
import az.atl.auto_mart.exceptions.ErrorMessage;
import az.atl.auto_mart.exceptions.BrandModelRelationNotFoundException;
import az.atl.auto_mart.mapper.CarMapper;
import az.atl.auto_mart.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository repository;

    private final CarMapper mapper;

    private final ModelService modelService;

    private final BrandService brandService;

    @Override
    public void add(CarRequest request) {
        var model = modelService.getById(request.getModelId());
        if (!Objects.equals(model.getBrand().getId(), request.getBrandId())) {
            throw new BrandModelRelationNotFoundException();
        }
        var car = mapper.dtoToEntity(request);
        repository.save(car);
    }

    @Override
    public Set<CarResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toSet());
    }

    @Override
    public void update(Long id, UpdateCarRequest request) {
        var car = repository.findById(id).orElseThrow(() -> new CarNotFoundException(ErrorMessage.CAR_NOT_FOUND.format(id)));
        mapper.update(request, car);
        if (request.getModelId() != null && request.getBrandId() == null) {
            var model = modelService.getById(request.getModelId());
            if (!car.getBrand().getModels().contains(model)) {
                throw new BrandModelRelationNotFoundException();
            }
            car = repository.updateModelOfCar(request.getModelId(), car.getId());
        } else if (request.getModelId() == null && request.getBrandId() != null) {
            var brand = brandService.getById(request.getBrandId());
            if (!brand.getModels().contains(car.getModel())) {
                throw new BrandModelRelationNotFoundException();
            }
            car = repository.updateBrandOfCar(request.getBrandId(), car.getId());
        } else if (request.getModelId() != null && request.getBrandId() != null) {
            var model = modelService.getById(request.getModelId());
            var brand = brandService.getById(request.getBrandId());
            if (!Objects.equals(model.getBrand().getId(), request.getBrandId())) {
                throw new BrandModelRelationNotFoundException();
            }
            car = repository.updateModelOfCar(request.getModelId(), car.getId());
            car = repository.updateBrandOfCar(request.getBrandId(), car.getId());
        }
        repository.save(car);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
