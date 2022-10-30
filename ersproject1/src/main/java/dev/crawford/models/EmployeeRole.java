package dev.crawford.models;

import io.javalin.security.RouteRole;

public enum EmployeeRole implements RouteRole{
    EMPLOYEE, MANAGER;
}
