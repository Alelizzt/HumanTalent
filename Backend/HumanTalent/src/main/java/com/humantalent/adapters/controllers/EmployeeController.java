package com.humantalent.adapters.controllers;

import com.humantalent.application.service.contracts.EmployeeService;
import com.humantalent.application.service.contracts.PersonService;
import com.humantalent.domain.model.employee.Employee;
import com.humantalent.domain.model.person.Person;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 4800, allowCredentials = "false")//http://localhost:4200
@RequestMapping("/talent")
public class EmployeeController extends PersonController {

    final Integer pageSize = 10;

    public EmployeeController(@Qualifier("employeeServiceImpl")EmployeeService service) {
        super(service, "employee");
    }

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getEmployees( @RequestParam(defaultValue = "0") int page) {
        Map<String, Object> response = new HashMap<>();

        Page<Employee> employees = ((EmployeeService)service).getEmployeePagination(page, pageSize, null);

        if (employees.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("data", String.format("Can't find %ss", name_entity));
            return ResponseEntity.badRequest().body(response);
        }

        response.put("success", Boolean.TRUE);
        response.put("data", employees);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/employees/{sort}")
    public ResponseEntity<?> getAllEmployees(
            @PathVariable String sort,
            @RequestParam(defaultValue = "0") int page) {
        Map<String, Object> message = new HashMap<>();
        try {
            List<Order> orders = new ArrayList<Order>();

            Page<Employee> employees = ((EmployeeService)service).getEmployeePagination(page, pageSize, sort);

            if (employees.isEmpty()) {
                message.put("success", Boolean.FALSE);
                message.put("data", String.format("Can't find %ss", name_entity));
                return ResponseEntity.badRequest().body(message);
            }

            message.put("success", Boolean.TRUE);
            message.put("data", employees);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("success", Boolean.FALSE);
            message.put("data", e.getMessage());
            return ResponseEntity.internalServerError().body(message);
        }
    }

    @GetMapping("/employees/email")
    public ResponseEntity<Map<String, Object>> orderEmployeeByEmail(@RequestParam (defaultValue = "0") int page ) {

        Map<String, Object> message = new HashMap<>();

        try {
            Page<Employee> employeePaginationByEmail = ((EmployeeService) service).getEmployeePaginationByEmail(page, pageSize, null);

            if (employeePaginationByEmail.isEmpty()) {
                message.put("success", Boolean.FALSE);
                message.put("message", String.format("No employees with %s email", name_entity));
                return ResponseEntity.badRequest().body(message);
            }

            message.put("success", Boolean.TRUE);
            message.put("data", employeePaginationByEmail.getContent());
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("success", Boolean.FALSE);
            message.put("message", "Error fetching employees by email");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
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



    /*@PostMapping("/employee")
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

    @PutMapping("/{id}")
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
