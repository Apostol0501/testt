

import java.util.HashMap;

public class Admin {
    private HashMap<String, Student> students;

    private static final HashMap<String, HashMap<Integer, String[]>> predefinedSubjects = new HashMap<>();
    ////
    static {
        HashMap<Integer, String[]> mathSubjects = new HashMap<>();
        mathSubjects.put(1, new String[]{"Algebra", "Analiza Matematica", "Geometrie", "Logica", "Probabilitati"});
        mathSubjects.put(2, new String[]{"Matematici Aplicate", "Teoria Numerelor", "Statistica", "Topologie", "Ecuatii Diferentiale"});
        mathSubjects.put(3, new String[]{"Analiza Avansata", "Calcul Numeric", "Geometrie Diferentiala", "Optimizare", "Matematici Discrete"});

        HashMap<Integer, String[]> csSubjects = new HashMap<>();
        csSubjects.put(1, new String[]{"Programare", "Structuri de Date", "Matematica Discreta", "Logica", "Arhitectura Calculatoarelor"});
        csSubjects.put(2, new String[]{"Baze de Date", "Retele de Calculatoare", "Algoritmica", "Sisteme de Operare", "Inteligenta Artificiala"});
        csSubjects.put(3, new String[]{"Proiectare Software", "Programare Avansata", "Securitate Informationala", "Big Data", "Machine Learning"});

        predefinedSubjects.put("Matematica", mathSubjects);
        predefinedSubjects.put("Informatica", csSubjects);
    }

    public Admin() {
        this.students = new HashMap<>();
    }

    public void addStudent(Student student) {
        students.put(student.getFullName(), student);
    }

    public void editStudent(String fullName, int newYear) {
        Student student = students.get(fullName);
        if (student != null) {
            students.put(fullName, new Student(student.getFirstName(), student.getLastName(), student.getSpecialization(), newYear));
        }
    }

    public void removeStudent(String fullName) {
        students.remove(fullName);
    }

    public void addGrade(String fullName) {
        Student student = students.get(fullName);
        if (student != null) {
            // Afișează materiile disponibile pentru student
            String[] subjects = predefinedSubjects.get(student.getSpecialization()).get(student.getYear());
            System.out.println("Selecteaza materia pentru care doresti sa adaugi nota:");
            for (int i = 0; i < subjects.length; i++) {
                System.out.println((i + 1) + ". " + subjects[i]);
            }
            int choice = getValidChoice(1, subjects.length);
            String subject = subjects[choice - 1];
            System.out.print("Introdu nota: ");
            double grade = new java.util.Scanner(System.in).nextDouble();
            student.addGrade(subject, grade);
            System.out.println("Nota pentru " + subject + " a fost adaugata.");
        }
    }

    public void removeGrade(String fullName) {
        Student student = students.get(fullName);
        if (student != null) {
            // Afișează materiile și notele pentru care studenții au note
            HashMap<String, Double> grades = student.getGrades();
            if (grades.isEmpty()) {
                System.out.println("Studentul nu are note.");
                return;
            }
            System.out.println("Selecteaza materia pentru care doresti sa stergi nota:");
            int index = 1;
            for (String subject : grades.keySet()) {
                System.out.println(index + ". " + subject + " - Nota: " + grades.get(subject));
                index++;
            }
            int choice = getValidChoice(1, grades.size());
            String subjectToRemove = (String) grades.keySet().toArray()[choice - 1];
            student.removeGrade(subjectToRemove);
            System.out.println("Nota pentru materia " + subjectToRemove + " a fost stearsa.");
        }
    }

    public Student searchStudentByFullName(String fullName) {
        return students.get(fullName);
    }

    public HashMap<String, Student> getStudents() {
        return students;
    }

    private int getValidChoice(int min, int max) {
        int choice = -1;
        while (choice < min || choice > max) {
            try {
                choice = Integer.parseInt(new java.util.Scanner(System.in).nextLine());
                if (choice < min || choice > max) {
                    System.out.println("Optiune invalida. Te rog sa alegi un numar intre " + min + " si " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Optiune invalida. Te rog sa introduci un numar.");
            }
        }
        return choice;
    }
}
