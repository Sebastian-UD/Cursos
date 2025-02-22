package SpringBoot_Spring.Data.JPA.student;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(
            StudentRepository studentRepository,
            StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentResponseDTO> findAllStudents(){

        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDTO)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO findStudentById(Integer id){

        return studentRepository.findById(id)
                .map(studentMapper::toStudentResponseDTO)
                .orElse(null);
    }

    public List<StudentResponseDTO> findStudentByName(String name){
        return studentRepository.findAllByFirstnameContaining(name)
                .stream()
                .map(studentMapper::toStudentResponseDTO)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO saveStudent(@RequestBody StudentDTO dto){
        try {
            long millis = System.currentTimeMillis();
            Date d = new Date(millis);

            Student student = studentMapper.toStudent(dto);

            student.setCreate_date(d);

            Student savedStudent = studentRepository.save(student);

            return studentMapper.toStudentResponseDTO(savedStudent);
        }
        catch(DataIntegrityViolationException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteStudent(Integer id){
        studentRepository.deleteById(id);
    }
}
