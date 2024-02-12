package com.humantalent.application.service.implementations;

import com.humantalent.adapters.repositories.PersonRepository;
import com.humantalent.application.service.contracts.PersonService;
import com.humantalent.domain.model.person.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public class PersonServiceImpl extends GenericImpl<Person, PersonRepository> implements PersonService {

    public PersonServiceImpl(PersonRepository repository) {
        super(repository);
    }
    @Override
    @Transactional(readOnly = true)
    public Iterable<Person> findByFirstName(String firstName) {
        return repository.findByFirstName(firstName);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Person> findByOtherNames(String otherNames) {
        return repository.findByOtherNames(otherNames);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findByFirstLastName(String firstLastName) {
        return repository.findByFirstLastName(firstLastName);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findBySecondLastName(String secondLastName) {
        return repository.findBySecondLastName(secondLastName);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Person> findByIdType(String idType) {
        return repository.findByIdType(idType);
    }

    @Override
    public Optional<Person> findByIdNumber(String idNumber) {
        return repository.findByIdNumber(idNumber);
    }

    @Override
    public Iterable<Person> findByCountry(String country) {
        return repository.findByCountry(country);
    }
}
