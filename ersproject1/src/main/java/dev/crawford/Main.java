package dev.crawford;

import dev.crawford.controllers.EmployeeController;
import dev.crawford.controllers.ReimbursementController;
import dev.crawford.models.EmployeeRole;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.staticfiles.Location;

public class Main {

    static EmployeeRole getUserRole(Context ctx) {
        return EmployeeRole.valueOf(ctx.cookieStore().get("role").toString());
    }

    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public", Location.CLASSPATH);
            config.accessManager((handler, ctx, routeRoles) -> {
                EmployeeRole userRole = getUserRole(ctx);
                if (routeRoles.contains(userRole)) {
                    handler.handle(ctx);
                } else {
                    ctx.status(401).result("Unauthorized");
                }
            });
        }).start(7070);
        
        app.before("/", ctx -> ctx.cookieStore().set("role", EmployeeRole.EMPLOYEE));
        app.before("/login", ctx -> ctx.cookieStore().set("role", EmployeeRole.EMPLOYEE));

        app.post("/", EmployeeController.hello, EmployeeRole.EMPLOYEE);
        app.post("/login", EmployeeController.login, EmployeeRole.EMPLOYEE);
        app.post("/register", EmployeeController.createEmployee, EmployeeRole.EMPLOYEE, EmployeeRole.MANAGER);
        app.get("/employees", EmployeeController.getAllEmployees, EmployeeRole.EMPLOYEE, EmployeeRole.MANAGER);
        app.get("/employees/<email>", EmployeeController.getEmployeeByEmail, EmployeeRole.MANAGER);
        app.put("/employee/<email>", EmployeeController.updateEmployee, EmployeeRole.MANAGER);

        // DELETE ALL ------ NOT FOR PRODUCTION!!!
        app.delete("/employees", EmployeeController.deleteAllEmployees, EmployeeRole.MANAGER);

        app.post("/reimbursements", ReimbursementController.createReimbursement, EmployeeRole.EMPLOYEE, EmployeeRole.MANAGER);
        app.get("/reimbursements", ReimbursementController.getAllReimbursements, EmployeeRole.EMPLOYEE, EmployeeRole.MANAGER);
        app.get("/reimbursements/id/<id>", ReimbursementController.getReimbursementById, EmployeeRole.MANAGER);
        app.get("/reimbursements/author/<author>", ReimbursementController.getReimbursementByAuthor, EmployeeRole.MANAGER);
        app.get("/reimbursements/type/<type>", ReimbursementController.getReimbursementByType, EmployeeRole.EMPLOYEE, EmployeeRole.MANAGER);
        app.get("/reimbursements/status/<status>", ReimbursementController.getReimbursementByStatus, EmployeeRole.EMPLOYEE, EmployeeRole.MANAGER);
        app.put("/reimbursements/id/<id>", ReimbursementController.updateReimbursement, EmployeeRole.MANAGER);

    }
}
