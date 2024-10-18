package az.atl.auto_mart.entity;

import az.atl.auto_mart.enums.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    BigDecimal engine;

    @Column(nullable = false)
    Integer horsePower;

    @Column(nullable = false)
    Integer releaseYear;

    @Column(nullable = false)
    Integer mileage;

    @Column(nullable = false)
    BigDecimal price;

    String addInfo;

    @Column(nullable = false)
    Boolean isActive;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    BodyType bodyType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    FuelType fuelType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    GearBox gearBox;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Transmitter transmitter;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    Model model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    City city;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

//    image;------------------------------------------------------

}
