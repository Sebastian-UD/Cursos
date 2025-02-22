package SpringBoot_Spring.Data.JPA.student;

import SpringBoot_Spring.Data.JPA.school.School;
import SpringBoot_Spring.Data.JPA.studentProfile.StudentProfile;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_STUDENT")
public class Student {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(
            name = "c_fname",
            length = 20
    )
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    private Integer age;
    @Column(updatable = false)
    private Date create_date;

    @OneToOne(
            mappedBy = "student",
            cascade = CascadeType.ALL
    )
    private StudentProfile studentProfile;

    @ManyToOne
    @JoinColumn(
            name = "school_id"
    )
    @JsonBackReference
    private School school;

    public Student(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
}
