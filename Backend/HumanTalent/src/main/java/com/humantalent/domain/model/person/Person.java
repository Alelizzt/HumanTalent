package com.humantalent.domain.model.person;

import com.humantalent.domain.model.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Person {

    private Name name;
    private PersonCountry country;
    @EqualsAndHashCode.Include
    private PersonIdType idType;
    @EqualsAndHashCode.Include
    private String idNumber;

}
