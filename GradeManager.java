import java.util.HashMap;

// Student Grade Manager - has intentional bugs and code quality issues for testing

public class GradeManager {

    static HashMap students = new HashMap();  // bad: raw type, should use generics

    public static void addStudent(String name, int grade) {
        students.put(name, grade);
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
        else return "F";
    }

    public static void printAllStudents() {
        for (Object key : students.keySet()) {
            System.out.println(key + " - " + students.get(key) + " - " + getGrade((int) students.get(key)));
        }
    }

    public static void removeStudent(String name) {
        students.remove(name);  // bad: no check if student exists, silent failure
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
        return top;  // bug: returns empty string if no students or all scores are 0
    }

    public static void searchStudent(String name) {
        for (Object key : students.keySet()) {
            if (key == name) {  // bug: using == instead of .equals() for String comparison
                System.out.println("Found: " + name + " - " + students.get(key));
                return;
            }
        }
        System.out.println("Student not found");
    }

    public static void main(String[] args) {
        addStudent("Alice", 92);
        addStudent("Bob", 78);
        addStudent("Charlie", 85);
        addStudent("Diana", 60);
        addStudent("Eve", 45);

        System.out.println("All Students:");
        printAllStudents();

        System.out.println("\nClass Average: " + getAverage());
        System.out.println("Top Student: " + getTopStudent());

        System.out.println("\nSearch Bob:");
        searchStudent("Bob");

        removeStudent("Alice");
        System.out.println("\nAfter removing Alice:");
        printAllStudents();
    }
}
