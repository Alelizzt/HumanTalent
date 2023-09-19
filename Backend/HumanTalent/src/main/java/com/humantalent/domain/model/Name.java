package com.humantalent.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public abstract class Name implements Serializable {
    @EqualsAndHashCode.Include
    private String firstName;
    private String otherNames;
    @EqualsAndHashCode.Include
    private String firstLastName;
    @EqualsAndHashCode.Include
    private String secondLastName;

}
