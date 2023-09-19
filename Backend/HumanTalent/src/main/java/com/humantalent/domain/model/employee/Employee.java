package com.humantalent.domain.model.employee;

import com.humantalent.domain.model.EmailGenerator;
import com.humantalent.domain.model.person.Person;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee extends Person {

    private EmployeeWorkArea workArea;
    private boolean state;
    private LocalDateTime registrationDateAndTime;
    private LocalDateTime entryDate;
    private String email;

    private EmailGenerator emailGenerator;

    public void generateAndSetEmail() {
        this.email = emailGenerator.generateEmail(this);
    }
}
