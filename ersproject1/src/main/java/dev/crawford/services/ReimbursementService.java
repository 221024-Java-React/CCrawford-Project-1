package dev.crawford.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.crawford.repositories.ReimbursementRepository;
import io.javalin.http.Context;

public class ReimbursementService {

    private ReimbursementRepository reimbursementRepository;
    private ObjectMapper obj;

    public ReimbursementService(){
        reimbursementRepository = new ReimbursementRepository();
        obj = new ObjectMapper();
    }

    public void createReimbursement(Context ctx) {
    }

    public void getAllReimbursements(Context ctx) {
    }

    public void getReimbursementByAuthor(Context ctx) {
    }
    
}
