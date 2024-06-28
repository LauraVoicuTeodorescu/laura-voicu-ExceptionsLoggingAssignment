// Path: /src/main/java/com/example/exceptions/InvalidStudentException.java
package com.example.exceptions;

public class InvalidStudentException extends RuntimeException {
    public InvalidStudentException(String message) {
        super(message);
    }
}
