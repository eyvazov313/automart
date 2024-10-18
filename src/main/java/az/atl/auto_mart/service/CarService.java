package az.atl.auto_mart.service;

import az.atl.auto_mart.dto.CarRequest;
import az.atl.auto_mart.dto.CarResponse;
import az.atl.auto_mart.dto.UpdateCarRequest;

import java.util.Set;

public interface CarService {

    void add(CarRequest request);

    Set<CarResponse> getAll();

    void update(Long id, UpdateCarRequest request);

    void delete(Long id);
}
