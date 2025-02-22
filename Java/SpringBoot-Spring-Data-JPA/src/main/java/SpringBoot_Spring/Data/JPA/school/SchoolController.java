package SpringBoot_Spring.Data.JPA.school;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/schools")
    public List<SchoolDTO> findAll(){
        return schoolService.findAll();
    }

    @GetMapping("/schools/{id}")
    public SchoolDTO findById(@PathVariable Integer id){
        return schoolService.findById(id);
    }

    @PostMapping("/schools")
    public SchoolDTO saveSchool(@RequestBody SchoolDTO dto){
        return schoolService.saveSchool(dto);
    }
}