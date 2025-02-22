package SpringBoot_Spring.Data.JPA.student;

import SpringBoot_Spring.Data.JPA.school.School;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    // which service we want to test
    @InjectMocks
    private StudentService studentService;

    // declare the dependencies
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_student(){
        // given
        StudentDTO dto = new StudentDTO(
                "John",
                "Doe",
                "john@gmail.com",
                1
        );
        Student student = new Student(
                "John",
                "Doe",
                "john@gmail.com"
        );
        School s = new School();
        s.setId(1);
        student.setSchool(s);

        Student savedStudent = new Student(
                "John",
                "Doe",
                "john@gmail.com"
        );
        savedStudent.setId(1);
        savedStudent.setSchool(s);

        // Mock the calls
        when(studentMapper.toStudent(dto))
                .thenReturn(student);
        when(studentRepository.save(student))
                .thenReturn(savedStudent);
        when(studentMapper.toStudentResponseDTO(savedStudent))
                .thenReturn(new StudentResponseDTO(
                        "John",
                        "Doe",
                        "john@gmail.com")
                );
        // when
        StudentResponseDTO responseDTO = studentService.saveStudent(dto);

        //then
        assertEquals(dto.getFirstname(), responseDTO.getFirstname());
        assertEquals(dto.getLastname(), responseDTO.getLastname());
        assertEquals(dto.getEmail(), responseDTO.getEmail());

        verify(studentMapper, times(1))
                .toStudent(dto);
        verify(studentRepository, times(1))
                .save(student);
        verify(studentMapper, times(1))
                .toStudentResponseDTO(savedStudent);
    }

    @Test
    public void should_return_all_students(){
        // given
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "John",
                "Doe",
                "john@gmail.com"
        ));

        //Mock the calls
        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDTO(any(Student.class)))
                .thenReturn(new StudentResponseDTO(
                        "John",
                        "Doe",
                        "john@gmail.com"
                ));

        // when
        List<StudentResponseDTO> responsesDTO = studentService.findAllStudents();

        // then
        assertEquals(students.size(), responsesDTO.size());
        verify(studentRepository, times(1))
                .findAll();
    }

    @Test
    public void should_return_student_by_id(){
        // given
        Integer studentId = 1;
        Student student = new Student(
                "John",
                "Doe",
                "john@gmail.com"
        );

        //Mocks calls
        when(studentRepository.findById(studentId))
                .thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDTO(any(Student.class)))
                .thenReturn(new StudentResponseDTO(
                        "John",
                        "Doe",
                        "john@gmail.com"
                ));

        // when
        StudentResponseDTO dto = studentService.findStudentById(studentId);

        // then
        assertEquals(dto.getFirstname(), student.getFirstname());
        assertEquals(dto.getLastname(), student.getLastname());
        assertEquals(dto.getEmail(), student.getEmail());

        verify(studentRepository, times(1)).findById(studentId);
    }

    @Test
    public void should_return_student_by_name(){
        // given
        String studentFirstName = "John";
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "John",
                "Doe",
                "john@gmail.com"
        ));

        //Mocks calls
        when(studentRepository.findAllByFirstnameContaining(studentFirstName))
                .thenReturn(students);
        when(studentMapper.toStudentResponseDTO(any(Student.class)))
                .thenReturn(new StudentResponseDTO(
                        "John",
                        "Doe",
                        "john@gmail.com"
                ));

        // when
        List<StudentResponseDTO> responseDTO = studentService
                .findStudentByName(studentFirstName);

        // then
        assertEquals(students.size(), responseDTO.size());
        verify(studentRepository, times(1))
                .findAllByFirstnameContaining(studentFirstName);
    }
}