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
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> getEmployees( @RequestParam(defaultValue = "0") Integer page) {
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

    @GetMapping("/employees/id/{id}")
    public ResponseEntity<?> getEmployeeById( @PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        Optional<Person> employeeData = service.findById(id);

        if (employeeData.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("data", String.format("Can't find %ss", name_entity));
            return ResponseEntity.badRequest().body(response);
        }

        response.put("success", Boolean.TRUE);
        response.put("data", employeeData);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/employees/{sort}")
    public ResponseEntity<?> getAllEmployees(
            @PathVariable String sort,
            @RequestParam(defaultValue = "0") Integer page) {
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
                message.put("message", String.format("No employees %s with email", name_entity));
                return ResponseEntity.badRequest().body(message);
            }

            message.put("success", Boolean.TRUE);
            message.put("data", employeePaginationByEmail);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("success", Boolean.FALSE);
            message.put("message", "Error fetching employees by email");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @GetMapping("/employees/workArea")
    public ResponseEntity<Map<String, Object>> orderEmployeeByWorkArea(@RequestParam (defaultValue = "0") int page ) {
        Map<String, Object> message = new HashMap<>();
        try {
            Page<Employee> employeePaginationByWorkArea = ((EmployeeService) service).getEmployeePaginationByWorkArea(page, pageSize, null);

            if (employeePaginationByWorkArea.isEmpty()) {
                message.put("success", Boolean.FALSE);
                message.put("message", String.format("No employees %s with workArea", name_entity));
                return ResponseEntity.badRequest().body(message);
            }

            message.put("success", Boolean.TRUE);
            message.put("data", employeePaginationByWorkArea);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("success", Boolean.FALSE);
            message.put("message", "Error fetching employees by workArea");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @GetMapping("/employees/state")
    public ResponseEntity<Map<String, Object>> orderEmployeeByState(@RequestParam (defaultValue = "0") int page ) {
        Map<String, Object> message = new HashMap<>();
        try {
            Page<Employee> employeePaginationByState = ((EmployeeService) service).getEmployeePaginationByState(page, pageSize, null);

            if (employeePaginationByState.isEmpty()) {
                message.put("success", Boolean.FALSE);
                message.put("message", String.format("No employees %s with state", name_entity));
                return ResponseEntity.badRequest().body(message);
            }

            message.put("success", Boolean.TRUE);
            message.put("data", employeePaginationByState);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("success", Boolean.FALSE);
            message.put("message", "Error fetching employees by state: "+e.getCause());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @GetMapping("/employees/regDateTime")
    public ResponseEntity<Map<String, Object>> orderEmployeeByRegDateTime(@RequestParam (defaultValue = "0") int page ) {
        Map<String, Object> message = new HashMap<>();
        try {
            Page<Employee> employeePaginationByRegDateTime = ((EmployeeService) service).getEmployeePaginationByRegDateTime(page, pageSize, null);

            if (employeePaginationByRegDateTime.isEmpty()) {
                message.put("success", Boolean.FALSE);
                message.put("message", String.format("No employees %s with RegDateTime", name_entity));
                return ResponseEntity.badRequest().body(message);
            }

            message.put("success", Boolean.TRUE);
            message.put("data", employeePaginationByRegDateTime);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("success", Boolean.FALSE);
            message.put("message", "Error fetching employees by RegDateTime: "+e.getCause());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @GetMapping("/employees/entryDate")
    public ResponseEntity<Map<String, Object>> orderEmployeeByEntryDate(@RequestParam (defaultValue = "0") int page ) {
        Map<String, Object> message = new HashMap<>();
        try {
            Page<Employee> employeePaginationByEntryDate = ((EmployeeService) service).getEmployeePaginationByEntryDate(page, pageSize, null);

            if (employeePaginationByEntryDate.isEmpty()) {
                message.put("success", Boolean.FALSE);
                message.put("message", String.format("No employees %s with Entry Date", name_entity));
                return ResponseEntity.badRequest().body(message);
            }

            message.put("success", Boolean.TRUE);
            message.put("data", employeePaginationByEntryDate);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.put("success", Boolean.FALSE);
            message.put("message", "Error fetching employees by EntryDate: "+e.getCause());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @PostMapping("/employees")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        try {
            if(result.hasErrors()){
                response.put("success", Boolean.FALSE);
                response.put("validations", super.getValidations(result));
                return ResponseEntity.badRequest().body(response);
            }

            Person savedEmployee = ((EmployeeService) service).processEmployeeAndGenerateEmail(employee);

            response.put("success", Boolean.TRUE);
            response.put("data", savedEmployee);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("success", Boolean.FALSE);
            response.put("message", "Error creating employee: "+e.getCause());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Person> employeeData = service.findById(id);

            if(employeeData.isPresent() && employeeData.get() instanceof Employee _employee) {
                _employee.setFirstName(employee.getFirstName());
                _employee.setOtherNames(employee.getOtherNames());
                _employee.setFirstLastName(employee.getFirstLastName());
                _employee.setSecondLastName(employee.getSecondLastName());
                _employee.setCountry(employee.getCountry());
                _employee.setIdType(employee.getIdType());
                _employee.setIdentificationNum(employee.getIdentificationNum());

                _employee.setWorkArea(employee.getWorkArea());
                _employee.setEntryDate(employee.getEntryDate());

                Person savedEmployee = ((EmployeeService) service).processEmployeeAndGenerateEmail(_employee);

                response.put("success", Boolean.TRUE);
                response.put("data", savedEmployee);

                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response.put("success", Boolean.FALSE);
                response.put("message", "Employee not found with id: "+id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

        } catch (Exception e) {
            response.put("success", Boolean.FALSE);
            response.put("message", "Error creating employee: "+e.getCause());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Integer id) {
        try {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}