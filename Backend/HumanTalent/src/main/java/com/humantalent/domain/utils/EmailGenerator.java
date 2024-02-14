package com.humantalent.domain.utils;

import com.humantalent.adapters.repositories.EmployeeRepository;
import com.humantalent.domain.model.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class EmailGenerator {

    @Autowired
    private EmployeeRepository employeeRepository;
    public String generateEmail(Employee employee) {
        return generateUniqueEmail(employee);
    }

    private String generateUniqueEmail(Employee employee) {
        String baseEmail = employee.getFirstName().toLowerCase().replace(" ", "") + "."
                + employee.getFirstLastName().toLowerCase().replace(" ", "")
                + "@company.com." + employee.getCountry().toString().toLowerCase();

        String uniqueEmail = baseEmail;
        int counter = 1;

        while (employeeRepository != null && employeeRepository.existsByEmail(uniqueEmail)) {
            int atIndex = baseEmail.indexOf("@");
            uniqueEmail = baseEmail.substring(0, atIndex) +"."+ counter++ + baseEmail.substring(atIndex);
        }
        return uniqueEmail;
    }
}
