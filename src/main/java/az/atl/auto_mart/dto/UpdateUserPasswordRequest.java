package az.atl.auto_mart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserPasswordRequest {

    @NotBlank(message = "password is required")
    @Size(min = 8,max = 40, message = "Password must be at least 8, max 40 characters long")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=.]).*$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character (@#$%^&+=)"
    )
    private String newPassword;
}
