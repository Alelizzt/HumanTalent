package com.humantalent.domain.mapper;

import com.humantalent.domain.dto.EmployeeDto;
import com.humantalent.domain.model.employee.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EmployeeMapperMS {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "country", target = "country"),
            @Mapping(source = "idType", target = "idType"),
            @Mapping(source = "idNumber", target = "idNumber"),
            @Mapping(source = "workArea", target = "workArea"),
            @Mapping(source = "entryDate", target = "entryDate"),
            @Mapping(source = "email", target = "email"),
    })
    EmployeeDto mapEmployee(Employee employee);
}
