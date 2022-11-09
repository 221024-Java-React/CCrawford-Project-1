package dev.crawford.services;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.crawford.models.Employee;
import dev.crawford.models.EmployeeRole;
import dev.crawford.repositories.EmployeeDAO;
import io.javalin.http.Context;

public class EmployeeService {
    
    private EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    // Login through JSON & Form-Encoded
    public boolean login(Employee employee){
        if(getEmployeeByEmail(employee.getEmail()) != null) {
            Employee dbEmployee = getEmployeeByEmail(employee.getEmail());
            if(employee.getPassword().equals(dbEmployee.getPassword())){
                return true;
            }
        }
        return false;
    }

    // Create Employee through JSON/Object Mapper
    public boolean createEmployee(Employee employee, String type) {
        if(type.equals("JSON")){
            if(getEmployeeByEmail(employee.getEmail()) == null) {
                employeeDAO.create(employee);
                return true;
            }
        } else {
            if(getEmployeeByEmail(employee.getEmail()) == null) {
                employee.setId(0);
                employeeDAO.create(employee);
                return true;
            }
        }
        return false;
    }

    public List<Employee> getAllEmployees(String user, String role) {
        try {
            if(role.equals("EMPLOYEE")){
            List<Employee> empArr = new ArrayList<>();
            empArr.add(employeeDAO.getEmployeeByEmail(user));
            return empArr;
        } else {
            return employeeDAO.getAllEmployees();
        }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Employee getEmployeeByEmail(String email) {
        if(employeeDAO.getEmployeeByEmail(email) != null){
            return employeeDAO.getEmployeeByEmail(email);
        } else {
            return null;
        }
    }

    public void updateEmployee(Employee employee, String email){
        if(employeeDAO.getEmployeeByEmail(email) != null){
            employee.setEmail(email);
            employeeDAO.updateEmployee(employee);
        }
    }

    // DELETE ALL ------ NOT FOR PRODUCTION!!!
    public void deleteAll() {
        employeeDAO.deleteAll();
    }

    // Convert Form-Encoded to Employee Object
    public Employee convertFormEmployee(Context ctx){
        String line = ctx.body();
        Employee newEmployee = new Employee();

        String[] pairs = line.split("\\&");
        for (int i = 0; i < pairs.length; i++) {
            String[] fields = pairs[i].split("=");
            String name;
            try {
                name = URLDecoder.decode(fields[0], "UTF-8");
                String value = URLDecoder.decode(fields[1], "UTF-8");
                switch(name) {
                    case "firstName": 
                        newEmployee.setFirstName(value);
                        break;
                    case "lastName": 
                        newEmployee.setLastName(value);
                        break;
                    case "email": 
                        newEmployee.setEmail(value);
                        break;
                    case "password": 
                        newEmployee.setPassword(value);
                        break;
                    case "role": 
                        newEmployee.setRole(EmployeeRole.valueOf(value));
                        break;
                    default:
                        break;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return newEmployee;
    }
}
