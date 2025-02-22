package SpringBoot_Spring.Data.JPA.student;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    @NotEmpty(message = "El nombre no debe estar vacío")
    private String firstname;
    @NotEmpty(message = "El apellido no debe estar vacío")
    private String lastname;
    @NotEmpty(message = "El email no debe estar vacío")
    @Email
    private String email;
    @NotNull(message = "El campo no puede ser nulo")
    @Min(value = 1, message = "El valor debe ser mayor que 0")
    private Integer schoolId;
}