package com.humantalent.application.service.implementations;

import com.humantalent.adapters.repositories.EmployeeRepository;
import com.humantalent.application.service.contracts.EmployeeService;
import com.humantalent.domain.model.employee.Employee;
import com.humantalent.domain.model.person.Person;
import com.humantalent.domain.utils.EmailGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeServiceImpl extends PersonServiceImpl implements EmployeeService {

    private final EmailGenerator emailGenerator;
    public EmployeeServiceImpl(@Qualifier("employeeRepository") EmployeeRepository repository,
                               EmailGenerator emailGenerator) {
        super(repository);
        this.emailGenerator = emailGenerator;
    }


    @Transactional(readOnly = true)
    public Boolean existsByEmail(String email) {
        return ((EmployeeRepository)repository).existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Person> findByState(Boolean state) {
        return ((EmployeeRepository)repository).findByState(state);
    }

    @Override
    public Person processEmployeeAndGenerateEmail(Person employee) {
        if (employee != null) {
            Employee employeeEntity = (Employee) employee;
            if (employeeEntity.getEmail() == null || employeeEntity.getEmail().isEmpty()) {
                employeeEntity.generateAndSetEmail(emailGenerator);
            }
        }
        return super.save((Employee) employee);
    }

    @Override
    public Page<Employee> getEmployeePagination(Integer pageNumber, Integer pageSize, String sort) {
        Pageable pageable = null;
        if(sort != null) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        return ((EmployeeRepository)repository).findAll(pageable).map(employee -> (Employee) employee);
    }

    @Override
    public Page<Employee> getEmployeePaginationByEmail(Integer pageNumber, Integer pageSize, String sort){
        Pageable pageable;
        if (sort != null) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return ((EmployeeRepository)repository).orderByEmail(pageable).map(employee -> (Employee) employee);
    }

    @Override
    public Page<Employee> getEmployeePaginationByWorkArea(Integer pageNumber, Integer pageSize, String sort) {
        Pageable pageable;
        if (sort != null) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return ((EmployeeRepository)repository).orderByWorkArea(pageable);
    }

    @Override
    public Page<Employee> getEmployeePaginationByState(Integer pageNumber, Integer pageSize, String sort) {
        Pageable pageable;
        if (sort != null) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return ((EmployeeRepository)repository).orderByState(pageable);
    }

    @Override
    public Page<Employee> getEmployeePaginationByRegDateTime(Integer pageNumber, Integer pageSize, String sort) {
        Pageable pageable;
        if (sort != null) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return ((EmployeeRepository)repository).orderByRegDateTime(pageable);
    }

    @Override
    public Page<Employee> getEmployeePaginationByEntryDate(Integer pageNumber, Integer pageSize, String sort) {
        Pageable pageable;
        if (sort != null) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return ((EmployeeRepository)repository).orderByEntryDate(pageable);
    }

}
