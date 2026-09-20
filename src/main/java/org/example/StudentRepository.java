package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    public List<Student> getStudents() {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT id, name FROM students";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery()
        ) {

            while (result.next()) {

                int id = result.getInt("id");
                String name = result.getString("name");

                students.add(new Student(id, name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }


    public Student getStudent(int id) {

        String sql = "SELECT id, name FROM students WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {

                    int studentId = result.getInt("id");
                    String name = result.getString("name");

                    return new Student(studentId, name);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void addStudent(Student student) {

        String sql = "INSERT INTO students (id, name) VALUES (?, ?)";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, student.getId());
            statement.setString(2, student.getName());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean existsById(int id) {

        String sql = "SELECT 1 FROM students WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {

                return result.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateStudent(int id, Student student) {

        String sql = "UPDATE students SET name = ? WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, student.getName());
            statement.setInt(2, id);

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public List<Student> searchByName(String name) {

        List<Student> students = new ArrayList<>();

        String sql = """
            SELECT id, name
            FROM students
            WHERE LOWER(name) = LOWER(?)
            """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, name);

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {

                    int id = result.getInt("id");
                    String studentName = result.getString("name");

                    students.add(
                            new Student(id, studentName)
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }
}