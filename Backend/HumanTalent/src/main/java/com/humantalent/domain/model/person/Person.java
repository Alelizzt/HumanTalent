package com.humantalent.domain.model.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.humantalent.domain.model.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Employee.class, name = "employee")
        // Agregar más subtipos si es necesario
})
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @EqualsAndHashCode.Include
    private String firstName;
    private String otherNames;
    @EqualsAndHashCode.Include
    private String firstLastName;
    @EqualsAndHashCode.Include
    private String secondLastName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonCountry country;

    @Column(nullable = false, name = "id_type", columnDefinition = "VARCHAR DEFAULT 'CITIZENSHIP_CARD'")
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Include
    private PersonIdType idType;

    @Column(nullable = false, unique = true, name = "identification_num", length = 50)
    @EqualsAndHashCode.Include
    private String identificationNum;//TODO: Debe cambiarse a String, incluyendo sus metodos de busqueda

}
