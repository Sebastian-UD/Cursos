package com.smr.Spring_Data_JPA_Hibernate.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Section extends BaseEntity{

    private String name;
    private int sectionOrder;
    @ManyToOne
    @JoinColumn(
            name = "course_id"
    )
    private Course course;
    @OneToMany(
            mappedBy = "section"
    )
    private List<Lecture> lectures;
}
