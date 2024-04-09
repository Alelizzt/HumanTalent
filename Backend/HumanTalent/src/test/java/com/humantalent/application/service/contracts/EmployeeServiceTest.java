package com.humantalent.application.service.contracts;

import com.humantalent.adapters.repositories.EmployeeRepository;
import com.humantalent.application.service.implementations.EmployeeServiceImpl;
import com.humantalent.domain.model.employee.Employee;
import com.humantalent.domain.utils.EmailGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.humantalent.data.DummyData.employee01;
import static com.humantalent.data.DummyData.employee02;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @DisplayName("Test para guardar un empleado")
    @Test
    void saveEmployee() {
        // Given
        employee = (Employee) employee01();
        given(employeeRepository.findById(employee.getId()))
                .willReturn(Optional.empty());
        given(employeeRepository.save(employee)).willReturn(employee);

        // When
        Employee savedEmployee = employeeRepository.save(employee);

        // Then
        assertThat(savedEmployee).isNotNull();

    }

    @DisplayName("Test para eliminar un empleado")
    @Test
    void deleteEmployee() {
        // Given
        Integer employeeId = 1;
        willDoNothing().given(employeeRepository).deleteById(employeeId);

        // When
        employeeRepository.deleteById(employeeId);

        // Then
        verify(employeeRepository,times(1)).deleteById(employeeId);
    }

    @DisplayName("test del generador de correos")
    @Test
    void testEmailGenerator() {
        // Given
        Employee employee01 = (Employee) employee01();

        // When
        EmailGenerator emailGenerator = new EmailGenerator();
        employee01.generateAndSetEmail(emailGenerator);
        employeeService.save(employee01);

        // Then
        assertThat(employee01.getEmail()).isEqualTo("juan.perez@company.com.co");

    }
}