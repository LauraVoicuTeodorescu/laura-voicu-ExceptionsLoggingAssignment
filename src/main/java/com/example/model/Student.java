// Path: /src/main/java/com/example/model/Student.java
package com.example.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String id;

    public Student(String firstName, String lastName, String dateOfBirth, String gender, String id) {
        if (firstName == null || firstName.isEmpty()) {
            throw new InvalidStudentException("First name cannot be empty.");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new InvalidStudentException("Last name cannot be empty.");
        }
        this.dateOfBirth = parseDateOfBirth(dateOfBirth);
        this.gender = validateGender(gender);
        this.id = id;
    }

    private LocalDate parseDateOfBirth(String dateOfBirth) {
        LocalDate dob = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate minDate = LocalDate.of(1900, 1, 1);
        LocalDate maxDate = LocalDate.now().minusYears(18);
        if (dob.isBefore(minDate) || dob.isAfter(maxDate)) {
            throw new InvalidStudentException("Date of birth must be between 1900 and current year - 18.");
        }
        return dob;
    }

    private String validateGender(String gender) {
        if (gender == null || !(gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female") ||
                gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))) {
            throw new InvalidStudentException("Gender must be 'male', 'female', 'M', or 'F'.");
        }
        return gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    public int getAge() {
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }

    @Override
    public String toString() {
        return String.format("Student{name='%s %s', dateOfBirth=%s, gender='%s', id='%s'}",
                firstName, lastName, dateOfBirth, gender, id);
    }
}
