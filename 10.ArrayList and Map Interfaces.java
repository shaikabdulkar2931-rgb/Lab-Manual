import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private int rollNo;
    private String name;
    private double percentage;


    public Student(int rollNo, String name, double percentage) {
        this.rollNo = rollNo;
        this.name = name;
        this.percentage = percentage;
    }

    
    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public double getPercentage() {
        return percentage;
    }

    @Override
    public String toString() {
        return "Student{Roll=" + rollNo + ", Name='" + name + "', Percentage=" + percentage + "}";
    }


    public static void main(String[] args) {

        Student s1 = new Student(101, "Rahul", 88.5);
        Student s2 = new Student(102, "Sneha", 91.2);
        Student s3 = new Student(103, "Kiran", 84.8);

        
        List<Student> studentList = new ArrayList<>();
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);


        Map<Integer, Student> studentMap = new HashMap<>();
        studentMap.put(s1.getRollNo(), s1);
        studentMap.put(s2.getRollNo(), s2);
        studentMap.put(s3.getRollNo(), s3);


        System.out.println("Student List:");
        System.out.println("----------------------------------------");
        System.out.printf("%-7s %-8s %-10s\n", "Roll", "Name", "Percentage");
        System.out.println("----------------------------------------");
        for (Student s : studentList) {
            System.out.printf("%-7d %-8s %-10.1f\n", s.getRollNo(), s.getName(), s.getPercentage());
        }


        System.out.println("\nHashMap Entries:");
        System.out.println("----------------------------------------");
        for (Map.Entry<Integer, Student> entry : studentMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " => " + entry.getValue());
        }
    }
}
