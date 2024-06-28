// Path: /src/main/java/com/example/repository/StudentRepository.java
package com.example.repository;

import com.example.exceptions.*;
import com.example.model.Student;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class StudentRepository {
    private static final Logger logger = Logger.getLogger(StudentRepository.class.getName());
    private Map<String, Student> students = new HashMap<>();

    public void addStudent(Student student) {
        if (students.containsKey(student.getId())) {
            throw new InvalidStudentException("Student with ID " + student.getId() + " already exists.");
        }
        students.put(student.getId(), student);
        logger.info("Added student: " + student);
    }

    public void deleteStudent(String id) {
        if (id == null || id.isEmpty()) {
            throw new InvalidInputException("ID cannot be empty.");
        }
        if (!students.containsKey(id)) {
            throw new StudentNotFoundException("Student with ID " + id + " does not exist.");
        }
        students.remove(id);
        logger.info("Deleted student with ID: " + id);
    }

    public List<Student> getStudentsByAge(int age) {
        if (age < 0) {
            throw new InvalidInputException("Age cannot be negative.");
        }
        return students.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }

    public List<Student> listStudents(String orderBy) {
        if (orderBy == null || orderBy.isEmpty()) {
            throw new InvalidInputException("Order by parameter cannot be empty.");
        }
        return students.values().stream()
                .sorted(orderBy.equalsIgnoreCase("lastName") ?
                        Comparator.comparing(Student::getLastName) :
                        Comparator.comparing(Student::getDateOfBirth))
                .collect(Collectors.toList());
    }
}
