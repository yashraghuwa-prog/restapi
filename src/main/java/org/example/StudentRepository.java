package org.example;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private final List<Student> students = new ArrayList<>();

    public StudentRepository() {
        students.add(new Student(1, "Yash"));
        students.add(new Student(2, "Rahul"));
        students.add(new Student(3, "Aman"));
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student getStudent(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }
    public void addStudent(Student student) {
        students.add(student);
    }
    public boolean existsById(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return true;
            }
        }

        return false;
    }
    public void updateStudent(Student student) {

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getId() == student.getId()) {
                students.set(i, student);
                return;
            }
        }
    }
}