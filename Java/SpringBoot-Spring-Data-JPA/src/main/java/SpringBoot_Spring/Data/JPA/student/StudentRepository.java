package SpringBoot_Spring.Data.JPA.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllByFirstnameContaining(String firstname);
}
