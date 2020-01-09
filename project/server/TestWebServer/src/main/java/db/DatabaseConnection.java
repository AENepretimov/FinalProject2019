package db;

import java.util.List;

public interface DatabaseConnection {
    List<Student> getStudents();
    void addScore(Integer studentId, Integer score);
    void updateStudent(Student Student);
    void removeStudent(String studentId);
}
