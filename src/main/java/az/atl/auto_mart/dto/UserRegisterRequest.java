package az.atl.auto_mart.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {

    //TODO: give validation message for validation of date of birth

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "surname is required")
    private String surname;

    @NotBlank(message = "email is required")
    @Email(message = "Invalid email format")
    @Pattern(regexp = ".*\\.(com|org|net|edu|ru)$", message = "Email must end with .com, .org, .net, or .edu")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 8,max = 40, message = "Password must be at least 8, max 40 characters long")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=.]).*$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character (@#$%^&+=)"
    )
    private String password;

    @NotNull(message = "Date of birth is required. Format: yyyy-MM-dd")
    private LocalDate dateOfBirth;
}
