package com.humantalent.domain.mapper;

import com.humantalent.domain.dto.EmployeeDto;
import com.humantalent.domain.model.employee.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", config = EmployeeMapperConfig.class)
public abstract class EmployeeMapper {
    public abstract EmployeeDto mapEmployee(Employee employee);
    public abstract Employee mapEmployee(EmployeeDto employee);
    public abstract List<EmployeeDto> mapEmployee(List<Employee> employee);
}
