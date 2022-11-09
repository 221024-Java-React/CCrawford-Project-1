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
import dev.crawford.models.Reimbursement;
import dev.crawford.models.ReimbursementStatus;
import dev.crawford.models.ReimbursementType;
import dev.crawford.repositories.EmployeeDAOJDBC;
import dev.crawford.repositories.ReimbursementDAOJDBC;

@TestMethodOrder(OrderAnnotation.class)
public class ReimbursementServiceTest {

    Reimbursement newReimbursement = new Reimbursement(
            0,
            "email5@email.com",
            null,
            ReimbursementStatus.PENDING,
            14.15,
            "Test Reimbursement",
            ReimbursementType.TRAVEL);
    ReimbursementService reimbursementService = new ReimbursementService(new ReimbursementDAOJDBC());

    Employee newEmployee = new Employee(
            0,
            "John",
            "Smith",
            "email5@email.com",
            "pass",
            EmployeeRole.MANAGER);
    EmployeeService employeeService = new EmployeeService(new EmployeeDAOJDBC());

    @Test
    @Order(1)
    void testCreateReimbursement() {
        assertTrue(employeeService.createEmployee(newEmployee, "JSON"));
        reimbursementService.createReimbursement(newReimbursement);
    }

    @Test
    @Order(2)
    void testGetAllReimbursements() {
        assertEquals(newReimbursement.getAmount(), reimbursementService.getAllReimbursements().get(0).getAmount());
        assertEquals(newReimbursement.getType(), reimbursementService.getAllReimbursements().get(0).getType());
        assertEquals(newReimbursement.getDescription(),
                reimbursementService.getAllReimbursements().get(0).getDescription());
        assertEquals(newReimbursement.getStatus(), reimbursementService.getAllReimbursements().get(0).getStatus());
    }

    @Test
    @Order(3)
    void testUpdateReimbursement() {
        String idCheck = String.valueOf(reimbursementService.getAllReimbursements().get(0).getId());
        newReimbursement.setAmount(15.15);
        ;
        newReimbursement.setDescription("New Description");
        ;
        newReimbursement.setStatus(ReimbursementStatus.APPROVED);
        assertNotEquals(
                newReimbursement.getStatus(),
                reimbursementService.getAllReimbursements().get(0).getStatus());
        reimbursementService.updateReimbursement(idCheck, newReimbursement);
        assertEquals(newReimbursement.getAmount(), reimbursementService.getAllReimbursements().get(0).getAmount());
        assertEquals(newReimbursement.getType(), reimbursementService.getAllReimbursements().get(0).getType());
        assertEquals(newReimbursement.getDescription(),
                reimbursementService.getAllReimbursements().get(0).getDescription());
        assertEquals(newReimbursement.getStatus(), reimbursementService.getAllReimbursements().get(0).getStatus());
    }

    @Test
    @Order(4)
    public void deleteAllReimbursements() {
        reimbursementService.deleteAll();
        employeeService.deleteAll();
        assertArrayEquals(Collections.emptyList().toArray(), reimbursementService.getAllReimbursements().toArray());
    }
}
