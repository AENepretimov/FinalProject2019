package httphandler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpHandler;
import db.DatabaseConnection;

public abstract class BaseHttpHandler implements HttpHandler {
    private DatabaseConnection mConnection;
    private Gson mGson;

    public BaseHttpHandler(DatabaseConnection connection) {
        mConnection = connection;
        mGson = new Gson();
    }

    public DatabaseConnection getDatabaseConnection() {
        return mConnection;
    }

    protected Gson getGson() {
        return mGson;
    }
}
