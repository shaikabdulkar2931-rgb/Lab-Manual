import java.util.ArrayList;
import java.util.List;

class Student {
    int rollNo;
    String name;
    String department;
    int marks;

    public Student(int rollNo, String name, String department, int marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.department = department;
        this.marks = marks;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();


        students.add(new Student(101, "Rahul", "CSE", 95));
        students.add(new Student(102, "Sneha", "ISE", 91));


        System.out.printf("%-10s %-15s %-15s %-10s\n", "RollNo", "Name", "Department", "Marks");
        System.out.println("-----------------------------------------------------");

        for (Student s : students) {
            System.out.printf("%-10d %-15s %-15s %-10d\n", s.rollNo, s.name, s.department, s.marks);
        }
    }
}
