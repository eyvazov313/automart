package az.atl.auto_mart.service;

import az.atl.auto_mart.dto.BrandDto;
import az.atl.auto_mart.entity.Brand;

import java.util.Set;

public interface BrandService {
    
    void add(BrandDto dto);
    
    Set<BrandDto> getAll();

    Brand getById(Long id);
    
    void update(Long id, BrandDto dto);
    
    void delete(Long id);
}
