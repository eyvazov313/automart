package az.atl.auto_mart.controller;

import az.atl.auto_mart.dto.CarRequest;
import az.atl.auto_mart.dto.CarResponse;
import az.atl.auto_mart.dto.UpdateCarRequest;
import az.atl.auto_mart.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/car")
public class CarController {

    private final CarService service;

    @PostMapping
    public ResponseEntity<Void> addCar(@Valid @RequestBody CarRequest request){
        service.add(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Set<CarResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PatchMapping
    public ResponseEntity<Void> updateCar(@RequestParam Long id, @Valid @RequestBody UpdateCarRequest request){
        service.update(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
