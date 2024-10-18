package az.atl.auto_mart.service;


import az.atl.auto_mart.dto.ModelRequest;
import az.atl.auto_mart.dto.ModelResponse;
import az.atl.auto_mart.entity.Model;

import java.util.Set;

public interface ModelService {

    void add(ModelRequest request);

    Set<ModelResponse> getAll();

    Model getById(Long id);

    void update(Long id, ModelRequest request);

    void delete(Long id);
}
