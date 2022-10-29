package dev.crawford.services;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.crawford.models.Employee;
import dev.crawford.models.EmployeeRole;
import dev.crawford.repositories.EmployeeRepository;
import dev.crawford.util.LoggingUtil;
import io.javalin.http.Context;

public class EmployeeService {
    
    private EmployeeRepository employeeRepository;
    private ObjectMapper obj;

    public EmployeeService(){
        employeeRepository = new EmployeeRepository();
        obj = new ObjectMapper();
    }

    public void logging(){
        LoggingUtil.getLogger().warn("This is a warning");
    }

    // Create Employee through JSON/Object Mapper
    public void createEmployee(Context ctx) {
        Employee newEmployee;
        try {
            newEmployee = obj.readValue(ctx.body(), Employee.class);
            System.out.println(newEmployee.toString());
            if(getEmployeeByEmail(newEmployee.getEmail()) == null) {
                employeeRepository.create(newEmployee);
                System.out.println("Employee Created");
            } else {
                System.out.println("Email already taken");
            }
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    // Create Employee Using Form-Encoded
    public void createFormEmployee(Context ctx) throws UnsupportedEncodingException{
        String line = ctx.body();
        Employee newEmployee = new Employee();

        String[] pairs = line.split("\\&");
        for (int i = 0; i < pairs.length; i++) {
            String[] fields = pairs[i].split("=");
            String name = URLDecoder.decode(fields[0], "UTF-8");
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
                case "chooseRole": 
                    newEmployee.setRole(EmployeeRole.valueOf(value));
                    break;
                default:
                    break;
            }
        }
        if(getEmployeeByEmail(newEmployee.getEmail()) == null) {
            newEmployee.setId(0);
            System.out.println(newEmployee.toString());
            employeeRepository.create(newEmployee);
            System.out.println("Employee Created");
        } else {
            System.out.println("Email already taken");
        }
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Employee getEmployeeByEmail(String email) {
        if(employeeRepository.getByEmail(email) != null){
            return employeeRepository.getByEmail(email);
        } else {
            return null;
        }
    }

    // DELETE ALL ------ NOT FOR PRODUCTION!!!
    public void deleteAll() {
        System.out.println("All Employees Deleted");
        employeeRepository.deleteAll();
    }

    
}
