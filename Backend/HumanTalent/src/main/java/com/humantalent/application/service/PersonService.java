package com.humantalent.application.service;

import com.humantalent.domain.model.person.Person;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonService {
    Iterable<Person> findByFirstName(@Param("firstName") String firstName);
    Iterable<Person> findByOtherNames(@Param("otherNames") String otherNames);
    Optional<Person> findByFirstLastName(@Param("firstLastName") String firstLastName);

    Optional<Person> findBySecondLastName(@Param("secondLastName") String secondLastName);
    Iterable<Person> findByIdType(@Param("idType") String idType);
    Optional<Person> findByIdNumber(@Param("idNumber") String idNumber);
    Iterable<Person> findByCountry(@Param("country") String country);

}
