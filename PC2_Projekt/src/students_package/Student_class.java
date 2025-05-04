package students_package;

import java.util.ArrayList;

public abstract class Student_class {
    protected int id;
    protected String firstName;
    protected String lastName;
    protected int birthYear;
    protected ArrayList<Integer> grades = new ArrayList<>();

    public Student_class(int id, String firstName, String lastName, int birthYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }

    public void addGrade(int grade) {
        if (grade >= 1 && grade <= 5) {
            grades.add(grade);
        }
    }

    public double getAverage() {
        if (grades.isEmpty()) return 0;
        int sum = 0;
        for (int g : grades) sum += g;
        return (double) sum / grades.size();
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public ArrayList<Integer> getGrades() {
        return grades;
    }

    public abstract void useSkill();
}
