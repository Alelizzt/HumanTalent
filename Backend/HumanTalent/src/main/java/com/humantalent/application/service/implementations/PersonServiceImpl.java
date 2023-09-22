package com.humantalent.application.service.implementations;

import com.humantalent.adapters.repositories.PersonRepository;
import com.humantalent.application.service.PersonService;
import com.humantalent.domain.model.person.Person;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Person> findByFirstName(String firstName) {
        return personRepository.findByFirstName(firstName);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Person> findByOtherNames(String otherNames) {
        return personRepository.findByOtherNames(otherNames);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findByFirstLastName(String firstLastName) {
        return personRepository.findByFirstLastName(firstLastName);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findBySecondLastName(String secondLastName) {
        return personRepository.findBySecondLastName(secondLastName);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Person> findByIdType(String idType) {
        return personRepository.findByIdType(idType);
    }

    @Override
    public Optional<Person> findByIdNumber(String idNumber) {
        return personRepository.findByIdNumber(idNumber);
    }

    @Override
    public Iterable<Person> findByCountry(String country) {
        return personRepository.findByCountry(country);
    }
}
