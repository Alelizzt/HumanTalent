package com.humantalent.adapters.repositories;

import com.humantalent.domain.model.employee.Employee;
import com.humantalent.domain.model.person.Person;
import com.humantalent.domain.model.person.PersonCountry;
import com.humantalent.domain.model.person.PersonIdType;
import com.humantalent.data.DummyData.*;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static com.humantalent.data.DummyData.employee01;
import static com.humantalent.data.DummyData.employee02;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
//@Sql(scripts = "/data-testing.sql")
class EmployeeRepositoryTest {
    @Autowired
    @Qualifier("employeeRepository")
    PersonRepository employeeRepository;

    @DisplayName("Test para guardar un empleado")
    @Test
    void saveEmployee(){
        // Given
        Employee employee = new Employee (null,"Maria","Fernanda",
                "Perez","Jimenez",
                PersonCountry.CO, PersonIdType.CITIZENSHIP_CARD,
                "1033763321");

        // When
        Employee savedEmployee = employeeRepository.save(employee);

        // Then
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @DisplayName("Test para listar a los empleados")
    @Test
    void showEmployees() {
        // Given
        employeeRepository.save(employee01());
        employeeRepository.save(employee02());

        // When
        List<Person> employeeList = employeeRepository.findAll();

        // Then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @DisplayName("Test para obtener un empleado por id")
    @Test
    void getEmployeeById() {
        // Given
        Employee testEmployee = (Employee) employee01();
        employeeRepository.save(testEmployee);

        // When
        Person employeeDB = employeeRepository.findById(testEmployee.getId()).get();

        // Then
        assertThat(employeeDB).isNotNull();
    }

    @DisplayName("Test para actualizar un empleado")
    @Test
    void updateEmployee() {
        // Given
        Employee testEmployee = (Employee) employee02();
        employeeRepository.save(testEmployee);

        List<Person> all = employeeRepository.findAll();
        // When
        Person savedEmployee = employeeRepository.findById(testEmployee.getId()).get();
        savedEmployee.setFirstName("Ivan");
        savedEmployee.setFirstLastName("Soza");
        savedEmployee.setIdentificationNum("1022485123");

        Employee updatedEmployee = (Employee) employeeRepository.save(savedEmployee);

        // Then
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Ivan");
        assertThat(updatedEmployee.getFirstLastName()).isEqualTo("Soza");
        assertThat(updatedEmployee.getIdentificationNum()).isEqualTo("1022485123");

    }

    @DisplayName("Test para eliminar un empleado")
    @Test
    void deleteEmployee() {
        // Given
        Employee testEmployee = (Employee) employee01();
        employeeRepository.save(testEmployee);

        // When
        employeeRepository.deleteById(testEmployee.getId());
        Optional<Person> optionalEmployee = employeeRepository.findById(testEmployee.getId());

        // Then
        assertThat(optionalEmployee).isEmpty();

    }

}