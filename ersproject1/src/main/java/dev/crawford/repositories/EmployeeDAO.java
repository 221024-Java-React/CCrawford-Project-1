package dev.crawford.repositories;

import java.util.List;

import dev.crawford.models.Employee;


public interface EmployeeDAO {
    
    public void create(Employee userToBeRegistered);
    public Employee getEmployeeByEmail(String email);
    public List<Employee> getAllEmployees();
    public void updateEmployee(Employee updateEmployee);
    public void deleteAll();
}
