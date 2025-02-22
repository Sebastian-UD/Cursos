package com.smr.Spring_Data_JPA_Hibernate.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@NamedQuery(
        name = "Author.findNamedQuery",
        query = "SELECT a FROM Author a WHERE a.age >= :age"
)
//@Table(name = "AUTHOR_TBL")
public class Author extends BaseEntity{

//    @Column(
//            name = "f_name",
//            length = 35
//    )
    private String firstName;
    private String lastName;
//    @Column(
//            unique = true,
//            nullable = false
//    )
    private String email;
    private int age;
    @ManyToMany(
            mappedBy = "authors"
    )
    private List<Course> courses;
}
