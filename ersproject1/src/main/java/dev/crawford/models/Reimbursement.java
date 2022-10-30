package dev.crawford.models;

public class Reimbursement {
    
    private int id;
    private String author;
    private String resolver;
    private ReimbursementStatus status;
    private Double amount;
    private String description;
    private ReimbursementType type;

    public Reimbursement() {
    }

    public Reimbursement(int id, String author, String resolver, ReimbursementStatus status, Double amount, String description, ReimbursementType type) {
        this.id = id;
        this.author = author;
        this.resolver = resolver;
        this.status = status;
        this.amount = amount;
        this.description = description;
        this.type = type;
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

    public ReimbursementStatus getStatus() {
        return status;
    }

    public void setStatus(ReimbursementStatus status) {
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

    public ReimbursementType getType() {
        return type;
    }

    public void setType(ReimbursementType type) {
        this.type = type;
    }
}
