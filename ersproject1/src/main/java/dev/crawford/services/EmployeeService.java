package dev.crawford.services;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
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

    // Login through JSON & Form-Encoded
    public boolean login(Context ctx){
        Employee newEmployee;
        try {
            newEmployee = obj.readValue(ctx.body(), Employee.class);
            if(getEmployeeByEmail(newEmployee.getEmail()) != null) {
                Employee dbEmployee = getEmployeeByEmail(newEmployee.getEmail());
                if(newEmployee.getPassword().equals(dbEmployee.getPassword())){
                    ctx.cookieStore().set("user", newEmployee.getEmail());
                    ctx.cookieStore().set("role", EmployeeRole.valueOf(dbEmployee.getRole().toString()));
                    return true;
                }
            } else {
                ctx.status(401);
                return false;
            }
        } catch (Exception e) {
            newEmployee = convertFormEmployee(ctx);
            if(getEmployeeByEmail(newEmployee.getEmail()) != null) {
                Employee dbEmployee = getEmployeeByEmail(newEmployee.getEmail());
                if(newEmployee.getPassword().equals(dbEmployee.getPassword())){
                    ctx.cookieStore().set("user", newEmployee.getEmail());
                    ctx.cookieStore().set("role", EmployeeRole.valueOf(dbEmployee.getRole().toString()));
                    return true;
                }
            } else {
                ctx.status(401);
                return false;
            }
        }
        ctx.status(401);
        return false;
    }

    // Create Employee through JSON/Object Mapper
    public void createEmployee(Context ctx) {
        Employee newEmployee;
        try {
            newEmployee = obj.readValue(ctx.body(), Employee.class);
            if(getEmployeeByEmail(newEmployee.getEmail()) == null) {
                employeeRepository.create(newEmployee);
                ctx.status(201);
            }
        } catch (Exception e) {
            newEmployee = convertFormEmployee(ctx);
            if(getEmployeeByEmail(newEmployee.getEmail()) == null) {
                newEmployee.setId(0);
                employeeRepository.create(newEmployee);
                ctx.status(201);
            }
        }
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

    public List<Employee> getAllEmployees(Context ctx) {
        if(ctx.cookieStore().get("role").equals("EMPLOYEE")){
            List<Employee> empArr = new ArrayList<>();
            empArr.add(employeeRepository.getEmployeeByEmail(ctx.cookieStore().get("user")));
            return empArr;
        } else {
            return employeeRepository.getAllEmployees();
        }
    }

    public Employee getEmployeeByEmail(String email) {
        if(employeeRepository.getEmployeeByEmail(email) != null){
            return employeeRepository.getEmployeeByEmail(email);
        } else {
            return null;
        }
    }

    public void updateEmployee(String email, Context ctx){
        if(employeeRepository.getEmployeeByEmail(email) != null){
            Employee newEmployee;
            try {
                newEmployee = obj.readValue(ctx.body(), Employee.class);
                newEmployee.setEmail(email);
                employeeRepository.updateEmployee(newEmployee);
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            ctx.status(404);
        }
    }

    // DELETE ALL ------ NOT FOR PRODUCTION!!!
    public void deleteAll() {
        employeeRepository.deleteAll();
    }
}
