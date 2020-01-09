package httphandler;

import com.sun.net.httpserver.HttpExchange;
import db.Student;
import db.DatabaseConnection;
import db.Student;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class GetStudentsHandler extends BaseHttpHandler {

    public GetStudentsHandler(DatabaseConnection connection) {
        super(connection);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        List<Student> students;

        students = getDatabaseConnection().getStudents();

        String response = getGson().toJson(students);
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
