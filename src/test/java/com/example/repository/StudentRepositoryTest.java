// Path: /src/test/java/com/example/repository/StudentRepositoryTest.java
package com.example.repository;

import com.example.exceptions.*;
import com.example.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentRepositoryTest {

    private StudentRepository repository;

    @BeforeEach
    public void setup() {
        repository = new StudentRepository();
    }

    @Test
    public void testAddStudent() {
        Student student = new Student("John", "Doe", "2000-01-01", "male", "12345");
        repository.addStudent(student);
        assertEquals(1, repository.listStudents("lastName").size());
    }

    @Test
    public void testDeleteStudent() {
        Student student = new Student("John", "Doe", "2000-01-01", "male", "12345");
        repository.addStudent(student);
        repository.deleteStudent("12345");
        assertEquals(0, repository.listStudents("lastName").size());
    }

    @Test
    public void testGetStudentsByAge() {
        Student student = new Student("John", "Doe", "2000-01-01", "male", "12345");
        repository.addStudent(student);
        List<Student> students = repository.getStudentsByAge(24);
        assertEquals(1, students.size());
    }

    @Test
    public void testListStudentsByLastName() {
        Student student1 = new Student("John", "Doe", "2000-01-01", "male", "12345");
        Student student2 = new Student("Jane", "Smith", "1998-05-15", "female", "54321");
        repository.addStudent(student1);
        repository.addStudent(student2);
        List<Student> students = repository.listStudents("lastName");
        assertEquals("Doe", students.get(0).getLastName());
        assertEquals("Smith", students.get(1).getLastName());
    }

    @Test
    public void testListStudentsByDateOfBirth() {
        Student student1 = new Student("John", "Doe", "2000-01-01", "male", "12345");
        Student student2 = new Student("Jane", "Smith", "1998-05-15", "female", "54321");
        repository.addStudent(student1);
        repository.addStudent(student2);
        List<Student> students = repository.listStudents("dateOfBirth");
        assertEquals("Smith", students.get(0).getLastName());
        assertEquals("Doe", students.get(1).getLastName());
    }

    @Test
    public void testAddStudentWithEmptyFirstName() {
        Exception exception = assertThrows(InvalidStudentException.class, () -> {
            new Student("", "Doe", "2000-01-01", "male", "12345");
        });
        assertEquals("First name cannot be empty.", exception.getMessage());
    }

    @Test
    public void testDeleteStudentWithEmptyID() {
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            repository.deleteStudent("");
        });
        assertEquals("ID cannot be empty.", exception.getMessage());
    }

    @Test
    public void testGetStudentsByNegativeAge() {
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            repository.getStudentsByAge(-1);
        });
        assertEquals("Age cannot be negative.", exception.getMessage());
    }

    @Test
    public void testListStudentsWithEmptyOrderBy() {
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            repository.listStudents("");
        });
        assertEquals("Order by parameter cannot be empty.", exception.getMessage());
    }
}
