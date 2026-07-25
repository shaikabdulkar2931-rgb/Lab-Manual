import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;


    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return String.format("%-5d %-15s %-10s $%.2f", id, name, department, salary);
    }


    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();


        employees.add(new Employee(101, "Rahul", "CSE", 55000.00));
        employees.add(new Employee(102, "Sneha", "ECE", 62000.00));
        employees.add(new Employee(103, "Kiran", "CSE", 48000.00));
        employees.add(new Employee(104, "Divya", "MECH", 51000.00));
        employees.add(new Employee(105, "Arjun", "ECE", 70000.00));


        System.out.println("=== Average Salary per Department ===");
        Map<String, Double> avgSalary = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
        avgSalary.forEach((dept, avg) -> System.out.printf("%s : %.2f\n", dept, avg));


        double totalSalary = employees.stream()
                .map(Employee::getSalary)
                .reduce(0.0, Double::sum);
        System.out.printf("\nTotal Salary Paid : %.2f\n", totalSalary);


        long cseCount = employees.stream()
                .filter(e -> "CSE".equalsIgnoreCase(e.getDepartment()))
                .count();
        System.out.println("Number of CSE Employees : " + cseCount);


        Optional<Employee> highestPaid = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
        highestPaid.ifPresent(e -> System.out.println("Highest Paid : " + e.getName() + " (" + e.getSalary() + ")"));
    }
}
