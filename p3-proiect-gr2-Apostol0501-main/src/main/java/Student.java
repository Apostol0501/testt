
import java.util.HashMap;

public class Student {
    private String firstName; // Prenumele studentului
    private String lastName;  // Numele studentului
    private String specialization;
    private int year;
    private HashMap<String, Double> grades;

    public Student(String firstName, String lastName, String specialization, int year) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.year = year;
        this.grades = new HashMap<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getYear() {
        return year;
    }

    public void addGrade(String subject, double grade) {
        grades.put(subject, grade);
    }

    public void removeGrade(String subject) {
        grades.remove(subject);
    }

    public HashMap<String, Double> getGrades() {
        return grades;
    }

    public double getGrade(String subject) {
        return grades.getOrDefault(subject, 0.0);
    }

    @Override
    public String toString() {
        return "Student: " + getFullName() + ", Specialization: " + specialization + ", Year: " + year + ", Grades: " + grades.toString();
    }
}
