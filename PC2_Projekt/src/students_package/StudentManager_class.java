package students_package;

import java.util.ArrayList;
import java.util.Comparator;

public class StudentManager_class {
    protected ArrayList<Student_class> students = new ArrayList<>();
    protected int nextId = 1;

    public StudentManager_class() {
        
    }

    public StudentManager_class(ArrayList<Student_class> existing) {
        this.students = existing;
        this.nextId = existing.stream()
                .mapToInt(Student_class::getId)
                .max()
                .orElse(0) + 1;
    }

    public void addStudent(String firstName, String lastName, int birthYear, String group) {
        Student_class student = null;

        if (group.equalsIgnoreCase("telecom")) {
            student = new TelecomStudent_class(nextId, firstName, lastName, birthYear);
        } else if (group.equalsIgnoreCase("cyber")) {
            student = new CyberStudent_class(nextId, firstName, lastName, birthYear);
        } else {
            System.out.println("Neznámá skupina: " + group);
            return;
        }

        students.add(student);
        System.out.println("Přidaný student s ID: " + nextId);
        nextId++;
    }

    public void addGrade(int id, int grade) {
        Student_class s = findById(id);
        if (s != null) {
            s.addGrade(grade);
            System.out.println("Přidaná známka.");
        } else {
            System.out.println("Student nebyl nalezen.");
        }
    }

    public void removeStudent(int id) {
        Student_class s = findById(id);
        if (s != null) {
            students.remove(s);
            System.out.println("Student je odstraněný .");
        } else {
            System.out.println("Student nebyl nalezen.");
        }
    }

    public Student_class findById(int id) {
        for (Student_class s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public void showStudentInfo(int id) {
        Student_class s = findById(id);
        if (s != null) {
            System.out.println("ID: " + s.getId());
            System.out.println("Jméno: " + s.getFullName());
            System.out.println("Rok narození: " + s.getBirthYear());
            System.out.println("Průměr: " + s.getAverage());
        } else {
            System.out.println("Student nebyl nalezen.");
        }
    }

    public void executeSkill(int id) {
        Student_class s = findById(id);
        if (s != null) {
            s.useSkill();
        } else {
            System.out.println("Student nebyl nalezen.");
        }
    }

    public void printSortedByLastName() {
        students.stream()
                .sorted(Comparator.comparing(s -> s.lastName))
                .forEach(s -> {
                    System.out.printf("ID: %d | %s | rok narození: %d | průměr: %.2f%n",
                            s.getId(), s.getFullName(), s.getBirthYear(), s.getAverage());
                });
    }

    public double getGroupAverage(String group) {
        double sum = 0;
        int count = 0;
        for (Student_class s : students) {
            if ((group.equalsIgnoreCase("telecom") && s instanceof TelecomStudent_class)
                    || (group.equalsIgnoreCase("cyber") && s instanceof CyberStudent_class)) {
                sum += s.getAverage();
                count++;
            }
        }
        return count > 0 ? sum / count : 0;
    }

    public int getGroupCount(String group) {
        int count = 0;
        for (Student_class s : students) {
            if ((group.equalsIgnoreCase("telecom") && s instanceof TelecomStudent_class)
                    || (group.equalsIgnoreCase("cyber") && s instanceof CyberStudent_class)) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<Student_class> getAllStudents() {
        return students;
    }
}
