package com.humantalent.domain.model.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.humantalent.domain.model.employee.Employee;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Employee.class, name = "employee")
})
@Schema(discriminatorProperty = "type",
        discriminatorMapping = {
            @DiscriminatorMapping(value = "employee", schema = Employee.class)},
        description = "Supertype of all employees.")
public abstract class Person {
    @Hidden
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "Primer nombre de la persona", example = "Maria")
    @NotEmpty
    @EqualsAndHashCode.Include
    private String firstName;

    @Schema(description = "Segundo nombre de la persona", example = "Juana")
    private String otherNames;

    @Schema(description = "Primer apellido de la persona", example = "Martinez")
    @NotEmpty
    @EqualsAndHashCode.Include
    private String firstLastName;

    @Schema(description = "Segundo apellido de la persona", example = "Gutierrez")
    @NotEmpty
    @EqualsAndHashCode.Include
    private String secondLastName;

    @Schema(description = "Pais donde se encuentra la persona", examples = {"CO", "EU"})
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonCountry country;

    @Schema(description = "Tipo de documento de la persona", examples = {
            "CITIZENSHIP_CARD",
            "FOREIGNER_ID",
            "PASSPORT",
            "SPECIAL_PERMISSION"})
    @Column(nullable = false, name = "id_type", columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Include
    private PersonIdType idType = PersonIdType.CITIZENSHIP_CARD;

    @Schema(description = "Número de identificación de la persona", example = "1033123456")
    @Column(nullable = false, unique = true, name = "identification_num", length = 50)
    @EqualsAndHashCode.Include
    private String identificationNum;

}
