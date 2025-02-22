package SpringBoot_Spring.Data.JPA.school;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    private final SchoolMapper schoolMapper;
    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolMapper schoolMapper, SchoolRepository schoolRepository) {
        this.schoolMapper = schoolMapper;
        this.schoolRepository = schoolRepository;
    }

    public List<SchoolDTO> findAll(){
        return schoolRepository.findAll()
                .stream()
                .map(schoolMapper::toSchoolDTO)
                .collect(Collectors.toList());
    }

    public SchoolDTO findById(Integer id){
        return schoolRepository.findById(id)
                .map(schoolMapper::toSchoolDTO)
                .orElse(null);
    }

    public SchoolDTO saveSchool(SchoolDTO dto){
        School school = schoolMapper.toSchool(dto);
        schoolRepository.save(school);
        return dto;
    }
}
