package com.humantalent.domain.mapper;

import com.humantalent.domain.dto.EmployeeDto;
import com.humantalent.domain.model.employee.Employee;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;

@MapperConfig
public interface EmployeeMapperConfig {

    @InheritConfiguration(name = "mapPerson")
    void mapEmployee(Employee employee, @MappingTarget EmployeeDto employeeDto);
}
