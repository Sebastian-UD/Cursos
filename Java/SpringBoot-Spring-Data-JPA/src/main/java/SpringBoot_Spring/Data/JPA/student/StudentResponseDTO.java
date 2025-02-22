package SpringBoot_Spring.Data.JPA.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {

    private String firstname;
    private String lastname;
    private String email;
}
