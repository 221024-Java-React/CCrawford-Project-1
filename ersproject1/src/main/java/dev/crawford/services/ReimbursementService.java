package dev.crawford.services;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.crawford.models.Reimbursement;
import dev.crawford.models.ReimbursementStatus;
import dev.crawford.models.ReimbursementType;
import dev.crawford.repositories.ReimbursementDAO;
import io.javalin.http.Context;

public class ReimbursementService {

    private ReimbursementDAO reimbursementDao;

    public ReimbursementService(ReimbursementDAO reimbursementDao) {
        this.reimbursementDao = reimbursementDao;
    }

    // Create Reimbursement through JSON/Object Mapper
    public void createReimbursement(Reimbursement reimbursement) {
        reimbursement.setStatus(ReimbursementStatus.PENDING);
        reimbursementDao.createReimbursement(reimbursement);
    }

    public List<Reimbursement> getAllReimbursements(String user, String role) {
        try {
            if(role.equals("EMPLOYEE")){
            List<Reimbursement> empArr = new ArrayList<>();
            empArr.addAll(reimbursementDao.getReimbursementByAuthor(user));
            return empArr;
        } else {
            return reimbursementDao.getAllReimbursements();
        }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Reimbursement getReimbursementById(String id) {
        if(reimbursementDao.getReimbursementById(id) != null) {
            return reimbursementDao.getReimbursementById(id);
        } else {
            return new Reimbursement();
        }
    }

    public List<Reimbursement> getReimbursementByAuthor(String author) {
        if(reimbursementDao.getReimbursementByAuthor(author) != null) {
            return reimbursementDao.getReimbursementByAuthor(author);
        } else {
            return Collections.emptyList();
        }
    }

    public List<Reimbursement> getReimbursementByType(String type) {
        if(reimbursementDao.getReimbursementByType(type) != null) {
            return reimbursementDao.getReimbursementByType(type);
        } else {
            return Collections.emptyList();
        }
    }

    public List<Reimbursement> getReimbursementByStatus(String status) {
        if(reimbursementDao.getReimbursementByStatus(status) != null) {
            return reimbursementDao.getReimbursementByStatus(status);
        } else {
            return Collections.emptyList();
        }
    }

    public boolean updateReimbursement(String id, Reimbursement reimbursement, String resolver) {
        if(reimbursementDao.getReimbursementById(id) != null) {
            Reimbursement statusTest = reimbursementDao.getReimbursementById(id);
            if(statusTest.getStatus() == ReimbursementStatus.APPROVED
                    || statusTest.getStatus() == ReimbursementStatus.DENIED) {
                        reimbursement.setStatus(statusTest.getStatus());
            }
            if(statusTest.getResolver() == null){
                reimbursement.setResolver(resolver);
            } else {
                return false;
            }
            reimbursement.setId(Integer.parseInt(id));
            reimbursement.setAuthor(statusTest.getAuthor());
            reimbursementDao.updateReimbursement(reimbursement);
            return true;
        }
        return false;
    }

    // DELETE ALL ------ NOT FOR PRODUCTION!!!
    public void deleteAll() {
        reimbursementDao.deleteAll();
    }

    // Convert Form-Encoded to Reimbursement Object
    public Reimbursement convertFormReimbursement(Context ctx) {
        String line = ctx.body();
        Reimbursement newReimbursement = new Reimbursement();

        String[] pairs = line.split("\\&");
        for(int i = 0; i < pairs.length; i++) {
            String[] fields = pairs[i].split("=");
            String name;
            try {
                name = URLDecoder.decode(fields[0], "UTF-8");
                String value = URLDecoder.decode(fields[1], "UTF-8");
                switch (name) {
                    case "author":
                        newReimbursement.setAuthor(value);
                        break;
                    case "resolver":
                        newReimbursement.setResolver(value);
                        break;
                    case "status":
                        newReimbursement.setStatus(ReimbursementStatus.valueOf(value));
                        break;
                    case "amount":
                        newReimbursement.setAmount(Double.parseDouble(value));
                        break;
                    case "description":
                        newReimbursement.setDescription(value);
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
