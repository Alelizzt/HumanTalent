package com.humantalent.application.service.implementations;

import com.humantalent.adapters.repositories.EmployeeRepository;
import com.humantalent.adapters.repositories.PersonRepository;
import com.humantalent.application.service.contracts.EmployeeService;
import com.humantalent.domain.model.employee.Employee;
import com.humantalent.domain.model.person.Person;
import com.humantalent.domain.utils.EmailGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployeeServiceImpl extends PersonServiceImpl implements EmployeeService {

    private final EmailGenerator emailGenerator;
    public EmployeeServiceImpl(@Qualifier("employeeRepository")PersonRepository repository,
                               EmailGenerator emailGenerator) {
        super(repository);
        this.emailGenerator = emailGenerator;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findByEmail(String email) {
        return ((EmployeeRepository)repository).findByEmail(email);
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
}
