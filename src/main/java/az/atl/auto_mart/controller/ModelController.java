package az.atl.auto_mart.controller;

import az.atl.auto_mart.dto.ModelRequest;
import az.atl.auto_mart.dto.ModelResponse;
import az.atl.auto_mart.service.ModelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/model")
public class ModelController {

    private final ModelService service;

    @PostMapping
    public ResponseEntity<Void> addModel(@Valid @RequestBody ModelRequest request) {
        service.add(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Set<ModelResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PatchMapping
    public ResponseEntity<Void> updateModel(@RequestParam Long id, @Valid @RequestBody ModelRequest request) {
        service.update(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
