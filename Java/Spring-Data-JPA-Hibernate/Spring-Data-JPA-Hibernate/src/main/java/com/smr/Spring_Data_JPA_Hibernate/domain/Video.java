package com.smr.Spring_Data_JPA_Hibernate.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
// @PrimaryKeyJoinColumn(name = "video_id") solo con la estrategia JOINED
// @DiscriminatorValue("V") solo con la estrategia SINGLE_TABLE
// @Polymorphism(type = PolymorphismType.EXPLICIT) solo con la estrategia TABLE_PER_CLASS
public class Video extends Resource {

    private int length;
}
