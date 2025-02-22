package SpringBoot_Spring.Data.JPA.school;

import SpringBoot_Spring.Data.JPA.student.Student;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class School {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    public School(String name){
        this.name = name;
    }

    @OneToMany(
            mappedBy = "school",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<Student> students;

}
