import com.sun.net.httpserver.HttpServer;
import db.DatabaseConnection;
import db.SqliteDatabaseConnection;
import httphandler.*;

import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws Exception {
        DatabaseConnection connection = SqliteDatabaseConnection.open();

        if (connection == null) {
            return;
        }

        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", 8080), 0);
        server.createContext("/students", new GetStudentsHandler(connection));
        server.createContext("/add_student", new PostStudentHandler(connection));
        server.createContext("/remove_student", new RemoveStudentHandler(connection));
        server.createContext("/add_score", new AddScoreHandler(connection));
        server.setExecutor(null);
        System.out.println("Starting server...");
        server.start();
    }
}
