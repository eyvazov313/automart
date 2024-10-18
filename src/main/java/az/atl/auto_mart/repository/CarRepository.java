package az.atl.auto_mart.repository;

import az.atl.auto_mart.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "UPDATE cars SET model_id = :modelId WHERE id = :carId RETURNING *", nativeQuery = true)
    Car updateModelOfCar(@Param("modelId") Long modelId, @Param("carId") Long carId);

    @Query(value = "UPDATE cars SET brand_id = :brandId where id = :carId RETURNING *", nativeQuery = true)
    Car updateBrandOfCar(@Param("brandId") Long modelId, @Param("carId") Long carId);
}
