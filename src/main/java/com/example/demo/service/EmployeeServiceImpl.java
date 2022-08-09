package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.IEmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

  private final IEmployeeRepository employeeRepository;

  public EmployeeServiceImpl(IEmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public Employee saveEmployee(Employee employee) {
    return employeeRepository.save(employee);
  }

  @Override
  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  @Override
  public Employee getEmployeeById(long id) {
    Optional<Employee> employee = employeeRepository.findById(id);
    if (employee.isPresent()) {
      return employee.get();
    } else {
      throw new ResourceNotFoundException("Employee", "Id", id);
    }
  }

  @Override
  public Employee updateEmployee(Employee employee, long id) {
    Employee existingEmployee = employeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
    existingEmployee.setFirstName(employee.getFirstName());
    existingEmployee.setLastName(employee.getLastName());
    existingEmployee.setEmail(employee.getEmail());
    return employeeRepository.save(existingEmployee);
  }

  @Override
  public void deleteEmployee(long id) {
    employeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
    employeeRepository.deleteById(id);
  }
}
