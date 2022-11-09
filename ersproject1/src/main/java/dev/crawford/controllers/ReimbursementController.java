package dev.crawford.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.crawford.models.Reimbursement;
import dev.crawford.services.ReimbursementService;
import io.javalin.http.Handler;

public class ReimbursementController {

    private ReimbursementService reimbursementService;

    private ObjectMapper obj;

    public ReimbursementController(ReimbursementService reimbursementService){
        this.reimbursementService = reimbursementService;
        obj = new ObjectMapper();
    }

    public final Handler createReimbursement = ctx -> {
        Reimbursement newReimbursement;
        try {
            newReimbursement = obj.readValue(ctx.body(), Reimbursement.class);
        } catch (Exception e) {
            newReimbursement = reimbursementService.convertFormReimbursement(ctx);
        }
        reimbursementService.createReimbursement(newReimbursement);
    };

    public final Handler getAllReimbursements = ctx -> ctx.json(reimbursementService.getAllReimbursements());

    public final Handler getReimbursementById = ctx -> ctx
            .json(reimbursementService.getReimbursementById(ctx.pathParam("id")));

    public final Handler getReimbursementByAuthor = ctx -> ctx
            .json(reimbursementService.getReimbursementByAuthor(ctx.pathParam("author")));

    public final Handler getReimbursementByType = ctx -> ctx
            .json(reimbursementService.getReimbursementByType(ctx.pathParam("type")));

    public final Handler getReimbursementByStatus = ctx -> ctx
            .json(reimbursementService.getReimbursementByStatus(ctx.pathParam("status")));

    public final Handler updateReimbursement = ctx -> {
        Reimbursement newReimbursement;
        try {
            newReimbursement = obj.readValue(ctx.body(), Reimbursement.class);
        } catch (Exception e) {
            newReimbursement = reimbursementService.convertFormReimbursement(ctx);
        }
        reimbursementService.updateReimbursement(ctx.pathParam("id"), newReimbursement);
    };

    // DELETE ALL ------ NOT FOR PRODUCTION!!!
    public final Handler deleteAllReimbursements = ctx -> reimbursementService.deleteAll();
}
