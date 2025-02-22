package com.smr.Spring_Data_JPA_Hibernate.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// @Inheritance(strategy = InheritanceType.JOINED)
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @DiscriminatorColumn(name = "resource_type") solo con SINGLE_TABLE
public class Resource {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private int size;
    private String url;
    @OneToOne
//    @JoinColumn(
//            name = "lecture_id"
//    )
    private Lecture lecture;
}
