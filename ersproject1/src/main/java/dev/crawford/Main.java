package dev.crawford;

import dev.crawford.controllers.EmployeeController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Main {
    
    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public", Location.CLASSPATH);
        }).start(7070);

        // app.before(EmployeeController.before);

        app.post("/", EmployeeController.hello);

        app.post("/register", EmployeeController.createEmployee);

        app.post("/formregister", EmployeeController.createFormEmployee);

        app.get("/employees", EmployeeController.getAllEmployees);

        app.get("/employees/<email>", EmployeeController.getEmployeeByEmail);


        // DELETE ALL ------ NOT FOR PRODUCTION!!!
        app.delete("/employees", EmployeeController.deleteAllEmployees);


    }
}
