package com.humantalent.application.service.implementations;

import com.humantalent.adapters.repositories.EmployeeRepository;
import com.humantalent.application.service.EmployeeService;
import com.humantalent.domain.model.person.Person;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Person> findByState(Boolean state) {
        return employeeRepository.findByState(state);
    }
}
