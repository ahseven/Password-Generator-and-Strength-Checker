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
        
        if (username.equals(password)) { // Password same as username, set to 0 (Weak)
            return 0; 
        }
        return strength;
    }
    
    public static String passwordStrengthRating(int strength) { 
        
        
        // Define the codes once at the top of your method
        String RED = "\033[1;31m";    // Bold Red
        String YELLOW = "\033[1;33m"; // Bold Yellow
        String GREEN = "\033[1;32m";  // Bold Green
        String RESET = "\033[0m";     // Back to normal

        // Convert strength score to a rating
        if (strength <= 2) {
            return RED + "Weak" + RESET;
        } 
        else if (strength <= 4) {
            return YELLOW + "Medium" + RESET;
        } 
        else if (strength == 5) {
            return GREEN + "Strong" + RESET;
        } 
        else {
            return "None"; 
        }
    }
    public static String common_password_check(String password) {

        // Coloring text 
        String RED_BOLD = "\033[1;31m";
        String GREEN_BOLD = "\033[1;32m";
        String RESET = "\033[0m";

        // Wordlist to use
        Path path = Paths.get("rockyou.txt");

        // Processes 14+ million lines of password directly from disk to save memory
        try (Stream<String> lines = Files.lines(path)) {
            
            // .anyMatch returns true as soon as it finds a match and STOPS reading the file.
            boolean isCommon = lines.anyMatch(line -> line.equals(password));

           if (isCommon) {
            // Returning the string with Red formatting "baked in"
            return "\n" + RED_BOLD + "*** WARNING: THIS IS A KNOWN COMMON PASSWORD! STOP USING IT AND CREATE A STRONGER ONE! ***" + RESET;
            } else {
                // Returning the string with Green formatting "baked in"
                return "\n" + GREEN_BOLD + "*** SUCCESS: Password not found in common database. ***" + RESET;
            }
            } catch (IOException e) {
            return RED_BOLD + "Error: Database file 'rockyou.txt' not found." + RESET;
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
