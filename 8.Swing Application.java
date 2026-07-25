import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== STUDENT REGISTRATION FORM ===");
        System.out.println("---------------------------------");

        System.out.print("Enter USN: ");
        String usn = scanner.nextLine().trim();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();

        if (usn.isEmpty() || name.isEmpty()) {
            System.out.println("\n[ERROR]: Please fill in both USN and Name!");
            return;
        }

        System.out.print("Enter Branch (e.g., CSE, ISE, ECE): ");
        String branch = scanner.nextLine().trim();

        System.out.println("\nRegistration Successful!");
        System.out.println("USN: " + usn);
        System.out.println("Name: " + name);
        System.out.println("Branch: " + branch);

        scanner.close();
    } 
} 
