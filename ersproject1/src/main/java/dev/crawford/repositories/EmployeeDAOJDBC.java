package dev.crawford.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.crawford.models.Employee;
import dev.crawford.models.EmployeeRole;
import dev.crawford.util.ConnectionFactory;

public class EmployeeDAOJDBC implements EmployeeDAO{
    
    static ConnectionFactory cu = ConnectionFactory.getInstance();
    
    // Create new Employee
    public void create(Employee userToBeRegistered) {
        String sql = "insert into employees (id, first_name, last_name, email, passwrd, role) values (default, ?, ?, ?, ?, ?)";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userToBeRegistered.getFirstName());
            ps.setString(2, userToBeRegistered.getLastName());
            ps.setString(3, userToBeRegistered.getEmail().toLowerCase());
            ps.setString(4, userToBeRegistered.getPassword());
            ps.setString(5, userToBeRegistered.getRole().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read Employee by Unique value of Email
    public Employee getEmployeeByEmail(String email) {
        String sql = "select * from employees where email = ?";
        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email.toLowerCase());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Employee newEmployee = new Employee(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("passwrd"),
                    EmployeeRole.valueOf(rs.getString("role"))
                );
                return newEmployee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Read all Employees
    public List<Employee> getAllEmployees() {
        List<Employee> newEmployeeList = new ArrayList<>();
        String sql = "select * from employees";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Employee newEmployee = new Employee(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("passwrd"),
                    EmployeeRole.valueOf(rs.getString("role"))
                );
                newEmployeeList.add(newEmployee);
            }
            return newEmployeeList;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    // Update Employee Information -- Does not allow changing of email
    public void updateEmployee(Employee updateEmployee){
        String sql = "update employees set first_name = ?, last_name = ?, email = ?, passwrd = ?, role = ? where email = ?";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, updateEmployee.getFirstName());
            ps.setString(2, updateEmployee.getLastName());
            ps.setString(3, updateEmployee.getEmail());
            ps.setString(4, updateEmployee.getPassword());
            ps.setString(5, updateEmployee.getRole().toString());
            ps.setString(6, updateEmployee.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE ALL ------ NOT FOR PRODUCTION!!!
    public void deleteAll() {
        String sql = "TRUNCATE TABLE employees CASCADE";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}