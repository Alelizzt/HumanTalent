package com.humantalent.adapters.repositories;

import com.humantalent.domain.model.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@NoRepositoryBean
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("SELECT p FROM Person p WHERE p.name.firstName like %:firstName%")
    Iterable<Person> findByFirstName(@Param("firstName") String firstName);

    @Query("SELECT p FROM Person p WHERE p.name.otherNames like %:otherNames%")
    Iterable<Person> findByOtherNames(@Param("otherNames") String otherNames);

    @Query("SELECT p FROM Person p WHERE p.name.firstLastName like %:firstLastName%")
    Optional<Person> findByFirstLastName(@Param("firstLastName") String firstLastName);

    @Query("SELECT p FROM Person p WHERE p.name.secondLastName like %:secondLastName%")
    Optional<Person> findBySecondLastName(@Param("secondLastName") String secondLastName);

    @Query("SELECT p FROM Person p WHERE p.idType = :idType")
    Iterable<Person> findByIdType(@Param("idType") String idType);

    @Query("SELECT p FROM Person p WHERE p.idNumber = :idNumber")
    Optional<Person> findByIdNumber(@Param("idNumber") String idNumber);

    @Query("SELECT p FROM Person p WHERE :country IN(p.country)")
    Iterable<Person> findByCountry(@Param("country") String country);

}
