package com.humantalent.domain.model;

import com.humantalent.domain.model.employee.Employee;

public class EmailGenerator {

    public String generateEmail(Employee employee) {
        String email = "";

        return employee.getName().getFirstName().toLowerCase() + "."
                + employee.getName().getFirstLastName().toLowerCase().trim()
                + "@company.com." + employee.getCountry().toString().toLowerCase();
    }
}
