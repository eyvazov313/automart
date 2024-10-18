package az.atl.auto_mart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModelRequest {

    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "brand id is required")
    @Min(value = 1, message = "brand id can not be 0 or negative")
    private Long brandId;
}
