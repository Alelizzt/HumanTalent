package com.humantalent.adapters.repositories;

import com.humantalent.domain.model.employee.Employee;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Sql(scripts = "/data-testing.sql")
class EmployeeRepositoryTest {
    @Autowired
    @Qualifier("employeeRepository")
    PersonRepository employeeRepository;

    @Test
    void orderByEmail() {
        // Given: Create Pageable with sorting by email in ascending order
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "email"));

        // When: Call orderByEmail method to get the ordered page
        Page<Employee> employeesOrderedByEmail = ((EmployeeRepository) employeeRepository).orderByEmail(pageable);

        // Then: Verify that the page is ordered correctly
        assertEquals("aimee.humphrey@company.com.eu", employeesOrderedByEmail.getContent().get(0).getEmail());
        assertEquals("alfonso.cash@company.com.eu", employeesOrderedByEmail.getContent().get(1).getEmail());
        assertEquals("alma.conway@company.com.eu", employeesOrderedByEmail.getContent().get(2).getEmail());

        // You can also check other properties of the Page object if needed
        assertEquals(10, employeesOrderedByEmail.getSize());
        assertEquals(42, employeesOrderedByEmail.getTotalElements());
    }

    @Test
    @Disabled
    void orderByWorkArea() {
    }

    @Test
    @Disabled
    void orderByState() {
    }

    @Test
    @Disabled
    void orderByRegDateTime() {
    }

    @Test
    @Disabled
    void orderByEntryDate() {
    }

    @Test
    @Disabled
    void findByEmailContaining() {
    }

    @Test
    @Disabled
    void existsByEmail() {
    }

    @Test
    @Disabled
    void findByState() {
    }
}