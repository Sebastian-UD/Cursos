package SpringBoot_Spring.Data.JPA.school;

import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {

    public School toSchool(SchoolDTO dto){
        return new School(dto.getName());
    }

    public SchoolDTO toSchoolDTO(School school){
        return new SchoolDTO(school.getName());
    }
}
