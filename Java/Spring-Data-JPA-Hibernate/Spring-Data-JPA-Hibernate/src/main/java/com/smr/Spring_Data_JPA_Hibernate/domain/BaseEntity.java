package com.smr.Spring_Data_JPA_Hibernate.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue
            /*(
            strategy = GenerationType.TABLE,
            generator = "author_id_gen"
    )*/
//    @SequenceGenerator(
//            name = "author_sequence",
//            sequenceName = "author_sequence",
//            allocationSize = 1
//    )
//    @TableGenerator(
//            name = "author_id_gen",
//            table = "id_generator",
//            pkColumnName = "id_name",
//            valueColumnName = "id_value",
//            allocationSize = 1
//    )
    private Integer id;

    //    @Column(
//            updatable = false,
//            nullable = false
//    )
    private LocalDateTime createdAt;
    //    @Column(
//            insertable = false
//    )
    private LocalDateTime lastModified;
    private String createdBy;
    private String lastModifiedBy;
}
