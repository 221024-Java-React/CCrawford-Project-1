package dev.crawford.controllers;


import dev.crawford.services.EmployeeService;
import io.javalin.http.Handler;

public final class EmployeeController {
    
    private static EmployeeService employeeService = new EmployeeService();

    
    public static final Handler hello = ctx -> {
        ctx.redirect("/index.html");
    };

    public static final Handler before = ctx -> {
        System.out.println("Before all requests");
    };

    public static final Handler login = ctx -> {
        if(employeeService.login(ctx)){
            ctx.html("Login Success");
        } else {
            ctx.html("Login Failed");
        }
    };

    // Create Employee Using JSON or Form-Encoded
    public static final Handler createEmployee = ctx -> {
        employeeService.createEmployee(ctx);
    };

    // Get all Employees
    public static final Handler getAllEmployees = ctx -> {
        ctx.json(employeeService.getAllEmployees());
    };

    //Get Employee by Unique value of Email
    public static final Handler getEmployeeByEmail = ctx -> {
        ctx.json(employeeService.getEmployeeByEmail(ctx.pathParam("email")));
    };

    // Put Update Employee
    public static final Handler updateEmployee = ctx -> {
        employeeService.updateEmployee(ctx.pathParam("email"), ctx);
    };

    // DELETE ALL ------ NOT FOR PRODUCTION!!!
    public static final Handler deleteAllEmployees = ctx -> {
        employeeService.deleteAll();
    };

}
