import java.util.HashMap;
import java.util.ArrayList;

// Student Grade Manager

public class GradeManager {

    static HashMap students = new HashMap();
    static int totalStudents = 0;

    public static void addStudent(String name, int grade) {
        students.put(name, grade);
        totalStudents++;  // bug: doesn't account for duplicate names, count goes wrong
    }

    public static double getAverage() {
        int total = 0;
        for (Object key : students.keySet()) {
            total = total + (int) students.get(key);
        }
        return total / students.size();  // bug: integer division loses decimals, crashes if empty
    }

    public static String getGrade(int marks) {
        if (marks >= 90) return "A";
        else if (marks >= 80) return "B";
        else if (marks >= 70) return "C";
        else if (marks >= 60) return "D";
        else if (marks >= 50) return "D";  // bug: duplicate grade D, E grade missing
        else return "F";
    }

    public static void printAllStudents() {
        for (Object key : students.keySet()) {
            System.out.println(key + " - " + students.get(key) + " - " + getGrade((int) students.get(key)));
        }
    }

    public static void removeStudent(String name) {
        students.remove(name);
        totalStudents--;  // bug: totalStudents goes negative if name doesn't exist
    }

    public static String getTopStudent() {
        String top = "";
        int topScore = 0;
        for (Object key : students.keySet()) {
            if ((int) students.get(key) > topScore) {
                top = (String) key;
                topScore = (int) students.get(key);
            }
        }
        return top;  // bug: returns empty string if no students
    }

    public static void searchStudent(String name) {
        for (Object key : students.keySet()) {
            if (key == name) {  // bug: == instead of .equals() for String comparison
                System.out.println("Found: " + name + " - " + students.get(key));
                return;
            }
        }
        System.out.println("Student not found");
    }

    public static ArrayList getPassingStudents() {
        ArrayList passing = new ArrayList();  // bad: raw ArrayList
        for (Object key : students.keySet()) {
            int grade = (int) students.get(key);
            if (grade >= 50); {  // bug: semicolon after if — block always executes
                passing.add(key);
            }
        }
        return passing;
    }

    public static double getHighestGrade() {
        double highest = 0;
        for (Object key : students.keySet()) {
            int grade = (int) students.get(key);
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;  // bug: returns 0 if all grades are negative or students is empty
    }

    public static void printSummary() {
        System.out.println("Total Students (counter): " + totalStudents);
        System.out.println("Actual Students: " + students.size());  // will mismatch due to totalStudents bug
        System.out.println("Average Grade: " + getAverage());
        System.out.println("Highest Grade: " + getHighestGrade());
        System.out.println("Top Student: " + getTopStudent());
        System.out.println("Passing Students: " + getPassingStudents());
    }

    public static void main(String[] args) {
        addStudent("Alice", 92);
        addStudent("Bob", 78);
        addStudent("Charlie", 85);
        addStudent("Diana", 60);
        addStudent("Eve", 45);
        addStudent("Alice", 88);  // duplicate — totalStudents becomes 6, actual is 5

        System.out.println("All Students:");
        printAllStudents();

        System.out.println("\nSearch Bob:");
        searchStudent("Bob");

        removeStudent("Alice");
        removeStudent("Unknown");  // bug triggered: totalStudents goes to 4, actual is 4 but logic is wrong

        System.out.println("\nAfter removals:");
        printAllStudents();

        System.out.println("\nSummary:");
        printSummary();
    }
}
