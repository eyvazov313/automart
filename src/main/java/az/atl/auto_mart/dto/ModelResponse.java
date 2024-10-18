package az.atl.auto_mart.dto;

import az.atl.auto_mart.entity.Brand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModelResponse {

    private String name;

    private String brandName;
}
