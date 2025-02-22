package SpringBoot_Spring.Data.JPA.student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.MethodArgumentNotValidException;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<StudentResponseDTO> findSAllStudents(){
        return studentService.findAllStudents();
    }

    @GetMapping("/students/{id_student}")
    public StudentResponseDTO findStudentById(@PathVariable("id_student") Integer id){
        return studentService.findStudentById(id);
    }

    @GetMapping("/students/search/{student_name}")
    public List<StudentResponseDTO> findStudentByName(@PathVariable("student_name") String name){
        return studentService.findStudentByName(name);
    }

    @PostMapping("/students")
    public StudentResponseDTO saveStudent(
            @Valid @RequestBody StudentDTO dto
    ){
        return studentService.saveStudent(dto);
    }

    @DeleteMapping("/students/{id_student}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable("id_student") Integer id){
        studentService.deleteStudent(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exp
    ){
        HashMap<String, String> errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    //------------------------------------------------------------//

    /*
    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String sayHello(){
        return "Hello from my first controller";
    }

    @GetMapping("/hello/{user-name}")
    public String pathVariable(@PathVariable("user-name") String username){
        return "my value = " + username;
    }

    @GetMapping("/hello/params")
    public String paramVariable(
            @RequestParam("user-name") String username,
            @RequestParam("user-lastname") String lastname
    ){
        return "my value = " + username + " " + lastname;
    }

    @PostMapping("/post-order")
    public String post(@RequestBody Order order){
        return "Request accepted and order is: " + order.toString();
    }

    @PostMapping("/post-order-record")
    public String postRecord(@RequestBody Order order){
        return "Request accepted and order is: " + order.toString();
    }
    */
}
