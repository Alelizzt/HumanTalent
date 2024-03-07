package com.humantalent.application.service.contracts;


import com.humantalent.domain.model.employee.Employee;
import com.humantalent.domain.model.person.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public interface EmployeeService extends PersonService {
    Page<Employee> getEmployeePaginationByEmail(Integer pageNumber, Integer pageSize, String sort) ;
    Iterable<Person> findByState(Boolean state);
    Person processEmployeeAndGenerateEmail(Person employee);
    Page<Employee> getEmployeePagination(Integer pageNumber, Integer pageSize, String sort);

}
