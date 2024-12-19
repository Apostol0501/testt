
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        Login adminLogin = new Login("admin", "Qadmin123", "admin");
        Login studentLogin = new Login("student", "student123", "student");

        System.out.println("=== Sistem de gestionare a studentilor ===");
        System.out.print("Introdu username: ");
        String username = scanner.nextLine();
        System.out.print("Introdu parola: ");
        String password = scanner.nextLine();

        if (adminLogin.authenticate(username, password)) {
            System.out.println("Logat ca admin.");
            boolean running = true;
            while (running) {
                // Meniul principal pentru admin
                System.out.println("1. Adauga student");
                System.out.println("2. Editeaza student");
                System.out.println("3. Sterge student");
                System.out.println("4. Cauta student dupa nume");
                System.out.println("5. Afiseaza toti studentii");
                System.out.println("6. Iesire");
                int choice = getValidChoice(scanner, 1, 6);

                switch (choice) {
                    case 1:
                        // Adăugă un nou student
                        System.out.print("Prenume student: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Nume student: ");
                        String lastName = scanner.nextLine();
                        System.out.println("Selectati specializarea:");
                        System.out.println("1. Matematica");
                        System.out.println("2. Informatica");
                        int specializationChoice = getValidChoice(scanner, 1, 2);
                        String specialization = (specializationChoice == 1) ? "Matematica" : "Informatica";
                        System.out.print("Anul de studiu (1/2/3): ");
                        int year = getValidChoice(scanner, 1, 3);
                        Student student = new Student(firstName, lastName, specialization, year);
                        admin.addStudent(student);
                        System.out.println("Student adaugat.");
                        break;
                    case 2:
                        // Editează un student existent
                        System.out.print("Prenume student de editat: ");
                        String editFirstName = scanner.nextLine();
                        System.out.print("Nume student de editat: ");
                        String editLastName = scanner.nextLine();
                        String fullName = editFirstName + " " + editLastName;
                        Student editStudent = admin.searchStudentByFullName(fullName);
                        if (editStudent != null) {
                            System.out.println("1. Modifica anul de studiu");
                            System.out.println("2. Adauga nota");
                            System.out.println("3. Sterge nota");
                            int editChoice = getValidChoice(scanner, 1, 3);

                            switch (editChoice) {
                                case 1:
                                    System.out.print("Noul an de studiu: ");
                                    int newYear = getValidChoice(scanner, 1, 3);
                                    admin.editStudent(fullName, newYear);
                                    System.out.println("Anul a fost actualizat.");
                                    break;
                                case 2:
                                    admin.addGrade(fullName);
                                    break;
                                case 3:
                                    admin.removeGrade(fullName);
                                    break;
                                default:
                                    System.out.println("Optiune invalida.");
                                    break;
                            }
                        } else {
                            System.out.println("Studentul nu a fost gasit.");
                        }
                        break;
                    case 3:
                        // Șterge un student
                        System.out.print("Prenume student de sters: ");
                        String removeFirstName = scanner.nextLine();
                        System.out.print("Nume student de sters: ");
                        String removeLastName = scanner.nextLine();
                        String removeFullName = removeFirstName + " " + removeLastName;
                        admin.removeStudent(removeFullName);
                        System.out.println("Student sters.");
                        break;
                    case 4:
                        // Căutare student după nume complet
                        System.out.print("Prenume student de cautat: ");
                        String searchFirstName = scanner.nextLine();
                        System.out.print("Nume student de cautat: ");
                        String searchLastName = scanner.nextLine();
                        String fullSearchName = searchFirstName + " " + searchLastName;
                        Student found = admin.searchStudentByFullName(fullSearchName);
                        if (found != null) {
                            System.out.println(found);
                        } else {
                            System.out.println("Studentul nu a fost gasit.");
                        }
                        break;
                    case 5:
                        // Afișează toți studenții
                        System.out.println("Lista studentilor:");
                        for (Student s : admin.getStudents().values()) {
                            System.out.println(s);
                        }
                        break;
                    case 6:
                        // Ieșire din sistem
                        running = false;
                        break;
                    default:
                        System.out.println("Optiune invalida.");
                        break;
                }
            }
        } else if (studentLogin.authenticate(username, password)) {
            System.out.println("Logat ca student.");
            System.out.print("Introdu prenumele tau: ");
            String studentFirstName = scanner.nextLine();
            System.out.print("Introdu numele tau: ");
            String studentLastName = scanner.nextLine();
            String fullName = studentFirstName + " " + studentLastName;
            Student loggedInStudent = admin.searchStudentByFullName(fullName);

            if (loggedInStudent != null) {
                System.out.println("Informatii student:");
                System.out.println(loggedInStudent);
            } else {
                System.out.println("Studentul nu exista in sistem.");
            }
        } else {
            System.out.println("Autentificare esuata. Username sau parola invalida.");
        }

        scanner.close();
    }

    private static int getValidChoice(Scanner scanner, int min, int max) {
        int choice = -1;
        while (choice < min || choice > max) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
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
