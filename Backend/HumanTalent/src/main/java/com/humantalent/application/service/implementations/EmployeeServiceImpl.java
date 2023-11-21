package com.humantalent.application.service.implementations;

import com.humantalent.adapters.repositories.EmployeeRepository;
import com.humantalent.adapters.repositories.PersonRepository;
import com.humantalent.application.service.EmployeeService;
import com.humantalent.domain.model.person.Person;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployeeServiceImpl extends PersonServiceImpl implements EmployeeService {

    public EmployeeServiceImpl(@Qualifier("employeeRepository")PersonRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findByEmail(String email) {
        return ((EmployeeRepository)repository).findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Person> findByState(Boolean state) {
        return ((EmployeeRepository)repository).findByState(state);
    }
}
