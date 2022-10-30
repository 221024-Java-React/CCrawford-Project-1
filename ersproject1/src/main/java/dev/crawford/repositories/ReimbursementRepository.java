package dev.crawford.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.crawford.models.Reimbursement;
import dev.crawford.models.ReimbursementStatus;
import dev.crawford.models.ReimbursementType;
import dev.crawford.util.ConnectionFactory;

public class ReimbursementRepository {

    static ConnectionFactory cu = ConnectionFactory.getInstance();

    public void createReimbursement(Reimbursement newReimbursement) {
        String sql = "insert into reimbursements (id, author, resolver, status, amount, description, type) values (default, ?, ?, ?, ?, ?, ?)";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newReimbursement.getAuthor());
            ps.setString(2, newReimbursement.getResolver());
            ps.setString(3, newReimbursement.getStatus().toString());
            ps.setDouble(4, newReimbursement.getAmount());
            ps.setString(5, newReimbursement.getDescription());
            ps.setString(6, newReimbursement.getType().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Reimbursement> getAllReimbursements() {
        List<Reimbursement> newReimbursementList = new ArrayList<>();
        String sql = "select * from reimbursements";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Reimbursement newReimbursement = new Reimbursement(
                    rs.getInt("id"),
                    rs.getString("author"),
                    rs.getString("resolver"),
                    ReimbursementStatus.valueOf(rs.getString("status")),
                    rs.getDouble("amount"),
                    rs.getString("description"),
                    ReimbursementType.valueOf(rs.getString("type"))
                );
                newReimbursementList.add(newReimbursement);
            }
            return newReimbursementList;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Reimbursement getReimbursementById(String id) {
        String sql = "select * from reimbursements where id = ?";
        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Reimbursement newReimbursement = new Reimbursement(
                    rs.getInt("id"),
                    rs.getString("author"),
                    rs.getString("resolver"),
                    ReimbursementStatus.valueOf(rs.getString("status")),
                    rs.getDouble("amount"),
                    rs.getString("description"),
                    ReimbursementType.valueOf(rs.getString("type"))
                );
                return newReimbursement;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reimbursement> getReimbursementByAuthor(String author) {
        List<Reimbursement> newReimbursementList = new ArrayList<>();
        String sql = "select * from reimbursements where author = ?";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, author.toLowerCase());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Reimbursement newReimbursement = new Reimbursement(
                    rs.getInt("id"),
                    rs.getString("author"),
                    rs.getString("resolver"),
                    ReimbursementStatus.valueOf(rs.getString("status")),
                    rs.getDouble("amount"),
                    rs.getString("description"),
                    ReimbursementType.valueOf(rs.getString("type"))
                );
                newReimbursementList.add(newReimbursement);
            }
            return newReimbursementList;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<Reimbursement> getReimbursementByType(String type) {
        List<Reimbursement> newReimbursementList = new ArrayList<>();
        String sql = "select * from reimbursements where type = ?";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, type.toUpperCase());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Reimbursement newReimbursement = new Reimbursement(
                    rs.getInt("id"),
                    rs.getString("author"),
                    rs.getString("resolver"),
                    ReimbursementStatus.valueOf(rs.getString("status")),
                    rs.getDouble("amount"),
                    rs.getString("description"),
                    ReimbursementType.valueOf(rs.getString("type"))
                );
                newReimbursementList.add(newReimbursement);
            }
            return newReimbursementList;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<Reimbursement> getReimbursementByStatus(String status) {
        List<Reimbursement> newReimbursementList = new ArrayList<>();
        String sql = "select * from reimbursements where status = ?";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status.toUpperCase());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Reimbursement newReimbursement = new Reimbursement(
                    rs.getInt("id"),
                    rs.getString("author"),
                    rs.getString("resolver"),
                    ReimbursementStatus.valueOf(rs.getString("status")),
                    rs.getDouble("amount"),
                    rs.getString("description"),
                    ReimbursementType.valueOf(rs.getString("type"))
                );
                newReimbursementList.add(newReimbursement);
            }
            return newReimbursementList;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public void updateReimbursement(Reimbursement updateReimbursement){
        String sql = "update reimbursements set author = ?, resolver = ?, status = ?, amount = ?, description = ?, type = ? where id = ?";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, updateReimbursement.getAuthor());
            ps.setString(2, updateReimbursement.getResolver());
            ps.setString(3, updateReimbursement.getStatus().toString());
            ps.setDouble(4, updateReimbursement.getAmount());
            ps.setString(5, updateReimbursement.getDescription());
            ps.setString(6, updateReimbursement.getType().toString());
            ps.setInt(7, updateReimbursement.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
