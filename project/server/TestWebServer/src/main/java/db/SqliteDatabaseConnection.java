package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteDatabaseConnection implements DatabaseConnection {

    private static final String BASE_URL = "jdbc:mysql:";
    private static final String DB_LOCATION = "//188.225.58.136:3306/TestDB?user=root&password=pkQ8XxN5_pkQ8XxN5";
    private static final String DATABASE_URL = BASE_URL + DB_LOCATION;

    private Connection mConnection;

    private SqliteDatabaseConnection() throws SQLException {
        mConnection = DriverManager.getConnection(DATABASE_URL);
        mConnection.createStatement().execute(DatabaseRequest.CREATE_TABLE_STUDENTS);
    }

    public static SqliteDatabaseConnection open() {
        try {
            return new SqliteDatabaseConnection();
        } catch (SQLException e) {
            System.out.println("Unable to open database: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Student> getStudents() {
        try {
            ResultSet resultSet = mConnection.createStatement().executeQuery(DatabaseRequest.GET_STUDENTS);
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getInt(DatabaseRequest.TABLE_PROPERTY_ID),
                        resultSet.getString(DatabaseRequest.TABLE_PROPERTY_NAME),
                        resultSet.getString(DatabaseRequest.TABLE_PROPERTY_GROUP),
                        resultSet.getInt(DatabaseRequest.TABLE_PROPERTY_SCORE)

                ));
            }
            return students;
        } catch (SQLException e) {
            System.out.println("getCars() failed: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void addScore(Integer studentId, Integer score) {
        try {
            PreparedStatement statement = mConnection.prepareStatement(DatabaseRequest.ADD_SCORE);
            statement.setObject(1, score);
            statement.setObject(2, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("addScore() failed: " + e.getMessage());
        }
    }

    @Override
    public void updateStudent(Student student) {
        try {
            PreparedStatement statement = mConnection.prepareStatement(DatabaseRequest.ADD_STUDENT);
            //statement.setObject(1, student.getId());
            statement.setString(1, student.getName());
            statement.setString(2, student.getGroup());
            statement.setInt(3, student.getScore());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateStudent() failed: " + e.getMessage());
        }
    }

    @Override
    public void removeStudent(String carId) {
        try {
            PreparedStatement statement = mConnection.prepareStatement(DatabaseRequest.REMOVE_STUDENT);
            statement.setString(1, carId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("removeStudent() failed: " + e.getMessage());
        }
    }
}
