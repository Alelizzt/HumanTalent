package com.humantalent.adapters.repositories;

import com.humantalent.domain.model.employee.Employee;
import com.humantalent.domain.model.person.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("employeeRepository")
public interface EmployeeRepository extends PersonRepository {

    @Query("SELECT e FROM Employee e ORDER BY e.email ASC")
    Page<Employee> orderByEmail(Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.email LIKE %:email%")
    Page<Employee> findByEmailContaining(@Param("email") String email, Pageable pageable);

    @Query("SELECT COUNT(e) > 0 FROM Employee e WHERE e.email = ?1")
    boolean existsByEmail(String email);

    @Query("SELECT e FROM Employee e WHERE e.state = ?1")
    Iterable<Person> findByState(Boolean state);
}