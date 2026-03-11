import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

class password_strenght_check{
    public static void main(String[] args) {
        
        // For Aesthetic Purposes.

        try {
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            
            String banner = Files.readString(Paths.get("banner.txt"));
            System.out.println(banner);
        } catch (Exception e) {
            // Fallback if the file is missing
            System.out.println("Welcome to Password Checker!"); }

        // For clean terminal output not really necessary but makes it look nicer

        System.out.println("\nIs your password strong enough? Let's find out!\n\n"); // Welcome message
        

        // Main code Start :

        Scanner input = new Scanner(System.in); // Define scanner for using input

        System.out.print("Enter username: ");
        String username = input.nextLine(); // Read user input

        System.out.print("Enter a password: "); // Prompt user for input
        String password = input.nextLine(); // Read user input

        int result = passwordStrengthCheck(username, password); // Call method to check for password strength
        String strengthRating = passwordStrengthRating(result); // Get the strength rating
        
        System.out.println("\nThe password strength is: " + strengthRating); // Print the result
        System.out.println("\nChecking password against WORDLIST......");

         // Check if the password is a common password

        String commonPasswordResult = common_password_check(password);
        System.out.println(commonPasswordResult);
        
        while (true) {
        System.out.print("\nWould you like to generate a strong password? (Y/N) ");
        String generateChoice = input.nextLine();
        if (generateChoice.equalsIgnoreCase("Y")) {
            String generatedPassword = password_generator();
            System.out.println("Generated password: " + generatedPassword);
            
        } 
        else if (generateChoice.equalsIgnoreCase("N")) {
            System.out.println("No password generated. Remember to choose a strong password for better security!\n");
            break;
        } 
        else {
            System.out.println("Invalid choice. Please enter Y or N.");
        } 
        }
    
    }

    public static int passwordStrengthCheck(String username, String password){
        int strength = 0;
        if (password.length() > 8)
        {
            strength++;
        }
        
        // Regular expression for matching/checking

        if (password.matches(".*\\d.*")) { // Check for digits
            strength++;
        }
        if (password.matches(".*[A-Z].*")) { // Check for uppercase letters
            strength++;
        }
        if (password.matches(".*[a-z].*")) { // Check for lower letters
            strength++;
        }
        if (password.matches(".*[^A-Za-z0-9].*")) { // Check for special characters
            strength++;
        }

        if ((!username.equals(password))) {
            strength++; // If the password is the same as username
        }
        
        if (username.equals(password)) {
            return 0; 
        }
        return strength;
    }
    
    public static String passwordStrengthRating(int strength) { 
        
        // Convert strength score to a rating
        if (strength <= 3) {
            return "Weak";
        } 
        else if (strength <= 5) {
            return "Medium";
        } 
        else if (strength == 6) {
            return "Strong";
        } 
        else {
            return "None"; 
        }
        
    }
    
    public static String common_password_check(String password) {
        Path path = Paths.get("rockyou.txt");

        // Processes 14+ million lines of password directly from disk to save memory
        try (Stream<String> lines = Files.lines(path)) {
            
            // .anyMatch returns true as soon as it finds a match and STOPS reading the file.
            boolean isCommon = lines.anyMatch(line -> line.equals(password));

            if (isCommon) {
                return "\n*** WARNING: THIS IS A KNOWN COMMON PASSWORD! ***";
            } else {
                return "\n*** SUCCESS: Password not found in common database. ***";
            }

        } catch (IOException e) {
            return "Error: Database file 'rockyou.txt' or any WORDLIST not found.";
        }
    }

    public static String password_generator() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        StringBuilder password = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < 12; i++) {
            password.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        int result = passwordStrengthCheck("user", password.toString());
        String strengthRating = passwordStrengthRating(result);
        System.out.println("Generated password strength: " + strengthRating);

        return password.toString();
    }
}   

