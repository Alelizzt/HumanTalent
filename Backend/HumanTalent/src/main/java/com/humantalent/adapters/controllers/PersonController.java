package com.humantalent.adapters.controllers;

import com.humantalent.application.service.contracts.PersonService;
import com.humantalent.domain.model.employee.Employee;
import com.humantalent.domain.model.person.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PersonController extends GenericController<Person, PersonService>{

    public PersonController(PersonService service, String name_entity) {
        super(service, name_entity);
    }

    @GetMapping("/name")
    public ResponseEntity<?> findPersonByName(@RequestParam String firstName) {
        Map<String, Object> message = new HashMap<>();
        List<Person> oPerson = (List<Person>) service.findByFirstName(firstName);
        if(oPerson.isEmpty()){
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No persons found with %s name", firstName));
            return ResponseEntity.badRequest().body(message);
        }
        message.put("data", oPerson);
        message.put("success", Boolean.TRUE);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/other-names")
    public ResponseEntity<?> findByOtherNames(@RequestParam String otherNames) {
        Map<String, Object> message = new HashMap<>();
        List<Person> oPerson = (List<Person>) service.findByOtherNames(otherNames);
        if(oPerson.isEmpty()){
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No persons found with %s name", otherNames));
            return ResponseEntity.badRequest().body(message);
        }
        message.put("data", oPerson);
        message.put("success", Boolean.TRUE);
        return ResponseEntity.ok(message);
    }

    public ResponseEntity<?> findByFirstLastName(@RequestParam String firstLastName) {
        Map<String, Object> message = new HashMap<>();
        Optional<Person> oPerson = service.findByFirstLastName(firstLastName);
        if(oPerson.isEmpty()){
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No persons found with %s lastname, try with second lastname", firstLastName));
            return ResponseEntity.badRequest().body(message);
        }
        message.put("data", oPerson);
        message.put("success", Boolean.TRUE);
        return ResponseEntity.ok(message);
    }

    public ResponseEntity<?>  findBySecondLastName(@RequestParam String secondLastName) {
        Map<String, Object> message = new HashMap<>();
        Optional<Person> oPerson = service.findByFirstLastName(secondLastName);
        if(oPerson.isEmpty()){
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No persons found with %s second lastname", secondLastName));
            return ResponseEntity.badRequest().body(message);
        }
        message.put("data", oPerson);
        message.put("success", Boolean.TRUE);
        return ResponseEntity.ok(message);
    }
    public ResponseEntity<?> findByIdType(@RequestParam String idType) {
        Map<String, Object> message = new HashMap<>();
        List<Person> oPerson = (List<Person>) service.findByIdType(idType);
        if(oPerson.isEmpty()){
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No persons found with %s idType", idType));
            return ResponseEntity.badRequest().body(message);
        }
        message.put("data", oPerson);
        message.put("success", Boolean.TRUE);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdNumber(@RequestParam String idNumber) {
        Map<String, Object> message = new HashMap<>();
        Optional<Person> oPerson = service.findByIdNumber(idNumber);
        if(oPerson.isEmpty()){
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No persons found with %s id number", idNumber));
            return ResponseEntity.badRequest().body(message);
        }
        message.put("data", oPerson);
        message.put("success", Boolean.TRUE);
        return ResponseEntity.ok(message);
    }
    public ResponseEntity<?> findByCountry(@RequestParam String country) {
        Map<String, Object> message = new HashMap<>();
        List<Person> oPerson = (List<Person>)service.findByCountry(country);
        if(oPerson.isEmpty()){
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No persons found in %s", country));
            return ResponseEntity.badRequest().body(message);
        }
        message.put("data", oPerson);
        message.put("success", Boolean.TRUE);
        return ResponseEntity.ok(message);
    }
}
