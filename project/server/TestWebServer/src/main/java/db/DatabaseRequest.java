package db;

public class DatabaseRequest {
    public static final String TABLE_NAME_STUDENTS = "students";
    public static final String TABLE_PROPERTY_ID = "id";
    public static final String TABLE_PROPERTY_NAME = "name";
    public static final String TABLE_PROPERTY_GROUP = "studentGroup";
    public static final String TABLE_PROPERTY_SCORE = "score";

    public static final String CREATE_TABLE_STUDENTS = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_STUDENTS + " (" +
            TABLE_PROPERTY_ID + " integer PRIMARY KEY AUTO_INCREMENT, " +
            TABLE_PROPERTY_NAME + " text NOT NULL," +
            TABLE_PROPERTY_GROUP + " text NOT NULL," +
            TABLE_PROPERTY_SCORE + " integer NOT NULL" +
            ")";

    public static final String GET_STUDENTS = "SELECT * FROM " + TABLE_NAME_STUDENTS;

    public static final String ADD_SCORE = "UPDATE " + TABLE_NAME_STUDENTS + " SET "
            + TABLE_PROPERTY_SCORE + "=? WHERE " + TABLE_PROPERTY_ID + "=?";

    public static final String ADD_STUDENT = "INSERT INTO " + TABLE_NAME_STUDENTS
            + "(" + TABLE_PROPERTY_NAME + ","
            + TABLE_PROPERTY_GROUP + "," + TABLE_PROPERTY_SCORE + ") VALUES (?, ?, ?)";

    public static final String REMOVE_STUDENT = "DELETE FROM " + TABLE_NAME_STUDENTS + " WHERE " + TABLE_PROPERTY_ID
            + "=?";

    private DatabaseRequest() {
        throw new UnsupportedOperationException();
    }
}
