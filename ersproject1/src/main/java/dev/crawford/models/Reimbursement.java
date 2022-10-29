package dev.crawford.models;

public class Reimbursement {
    
    private int id;
    private String author;
    private String resolver;
    private String status;
    private Double amount;
    private String description;

    public Reimbursement() {
    }

    public Reimbursement(int id, String author, String resolver, String status, Double amount, String description) {
        this.id = id;
        this.author = author;
        this.resolver = resolver;
        this.status = status;
        this.amount = amount;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getResolver() {
        return resolver;
    }

    public void setResolver(String resolver) {
        this.resolver = resolver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
}
