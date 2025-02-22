package SpringBoot_Spring.Data.JPA.student;

import SpringBoot_Spring.Data.JPA.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student toStudent(StudentDTO dto){
        if(dto == null){
            throw new NullPointerException("The student dto should not be null");
        }
        Student student = new Student();
        student.setFirstname(dto.getFirstname());
        student.setLastname(dto.getLastname());
        student.setEmail(dto.getEmail());

        School school = new School();
        school.setId(dto.getSchoolId());
        student.setSchool(school);

        return student;
    }

    public StudentResponseDTO toStudentResponseDTO(Student student){
        return new StudentResponseDTO(
                student.getFirstname(),
                student.getLastname(),
                student.getEmail());
    }
}
