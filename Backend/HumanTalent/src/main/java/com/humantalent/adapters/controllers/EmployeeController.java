package com.humantalent.adapters.controllers;

import com.humantalent.application.service.contracts.EmployeeService;
import com.humantalent.application.service.contracts.PersonService;
import com.humantalent.domain.model.employee.Employee;
import com.humantalent.domain.model.person.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 4800, allowCredentials = "false")//http://localhost:4200
@RequestMapping("/employees")
public class EmployeeController extends PersonController {

    public EmployeeController(@Qualifier("employeeServiceImpl")PersonService service) {
        super(service, "employee");
    }

    @GetMapping
    public ResponseEntity<?> getEmployees() {
        Map<String, Object> message = new HashMap<>();
        Stream<Person> persons = (super.getAll().stream());
        List<Person> employees = persons.filter(person -> person instanceof Employee).toList();

        if(employees.isEmpty()) {
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("Can't find %ss", name_entity));
            return ResponseEntity.badRequest().body(message);
        }

        message.put("success", Boolean.TRUE);
        message.put("data", employees);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/identification")
    public ResponseEntity<?> getEmployeeByIdentification(@RequestParam String identificationNum) {
        Map<String, Object> message = new HashMap<>();

        Optional<Person> oEmployee = ((EmployeeService) service).findByIdNumber(identificationNum);

        if (oEmployee.isEmpty()) {
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No employee with identification number %s", identificationNum));
            return ResponseEntity.badRequest().body(message);
        }

        message.put("success", Boolean.TRUE);
        message.put("data", oEmployee.get());
        return ResponseEntity.ok(message);
    }


    @GetMapping("/email")
    public ResponseEntity<?> getEmployeeByEmail(@RequestParam String email) {
        Map<String, Object> message = new HashMap<>();
        Optional<Person> oEmployees = ((EmployeeService) service).findByEmail(email);

        if(oEmployees.isEmpty()) {
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No employees with %s email", email));
            return ResponseEntity.badRequest().body(message);
        }


        message.put("success", Boolean.TRUE);
        message.put("data", oEmployees);
        return ResponseEntity.ok(message);
    }

    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee, BindingResult result) {
        Map<String, Object> message = new HashMap<>();
        if(result.hasErrors()){
            message.put("success", Boolean.FALSE);
            message.put("validations", super.getValidations(result));
            return ResponseEntity.badRequest().body(message);
        }

        Person savedEmployee = ((EmployeeService) service).processEmployeeAndGenerateEmail(employee);

        message.put("success", Boolean.TRUE);
        message.put("data", savedEmployee);

        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    
    @GetMapping("/state")
    public ResponseEntity<?> getEmployeeByState(@RequestParam Boolean state) {
        Map<String, Object> message = new HashMap<>();
        Iterable<Person> byState = ((EmployeeService) service).findByState(state);
        List<Person> oEmployees = (List<Person>) byState;

        if(oEmployees.isEmpty()) {
            message.put("success", Boolean.FALSE);
            message.put("message", String.format("No employees with %s state", state));
            return ResponseEntity.badRequest().body(message);
        }


        message.put("success", Boolean.TRUE);
        message.put("data", oEmployees);
        return ResponseEntity.ok(message);
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee, BindingResult result) {
        Map<String, Object> message = new HashMap<>();
        Person employeeUpdate = null;
        PersonDto personDto = super.find super. findPersonById(id);
        if(result.hasErrors()) {
            message.put("success", Boolean.FALSE);
            message.put("validations", super.getValidations(result));
            return ResponseEntity.badRequest().body(message);
        }



        employeeUpdate.setz(employee.getName());
        employeeUpdate.setCountry(employee.getCountry());
        employeeUpdate.setIdType(employee.getIdType());
        employeeUpdate.setId(employee.getId());
        employeeUpdate.setEmail(employee.getEmail());

        message.put("success", Boolean.TRUE);
        message.put("data", service.save(employeeUpdate));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
    }*/



}
