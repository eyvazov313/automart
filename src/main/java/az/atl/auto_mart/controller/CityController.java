package az.atl.auto_mart.controller;

import az.atl.auto_mart.dto.CityDto;
import az.atl.auto_mart.service.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/city")
public class CityController {

    private final CityService service;

    @PostMapping
    public ResponseEntity<Void> addCity(@Valid @RequestBody CityDto dto) {
        service.add(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Set<CityDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PatchMapping
    public ResponseEntity<Void> updateCity(@RequestParam Long id, @Valid @RequestBody CityDto dto) {
        service.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
