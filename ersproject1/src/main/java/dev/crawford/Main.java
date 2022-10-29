package dev.crawford;

import dev.crawford.controllers.EmployeeController;
import dev.crawford.controllers.ReimbursementController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Main {
    
    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public", Location.CLASSPATH);
        }).start(7070);

        // app.before(EmployeeController.before);

        app.post("/", EmployeeController.hello);
        app.post("/login", EmployeeController.login);
        app.post("/register", EmployeeController.createEmployee);
        app.get("/employees", EmployeeController.getAllEmployees);
        app.get("/employees/<email>", EmployeeController.getEmployeeByEmail);
        app.put("/employee/<email>", EmployeeController.updateEmployee);

        // DELETE ALL ------ NOT FOR PRODUCTION!!!
        app.delete("/employees", EmployeeController.deleteAllEmployees);

        app.post("/reimbursements", ReimbursementController.createReimbursement);
        app.get("/reimbursements", ReimbursementController.getAllReimbursements);
        app.get("/reimbursements/<author>", ReimbursementController.getReimbursementByAuthor);
        app.put("/reimbursements/<author>", ReimbursementController.updateReimbursement);

    }
}
