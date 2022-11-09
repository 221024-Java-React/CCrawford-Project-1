package dev.crawford.services;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.List;

import dev.crawford.models.Reimbursement;
import dev.crawford.models.ReimbursementStatus;
import dev.crawford.models.ReimbursementType;
import dev.crawford.repositories.ReimbursementRepository;
import io.javalin.http.Context;

public class ReimbursementService {

    private ReimbursementRepository reimbursementRepository;

    public ReimbursementService() {
        reimbursementRepository = new ReimbursementRepository();
    }

    // Create Reimbursement through JSON/Object Mapper
    public void createReimbursement(Reimbursement reimbursement) {
        reimbursement.setStatus(ReimbursementStatus.PENDING);
        reimbursementRepository.createReimbursement(reimbursement);
    }

    public List<Reimbursement> getAllReimbursements() {
        return reimbursementRepository.getAllReimbursements();
    }

    public Reimbursement getReimbursementById(String id) {
        if (reimbursementRepository.getReimbursementById(id) != null) {
            return reimbursementRepository.getReimbursementById(id);
        } else {
            return null;
        }
    }

    public List<Reimbursement> getReimbursementByAuthor(String author) {
        if (reimbursementRepository.getReimbursementByAuthor(author) != null) {
            return reimbursementRepository.getReimbursementByAuthor(author);
        } else {
            return Collections.emptyList();
        }
    }

    public List<Reimbursement> getReimbursementByType(String type) {
        if (reimbursementRepository.getReimbursementByType(type) != null) {
            return reimbursementRepository.getReimbursementByType(type);
        } else {
            return Collections.emptyList();
        }
    }

    public List<Reimbursement> getReimbursementByStatus(String status) {
        if (reimbursementRepository.getReimbursementByStatus(status) != null) {
            return reimbursementRepository.getReimbursementByStatus(status);
        } else {
            return Collections.emptyList();
        }
    }

    public void updateReimbursement(String id, Reimbursement reimbursement) {
        if (reimbursementRepository.getReimbursementById(id) != null) {
            Reimbursement statusTest = reimbursementRepository.getReimbursementById(id);
            if (statusTest.getStatus() == ReimbursementStatus.APPROVED
                    || statusTest.getStatus() == ReimbursementStatus.DENIED) {
                        reimbursement.setStatus(statusTest.getStatus());
            }
            reimbursement.setId(Integer.parseInt(id));
            reimbursementRepository.updateReimbursement(reimbursement);
        }
    }

    // DELETE ALL ------ NOT FOR PRODUCTION!!!
    public void deleteAll() {
        reimbursementRepository.deleteAll();
    }

    // Convert Form-Encoded to Reimbursement Object
    public Reimbursement convertFormReimbursement(Context ctx) {
        String line = ctx.body();
        Reimbursement newReimbursement = new Reimbursement();

        String[] pairs = line.split("\\&");
        for (int i = 0; i < pairs.length; i++) {
            String[] fields = pairs[i].split("=");
            String name;
            try {
                name = URLDecoder.decode(fields[0], "UTF-8");
                String value = URLDecoder.decode(fields[1], "UTF-8");
                switch (name) {
                    case "firstName":
                        newReimbursement.setAuthor(value);
                        break;
                    case "lastName":
                        newReimbursement.setResolver(value);
                        break;
                    case "email":
                        newReimbursement.setStatus(ReimbursementStatus.valueOf(value));
                        break;
                    case "password":
                        newReimbursement.setAmount(Double.parseDouble(value));
                        break;
                    case "type":
                        newReimbursement.setType(ReimbursementType.valueOf(value));
                        break;
                    default:
                        break;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return newReimbursement;
    }

}
