package SpringBoot_Spring.Data.JPA.student;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDTOToStudent(){
        // Given
        StudentDTO dto = new StudentDTO(
                "John",
                "Doe",
                "john@gmail.com",
                1
        );

        // When
        Student student = mapper.toStudent(dto);

        // Then
        assertEquals(dto.getFirstname(), student.getFirstname());
        assertEquals(dto.getLastname(), student.getLastname());
        assertEquals(dto.getEmail(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.getSchoolId(), student.getSchool().getId());
    }

    @Test
    public void shouldMapStudentToStudentResponseDTO(){
        // Given
        Student student = new Student();
        student.setFirstname("John");
        student.setLastname("Doe");
        student.setEmail("john@gmail.com");

        // When
        StudentResponseDTO dto = mapper.toStudentResponseDTO(student);

        // Then
        assertEquals(student.getFirstname(), dto.getFirstname());
        assertEquals(student.getLastname(), dto.getLastname());
        assertEquals(student.getEmail(), dto.getEmail());
    }

    @Test
    public void should_throw_null_pointer_exception_when_studentDTO_is_null(){
        NullPointerException exp = assertThrows(NullPointerException.class, () -> mapper.toStudent(null));
        assertEquals("The student dto should not be null", exp.getMessage());
    }
}