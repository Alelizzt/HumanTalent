package com.humantalent.domain.model.person;

import com.humantalent.domain.model.Name;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person implements Serializable {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "first_name", length = 20)),
            @AttributeOverride(name = "otherNames", column = @Column(name = "other_names", length = 20)),
            @AttributeOverride(name = "firstLastName", column = @Column(name = "first_last_name", length = 20)),
            @AttributeOverride(name = "secondLastName", column = @Column(name = "second_last_name", length = 50))
    })
    @Pattern (regexp = "^[A-NO-Z]*$",
            message = "The name must contain only capital letters without accents or ñ")
    private Name name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonCountry country;

    @Column(nullable = false, name = "id_type")
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Include
    private PersonIdType idType;

    @Id
    @Column(nullable = false, name = "person_id", length = 50)
    @EqualsAndHashCode.Include
    private String idNumber;

}
