package students_package;

import java.util.ArrayList;
import java.util.Scanner;

public class Main_class {
    public static void main(String[] args) {
        
        DatabaseManager_class.createTable();
        ArrayList<Student_class> loaded = DatabaseManager_class.loadAll();
        StudentManager_class manager = new StudentManager_class(loaded);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n======= MENU =======");
            System.out.println("1 - Přidat studenta");
            System.out.println("2 - Přidat známku");
            System.out.println("3 - Odebrat studenta");
            System.out.println("4 - Zobrazit informace o studentovi");
            System.out.println("5 - Použijt dovednost");
            System.out.println("6 - Seznam všech studentů");
            System.out.println("7 - Průměr skupiny");
            System.out.println("8 - Počet studentů ve skupině");
            System.out.println("9 - Exit");
            System.out.print("Vyberte si možnost: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> {
                    System.out.print("Jméno: ");
                    String fn = scanner.nextLine();
                    System.out.print("Příjmení: ");
                    String ln = scanner.nextLine();
                    System.out.print("Rok narození: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Skupina (telecom/cyber): ");
                    String group = scanner.nextLine();
                    manager.addStudent(fn, ln, year, group);
                }
                case 2 -> {
                    System.out.print("ID Studenta: ");
                    int id = scanner.nextInt();
                    System.out.print("Známka (1-5): ");
                    int grade = scanner.nextInt();
                    manager.addGrade(id, grade);
                }
                case 3 -> {
                    System.out.print("ID Studenta: ");
                    int id = scanner.nextInt();
                    manager.removeStudent(id);
                }
                case 4 -> {
                    System.out.print("ID Studenta: ");
                    int id = scanner.nextInt();
                    manager.showStudentInfo(id);
                }
                case 5 -> {
                    System.out.print("ID Studenta: ");
                    int id = scanner.nextInt();
                    manager.executeSkill(id);
                }
                case 6 -> manager.printSortedByLastName();
                case 7 -> {
                    System.out.print("Skupina (telecom/cyber):");
                    String group = scanner.nextLine();
                    double avg = manager.getGroupAverage(group);
                    System.out.println("Průměr skupiny: " + avg);
                }
                case 8 -> {
                    System.out.print("Skupina (telecom/cyber): ");
                    String group = scanner.nextLine();
                    int count = manager.getGroupCount(group);
                    System.out.println("Počet studentů ve skupině: " + count);
                }
                case 9 -> {
                    System.out.println("Data jsou uložená");
                    DatabaseManager_class.saveAll(manager.getAllStudents());
                    running = false;
                }
                default -> System.out.println("Neplatná možnost!");
            }
        }

        scanner.close();
    }
}
