package com.humantalent.application.service.contracts;

import com.humantalent.application.service.contracts.PersonService;
import com.humantalent.domain.model.person.Person;

import java.util.Optional;

public interface EmployeeService extends PersonService {
    Optional<Person> findByEmail(String email);
    Iterable<Person> findByState(Boolean state);

    Person processEmployeeAndGenerateEmail(Person employee);
}
