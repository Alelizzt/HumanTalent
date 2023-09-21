package com.humantalent.domain.dto;

import com.humantalent.domain.model.employee.EmployeeWorkArea;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto extends PersonDto{

    @Setter
    private EmployeeWorkArea workArea;
    @Setter
    private LocalDateTime entryDate;

    private String email;
}
