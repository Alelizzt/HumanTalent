package com.humantalent.domain.model.employee;

import com.humantalent.domain.utils.EmailGenerator;
import com.humantalent.domain.model.person.Person;
import com.humantalent.domain.utils.LocalDateTimeConverter;
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
public class Employee extends Person {

    @Column(name = "work_area")
    @Enumerated(EnumType.STRING)
    private EmployeeWorkArea workArea;

    @Column(nullable = false)
    private boolean state = Boolean.TRUE;

    @Column(name = "reg_datetime")
    @Convert(converter = LocalDateTimeConverter.class) // DD/MM/YYYY HH:mm:ss
    private LocalDateTime regDateTime;

    @Column(name = "entry_date")
    //@Convert(converter = LocalDateTimeConverter.class) // DD/MM/YYYY HH:mm:ss
    private LocalDate entryDate;

    @Column(name = "email", length = 300)
    private String email;

    @Transient
    EmailGenerator emailGenerator;

    public void generateAndSetEmail(EmailGenerator emailGenerator) {
        if (this.email == null || this.email.isEmpty()) {
            this.email = emailGenerator.generateEmail(this);
        }
    }

    @PrePersist
    private void beforePersistence(){
        //this.entryDate = LocalDate.now();
        this.regDateTime = LocalDateTime.now();
    }

    @PreUpdate
    private void afterUpdate(){
        this.regDateTime = LocalDateTime.now();
    }


}
