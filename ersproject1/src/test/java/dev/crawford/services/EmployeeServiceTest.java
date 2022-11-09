package dev.crawford.services;



import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.crawford.models.Employee;
import dev.crawford.models.EmployeeRole;
import dev.crawford.repositories.EmployeeDAOJDBC;

@TestMethodOrder(OrderAnnotation.class)
public class EmployeeServiceTest {

    Employee newEmployee = new Employee(
        0, 
        "John", 
        "Smith", 
        "email5@email.com", 
        "pass", 
        EmployeeRole.MANAGER
    );
    EmployeeService employeeService = new EmployeeService(new EmployeeDAOJDBC());

    @Test
    @Order(1)
    public void testCreateEmployee() {
        assertTrue(employeeService.createEmployee(newEmployee, "JSON"));
    }

    @Test
    @Order(2)
    public void testLogin() {
        assertTrue(employeeService.login(newEmployee));
    }

    @Test
    @Order(3)
    public void testGetAllEmployees() {
        assertEquals(newEmployee.getEmail(), employeeService.getAllEmployees("email5@email.com", "EMPLOYEE").get(0).getEmail());
        assertEquals(newEmployee.getFirstName(), employeeService.getAllEmployees("email5@email.com", "EMPLOYEE").get(0).getFirstName());
        assertEquals(newEmployee.getLastName(), employeeService.getAllEmployees("email5@email.com", "EMPLOYEE").get(0).getLastName());
        assertEquals(newEmployee.getRole(), employeeService.getAllEmployees("email5@email.com", "EMPLOYEE").get(0).getRole());
    }

    @Test
    @Order(4)
    public void testGetEmployeeByEmail() {
        assertEquals(newEmployee.getEmail(), employeeService.getEmployeeByEmail("email5@email.com").getEmail());
        assertEquals(newEmployee.getFirstName(), employeeService.getEmployeeByEmail("email5@email.com").getFirstName());
        assertEquals(newEmployee.getLastName(), employeeService.getEmployeeByEmail("email5@email.com").getLastName());
        assertEquals(newEmployee.getRole(), employeeService.getEmployeeByEmail("email5@email.com").getRole());
    }

    @Test
    @Order(5)
    public void testUpdateEmployee() {
        newEmployee.setFirstName("William");
        newEmployee.setLastName("Wallace");
        newEmployee.setRole(EmployeeRole.EMPLOYEE);
        assertNotEquals(
            newEmployee.getRole(), 
            employeeService.getEmployeeByEmail("email5@email.com").getRole()
        );
        employeeService.updateEmployee(newEmployee, "email5@email.com");
        assertEquals(newEmployee.getEmail(), employeeService.getEmployeeByEmail("email5@email.com").getEmail());
        assertEquals(newEmployee.getFirstName(), employeeService.getEmployeeByEmail("email5@email.com").getFirstName());
        assertEquals(newEmployee.getLastName(), employeeService.getEmployeeByEmail("email5@email.com").getLastName());
        assertEquals(newEmployee.getRole(), employeeService.getEmployeeByEmail("email5@email.com").getRole());
    }

    @Test
    @Order(6)
    public void deleteAllEmployees(){
        employeeService.deleteAll();
        assertArrayEquals(Collections.emptyList().toArray(), employeeService.getAllEmployees("null", "MANAGER").toArray());
    }
}
