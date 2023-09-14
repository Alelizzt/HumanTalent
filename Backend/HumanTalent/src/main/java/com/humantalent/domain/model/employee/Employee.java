package com.humantalent.domain.model.employee;

import com.humantalent.domain.model.person.Person;
import lombok.*;


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
}
