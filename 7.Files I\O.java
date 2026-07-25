import java.io.*;
import java.util.Scanner;

public class StudentFile {
    public static void main(String[] args) {
        String fileName = System.getProperty("java.io.tmpdir") + "/students.txt";
        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter Details of 3 Students:\n");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Student " + i);
                System.out.print("Roll No: ");
                String rollNo = scanner.nextLine().trim();

                System.out.print("Name: ");
                String name = scanner.nextLine().trim();

                System.out.print("Marks: ");
                String marks = scanner.nextLine().trim();


                writer.write(rollNo + "," + name + "," + marks);
                writer.newLine();
                System.out.println();
            }
            System.out.println("Records saved successfully!\n");
        } catch (IOException e) {
            System.out.println("Error saving records: " + e.getMessage());
        }


        System.out.println("----- Student Records -----");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 3) {
                    System.out.println("Roll No : " + details[0]);
                    System.out.println("Name    : " + details[1]);
                    System.out.println("Marks   : " + details[2]);
                    System.out.println("---------------------------");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }


        System.out.print("\nEnter Roll Number to Search: ");
        String searchRoll = scanner.nextLine().trim();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 3 && details[0].equalsIgnoreCase(searchRoll)) {
                    System.out.println("\nRecord Found:");
                    System.out.println("Roll No : " + details[0]);
                    System.out.println("Name    : " + details[1]);
                    System.out.println("Marks   : " + details[2]);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("No student found with Roll No: " + searchRoll);
            }
        } catch (IOException e) {
            System.out.println("Error searching file: " + e.getMessage());
        }
    }
}
