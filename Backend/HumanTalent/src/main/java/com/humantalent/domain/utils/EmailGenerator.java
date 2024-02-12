package com.humantalent.domain.utils;

import com.humantalent.domain.model.employee.Employee;

public class EmailGenerator {

    public String generateEmail(Employee employee) {
        String email = "";

        return employee.getFirstName().toLowerCase() + "."
                + employee.getFirstLastName().toLowerCase().trim()
                + "@company.com." + employee.getCountry().toString().toLowerCase();
    }
}
