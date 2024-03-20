package com.humantalent.domain.model.employee;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.humantalent.domain.utils.EmailGenerator;
import com.humantalent.domain.model.person.Person;
import com.humantalent.domain.utils.LocalDateTimeConverter;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "employees")
@PrimaryKeyJoinColumn(name = "person_id")
@Schema(oneOf = Person.class, description = "Tipo de persona")
public class Employee extends Person {

    @Schema(description = "Área de trabajo del empleado", examples = {
            "ADMINISTRATION",
            "FINANCE",
            "PROCUREMENT",
            "INFRASTRUCTURE",
            "OPERATIONS",
            "HUMAN_RESOURCES",
            "MISCELLANEOUS_SERVICES"})
    @Column(name = "work_area")
    @Enumerated(EnumType.STRING)
    private EmployeeWorkArea workArea;

    @Schema(description = "Estado actual del empleado")
    @Column(nullable = false)
    private boolean state = Boolean.TRUE;

    @Hidden
    @Column(name = "reg_datetime")
    @Convert(converter = LocalDateTimeConverter.class) // DD/MM/YYYY HH:mm:ss
    private LocalDateTime regDateTime;

    @Schema(description = "Fecha de ingreso del empleado", example = "2024-02-12")
    @Column(name = "entry_date")
    private LocalDate entryDate;

    @Hidden
    @Column(name = "email", length = 300)
    private String email;

    @Hidden
    @Transient
    EmailGenerator emailGenerator;

    public void generateAndSetEmail(EmailGenerator emailGenerator) {
        if (this.email == null || this.email.isEmpty()) {
            this.email = emailGenerator.generateEmail(this);
        }
    }

    @PrePersist
    private void beforePersistence(){
        this.regDateTime = LocalDateTime.now();
    }

    @PreUpdate
    private void afterUpdate(){
        this.regDateTime = LocalDateTime.now();
    }


}
