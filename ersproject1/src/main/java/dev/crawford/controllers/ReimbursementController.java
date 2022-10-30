package dev.crawford.controllers;

import dev.crawford.services.ReimbursementService;
import io.javalin.http.Handler;

public class ReimbursementController {

    private static ReimbursementService reimbursementService = new ReimbursementService();
    
    public static final Handler createReimbursement = ctx -> {
        reimbursementService.createReimbursement(ctx);
    };

    public static final Handler getAllReimbursements = ctx -> {
        ctx.json(reimbursementService.getAllReimbursements());
    };

    public static final Handler getReimbursementById = ctx -> {
        ctx.json(reimbursementService.getReimbursementById(ctx.pathParam("id")));
    };

    public static final Handler getReimbursementByAuthor = ctx -> {
        ctx.json(reimbursementService.getReimbursementByAuthor(ctx.pathParam("author")));
    };

    public static final Handler getReimbursementByType = ctx -> {
        ctx.json(reimbursementService.getReimbursementByType(ctx.pathParam("type")));
    };

    public static final Handler getReimbursementByStatus = ctx -> {
        ctx.json(reimbursementService.getReimbursementByStatus(ctx.pathParam("status")));
    };

    public static final Handler updateReimbursement = ctx -> {
        reimbursementService.updateReimbursement(ctx.pathParam("id"), ctx);
    };
}
