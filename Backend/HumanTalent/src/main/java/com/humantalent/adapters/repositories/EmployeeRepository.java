package com.humantalent.adapters.repositories;

import com.humantalent.domain.model.person.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("employeeRepository")
public interface EmployeeRepository extends PersonRepository {

    @Query("SELECT e FROM Employee e WHERE e.email = ?1")
    Optional<Person> findByEmail(String email);

    @Query("SELECT e FROM Employee e WHERE e.state = ?1")
    Iterable<Person> findByState(Boolean state);
}