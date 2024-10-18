package az.atl.auto_mart.controller;

import az.atl.auto_mart.dto.BrandDto;
import az.atl.auto_mart.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/brand")
public class BrandController {

    private final BrandService service;

    @PostMapping
    public ResponseEntity<Void> addBrand(@Valid @RequestBody BrandDto dto) {
        service.add(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Set<BrandDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PatchMapping
    public ResponseEntity<Void> updateBrand(@RequestParam Long id, @Valid @RequestBody BrandDto dto) {
        service.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
