package com.humantalent.adapters.controllers;

import com.humantalent.adapters.repositories.EmployeeRepository;
import com.humantalent.application.service.implementations.EmployeeServiceImpl;
import com.humantalent.domain.model.employee.Employee;
import com.humantalent.domain.model.employee.EmployeeWorkArea;
import com.humantalent.domain.model.person.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.humantalent.data.DummyData.employee02;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.humantalent.data.DummyData.employee01;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    void setup() {
        employee = (Employee) employee01();
    }

    @DisplayName("Test para obtener un empleado por ID")
    @Test
    void getEmployeeById() {
        // Given
        given(employeeRepository.findById(employee.getId())).willReturn(Optional.of(employee));

        // When
        Employee savedEmployee = (Employee) employeeService.findById(employee.getId()).get();

        // Then
        assertThat(savedEmployee).isNotNull();
    }

    @DisplayName("Test para listar a los empleados")
    @Test
    void getAllEmployees() {
        // Given
        Employee employee1 = (Employee) employee02();
        given(employeeRepository.findAll()).willReturn(List.of(employee,employee1));

        // When
        List<Person> employees =
                StreamSupport.stream(employeeService.findAll().spliterator(), false).toList();

        // Then
        assertThat(employees).isNotNull();
        assertThat(employees.size()).isEqualTo(2);
    }

    @DisplayName("Test para guardar un empleado")
    @Test
    void createEmployee() {
        // Given
        given(employeeRepository.save(employee)).willReturn(employee);

        // When
        Employee savedEmployee = (Employee) employeeService.save(employee);

        // Then
        assertThat(savedEmployee).isNotNull();
    }

    @DisplayName("Test para actualizar a un empleado")
    @Test
    void updateEmployee() {
        // Given
        given(employeeRepository.save(employee)).willReturn(employee);
        employee.setIdentificationNum("1234567891011");
        employee.setWorkArea(EmployeeWorkArea.FINANCE);

        // When
        Employee updatedEmployee = (Employee) employeeService.save(employee);

        // Then
        assertThat(updatedEmployee.getIdentificationNum()).isEqualTo("1234567891011");
        assertThat(updatedEmployee.getWorkArea()).isEqualTo(EmployeeWorkArea.FINANCE);
    }

    @DisplayName("Test para eliminar un empleado")
    @Test
    void deleteEmployee() {
        // Given
        Integer employeeId = 1;
        willDoNothing().given(employeeRepository).deleteById(employeeId);

        // When
        employeeService.deleteById(employeeId);

        // Then
        verify(employeeRepository,times(1)).deleteById(employeeId);
        assertThat(employeeService.findById(employeeId)).isEmpty();
    }
}