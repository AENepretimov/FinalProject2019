package httphandler;

import com.sun.net.httpserver.HttpExchange;
import db.Student;
import db.DatabaseConnection;
import db.Student;

import java.io.IOException;
import java.io.OutputStream;

public class PostStudentHandler extends BaseHttpHandler {

    public PostStudentHandler(DatabaseConnection connection) {
        super(connection);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestBody = Utils.requestBodyToString(httpExchange.getRequestBody());
        Student student = getGson().fromJson(requestBody, Student.class);
        getDatabaseConnection().updateStudent(student);
        httpExchange.sendResponseHeaders(200, 0);
        OutputStream os = httpExchange.getResponseBody();
        os.close();
    }
}
