import java.util.Scanner;

public class StringCharacterCounter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        int vowels = 0;
        int consonants = 0;
        int digits = 0;
        int specialChars = 0;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            
            if (Character.isLetter(ch)) {
                char lowerCh = Character.toLowerCase(ch);
                if (lowerCh == 'a' || lowerCh == 'e' || lowerCh == 'i' || lowerCh == 'o' || lowerCh == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            } 
            
            else if (Character.isDigit(ch)) {
                digits++;
            } 
           
            else if (!Character.isWhitespace(ch)) {
                specialChars++;
            }
        }

        
        System.out.println("\n--- Character Count Results ---");
        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
        System.out.println("Digits: " + digits);
        System.out.println("Special Characters: " + specialChars);

        scanner.close();
    }
}
