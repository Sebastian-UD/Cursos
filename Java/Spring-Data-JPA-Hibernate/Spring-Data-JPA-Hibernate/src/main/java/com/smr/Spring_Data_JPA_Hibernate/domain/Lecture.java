package com.smr.Spring_Data_JPA_Hibernate.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Lecture extends BaseEntity{

    private String name;
    @ManyToOne
    @JoinColumn(
            name = "section_id"
    )
    private Section section;
    @OneToOne
    @JoinColumn(
            name = "resource_id"
    )
    private Resource resource;
}
