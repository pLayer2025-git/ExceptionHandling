import java.util.*;
import java.util.logging.*;

// Custom Exception for invalid grade
class InvalidGradeException extends Exception {
    public InvalidGradeException(String message) {
        super(message);
    }
}

// Student class
class Student {
    private String name;
    private int age;
    private double grade;

    public Student(String name, int age, double grade) throws InvalidGradeException {
        if (grade < 0 || grade > 100) {
            throw new InvalidGradeException("Grade must be between 0 and 100. Provided: " + grade);
        }
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return name + " (" + age + " years) - Grade: " + grade;
    }
}

// Main Class
public class ExceptionHandlingDemo {

    private static final Logger logger = Logger.getLogger(ExceptionHandlingDemo.class.getName());

    public static void main(String[] args) {

        // Example students
        List<Student> students = new ArrayList<>();

        try {
            students.add(new Student("Mohan", 18, 88.5));
            students.add(new Student("Anita", 17, 92.0));
            students.add(new Student("Ravi", 19, 75.5));
            students.add(new Student("InvalidStudent", 20, 120)); // Will throw custom exception
        } catch (InvalidGradeException e) {
            logger.severe("Error creating student: " + e.getMessage());
        }

        // Calculate average grade safely
        double average = 0;
        try {
            if (students.isEmpty()) {
                throw new ArithmeticException("No students available to calculate average grade.");
            }

            double sum = 0;
            for (Student s : students) {
                sum += s.getGrade();
            }

            // Potential runtime exception: division by zero
            average = sum / students.size();

        } catch (ArithmeticException e) {
            logger.severe("Cannot calculate average: " + e.getMessage());
        } finally {
            System.out.println("Average calculation attempt finished.");
        }

        System.out.println("\nStudent List:");
        for (Student s : students) {
            System.out.println(s);
        }

        System.out.printf("\nAverage Grade: %.2f%n", average);

        // Demonstrate unchecked exception
        try {
            String str = null;
            System.out.println(str.length()); // NullPointerException
        } catch (NullPointerException e) {
            logger.warning("Null pointer encountered: " + e.getMessage());
        }
    }
}
