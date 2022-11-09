package dev.crawford.repositories;

import java.util.List;

import dev.crawford.models.Reimbursement;

public interface ReimbursementDAO {
    
    public void createReimbursement(Reimbursement newReimbursement);
    public List<Reimbursement> getAllReimbursements();
    public Reimbursement getReimbursementById(String id);
    public List<Reimbursement> getReimbursementByAuthor(String author);
    public List<Reimbursement> getReimbursementByType(String type);
    public List<Reimbursement> getReimbursementByStatus(String status);
    public void updateReimbursement(Reimbursement updateReimbursement);
    public void deleteAll();
}
