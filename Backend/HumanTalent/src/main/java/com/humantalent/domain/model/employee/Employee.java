package com.humantalent.domain.model.employee;

import com.humantalent.domain.utils.EmailGenerator;
import com.humantalent.domain.model.person.Person;
import com.humantalent.domain.utils.LocalDateTimeConverter;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "employees")
@PrimaryKeyJoinColumn(name = "person_id")
public class Employee extends Person {

    @Column(name = "work_area")
    @Enumerated(EnumType.STRING)
    private EmployeeWorkArea workArea;

    @Column(nullable = false)
    private boolean state;

    @Column(name = "reg_datetime")
    @Convert(converter = LocalDateTimeConverter.class) // DD/MM/YYYY HH:mm:ss
    private LocalDateTime registrationDateAndTime;

    @Column(name = "entry_date")
    @Convert(converter = LocalDateTimeConverter.class) // DD/MM/YYYY HH:mm:ss
    private LocalDateTime entryDate;

    @Column(name = "email", length = 300)
    private String email;

    @Transient // No persistence on DB
    private EmailGenerator emailGenerator;

    public void generateAndSetEmail() {
        this.email = emailGenerator.generateEmail(this);
    }

    @PrePersist
    private void beforePersistence(){
        this.entryDate = LocalDateTime.now();
    }

    @PreUpdate
    private void afterUpdate(){
        this.registrationDateAndTime = LocalDateTime.now();
    }


}
