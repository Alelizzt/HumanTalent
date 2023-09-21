package com.humantalent.domain.dto;

import com.humantalent.domain.model.Name;
import com.humantalent.domain.model.person.PersonCountry;
import com.humantalent.domain.model.person.PersonIdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class PersonDto {
    private Integer id;
    private Name name;
    private PersonCountry country;
    private PersonIdType idType;
    private String idNumber;
}
