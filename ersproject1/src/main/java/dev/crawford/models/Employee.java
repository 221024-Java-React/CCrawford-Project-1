package dev.crawford.models;

import java.util.Arrays;

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private EmployeeRole role;
    private Reimbursement[] reimbursements;

    public Employee() {
        
    }

    public Employee(int id, String firstName, String lastName, String email, String password, EmployeeRole role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public Reimbursement[] getReimbursements() {
        return reimbursements;
    }

    public void setReimbursements(Reimbursement[] reimbursements) {
        this.reimbursements = reimbursements;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", password=" + password + ", role=" + role + ", reimbursements=" + Arrays.toString(reimbursements)
                + "]";
    }

    
}