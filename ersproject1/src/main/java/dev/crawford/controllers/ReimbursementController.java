package dev.crawford.controllers;

import dev.crawford.services.ReimbursementService;
import io.javalin.http.Handler;

public class ReimbursementController {

    private static ReimbursementService reimbursementService = new ReimbursementService();
    
    public static final Handler createReimbursement = ctx -> {
        reimbursementService.createReimbursement(ctx);
    };

    public static final Handler getAllReimbursements = ctx -> {
        reimbursementService.getAllReimbursements(ctx);
    };

    public static final Handler getReimbursementByAuthor = ctx -> {
        reimbursementService.getReimbursementByAuthor(ctx);
    };

    public static final Handler updateReimbursement = ctx -> {
        reimbursementService.createReimbursement(ctx);
    };
}
