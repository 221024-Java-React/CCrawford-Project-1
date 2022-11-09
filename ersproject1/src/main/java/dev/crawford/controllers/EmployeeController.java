package dev.crawford.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;

import dev.crawford.models.Employee;
import dev.crawford.models.EmployeeRole;
import dev.crawford.services.EmployeeService;
import io.javalin.http.Handler;

public final class EmployeeController {
    
    private static EmployeeService employeeService = new EmployeeService();

    private static ObjectMapper obj = new ObjectMapper();

    public static final Handler hello = ctx -> ctx.redirect("/index.html");

    public static final Handler login = ctx -> {
        Employee newEmployee;
        try {
            newEmployee = obj.readValue(ctx.body(), Employee.class);
        } catch (Exception e) {
            newEmployee = employeeService.convertFormEmployee(ctx);
        }
        if(employeeService.login(newEmployee)){
            ctx.cookieStore().set("user", newEmployee.getEmail());
            Employee dbEmployee = employeeService.getEmployeeByEmail(newEmployee.getEmail());
            ctx.cookieStore().set("role", EmployeeRole.valueOf(dbEmployee.getRole().toString()));
            ctx.result("Login Success");
        } else {
            ctx.result("Login Failed");
        }
    };

    // Create Employee Using JSON or Form-Encoded
    public static final Handler createEmployee = ctx -> {
        Employee newEmployee;
        try {
            newEmployee = obj.readValue(ctx.body(), Employee.class);
            employeeService.createEmployee(newEmployee, "JSON");
        } catch (Exception e) {
            newEmployee = employeeService.convertFormEmployee(ctx);
            employeeService.createEmployee(newEmployee, "FORM-ENCODED");
        }
        ctx.status(201);
    };

    // Get all Employees
    public static final Handler getAllEmployees = ctx -> {
        String user = ctx.cookieStore().get("user").toString();
        String role = ctx.cookieStore().get("role").toString();
        ctx.json(employeeService.getAllEmployees(user, role));
    };
        

    //Get Employee by Unique value of Email
    public static final Handler getEmployeeByEmail = ctx -> ctx.json(employeeService.getEmployeeByEmail(ctx.pathParam("email")));

    // Put Update Employee
    public static final Handler updateEmployee = ctx -> {
        Employee newEmployee;
        String email = ctx.pathParam("email");
        try {
            newEmployee = obj.readValue(ctx.body(), Employee.class);
            employeeService.updateEmployee(newEmployee, email);
        } catch (Exception e) {
            newEmployee = employeeService.convertFormEmployee(ctx);
            employeeService.updateEmployee(newEmployee, email);
        }
    };

    // DELETE ALL ------ NOT FOR PRODUCTION!!!
    public static final Handler deleteAllEmployees = ctx -> employeeService.deleteAll();

}
 