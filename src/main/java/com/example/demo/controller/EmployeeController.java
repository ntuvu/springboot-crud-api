package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  private final EmployeeServiceImpl employeeService;

  public EmployeeController(EmployeeServiceImpl employeeService) {
    this.employeeService = employeeService;
  }

  @PostMapping()
  public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
    return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
  }

  @GetMapping()
  public List<Employee> getAllEmployees() {
    return employeeService.getAllEmployees();
  }

  @GetMapping("{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
    return new ResponseEntity<Employee>(employeeService.getEmployeeById(id), HttpStatus.OK);
  }

  @PatchMapping("{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
    return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
    employeeService.deleteEmployee(id);
    return new ResponseEntity<String>("Employee deleted success", HttpStatus.OK);
  }
}
