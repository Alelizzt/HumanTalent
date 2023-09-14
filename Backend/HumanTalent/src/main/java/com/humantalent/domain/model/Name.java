package com.humantalent.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Name {
    @EqualsAndHashCode.Include
    private String firstName;
    private String otherNames;
    @EqualsAndHashCode.Include
    private String firstLastName;
    @EqualsAndHashCode.Include
    private String secondLastName;

}
