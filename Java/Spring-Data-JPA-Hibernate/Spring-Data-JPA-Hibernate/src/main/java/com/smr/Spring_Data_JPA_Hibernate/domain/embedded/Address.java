package com.smr.Spring_Data_JPA_Hibernate.domain.embedded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

    private String streetName;
    private String houseNumber;
    private String zipCode;
}
