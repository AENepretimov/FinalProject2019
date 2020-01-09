package httphandler;

import com.sun.net.httpserver.HttpExchange;
import db.DatabaseConnection;

import java.io.IOException;
import java.io.OutputStream;

public class RemoveStudentHandler extends BaseHttpHandler {

    public RemoveStudentHandler(DatabaseConnection connection) {
        super(connection);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String studentId = Utils.queryToMap(httpExchange.getRequestURI().getQuery()).get("id");

        if (studentId == null) {
            System.out.println("Student id not found in query");
            httpExchange.sendResponseHeaders(404, 0);
            OutputStream os = httpExchange.getResponseBody();
            os.close();
            return;
        }

        getDatabaseConnection().removeStudent(studentId);
        httpExchange.sendResponseHeaders(200, 0);
        OutputStream os = httpExchange.getResponseBody();
        os.close();
    }
}
