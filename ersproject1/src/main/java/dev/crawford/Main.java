package dev.crawford;

import dev.crawford.controllers.EmployeeController;
import dev.crawford.controllers.ReimbursementController;
import dev.crawford.models.EmployeeRole;
import dev.crawford.repositories.EmployeeDAO;
import dev.crawford.repositories.EmployeeDAOJDBC;
import dev.crawford.repositories.ReimbursementDAO;
import dev.crawford.repositories.ReimbursementDAOJDBC;
import dev.crawford.services.EmployeeService;
import dev.crawford.services.ReimbursementService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.staticfiles.Location;

public class Main {

    static EmployeeRole getUserRole(Context ctx) {
        return EmployeeRole.valueOf(ctx.cookieStore().get("role").toString());
    }

    public static void main(String[] args) {

        EmployeeDAO employeeDAO = new EmployeeDAOJDBC();
        EmployeeService employeeService = new EmployeeService(employeeDAO);
        EmployeeController employeeController = new EmployeeController(employeeService);

        ReimbursementDAO reimbursementDAO = new ReimbursementDAOJDBC();
        ReimbursementService reimbursementService = new ReimbursementService(reimbursementDAO);
        ReimbursementController reimbursementController = new ReimbursementController(reimbursementService);

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
        app.before("/register", ctx -> ctx.cookieStore().set("role", EmployeeRole.EMPLOYEE));

        app.post("/", employeeController.hello, EmployeeRole.EMPLOYEE, EmployeeRole.MANAGER);
        app.post("/login", employeeController.login, EmployeeRole.EMPLOYEE, EmployeeRole.MANAGER);
        app.post("/register", employeeController.createEmployee, EmployeeRole.EMPLOYEE, EmployeeRole.MANAGER);
        app.get("/employees", employeeController.getAllEmployees, EmployeeRole.EMPLOYEE, EmployeeRole.MANAGER);
        app.get("/employees/<email>", employeeController.getEmployeeByEmail, EmployeeRole.MANAGER);
        app.put("/employees/<email>", employeeController.updateEmployee, EmployeeRole.MANAGER);

        // DELETE ALL ------ NOT FOR PRODUCTION!!!
        app.delete("/employees", employeeController.deleteAllEmployees, EmployeeRole.MANAGER);

        app.post("/reimbursements", reimbursementController.createReimbursement, EmployeeRole.EMPLOYEE, EmployeeRole.MANAGER);
        app.get("/reimbursements", reimbursementController.getAllReimbursements, EmployeeRole.EMPLOYEE, EmployeeRole.MANAGER);
        app.get("/reimbursements/id/<id>", reimbursementController.getReimbursementById, EmployeeRole.MANAGER);
        app.get("/reimbursements/author/<author>", reimbursementController.getReimbursementByAuthor, EmployeeRole.MANAGER);
        app.get("/reimbursements/type/<type>", reimbursementController.getReimbursementByType, EmployeeRole.MANAGER);
        app.get("/reimbursements/status/<status>", reimbursementController.getReimbursementByStatus, EmployeeRole.MANAGER);
        app.put("/reimbursements/id/<id>", reimbursementController.updateReimbursement, EmployeeRole.MANAGER);

    }
}
