import java.io.IOException;
import java.nio.file.*;
import java.util.*;

// import java.util.stream.IntStream;

class password_strenght_check{
    public static void main(String[] args) {

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Is your password strong enough? Let's find out!"); // Welcome message
        System.out.println(" \n\n"); // Welcome message
        Scanner input = new Scanner(System.in); // Define scanner for using input


        System.out.print("Enter username: ");
        String username = input.nextLine(); // Read user input

        System.out.print("Enter a password: "); // Prompt user for input
        String password = input.nextLine(); // Read user input

        int result = passwordStrengthCheck(username, password); // Call method to check for password strength
        String strengthRating = passwordStrengthRating(result); // Get the strength rating
        System.out.println(" ");
        System.out.println("The password strength is: " + strengthRating); // Print the result

         // Check if the password is a common password
        while (true) {
        System.out.print("\nWould you like to generate a strong password? (Y/N) ");
        String generateChoice = input.nextLine();
        if (generateChoice.equalsIgnoreCase("Y")) {
            String generatedPassword = password_generator();
            System.out.println("Generated strong password: " + generatedPassword);
            // int checkGeneratedPasswordStrength = passwordStrengthCheck(username, generatedPassword);
            
        } 
        else if (generateChoice.equalsIgnoreCase("N")) {
            System.out.println("No password generated. Remember to choose a strong password for better security!");
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
        if (password.matches(".*\\d.*")) {
            strength++;
        }
        if (password.matches(".*[A-Z].*")) {
            strength++;
        }
        if (password.matches(".*[^A-Za-z0-9].*")) { // Check for special characters
            strength++;
        }

        if ((!username.equals(password))) {
            strength++; // If the password contains the username, set strength to 0
        }
        
        if (username.equals(password)) {
            return 0; 
        }
        return strength;
    }
    
    public static String passwordStrengthRating(int strength) {

        if (strength <= 2) {
            return "Weak";
        } 
        else if (strength <= 4) {
            return "Medium";
        } 
        else if (strength == 5) {
            return "Strong";
        } 
        else {
            return "None"; 
        }
        
    }

    public static String common_password_check(String password) {
        try {
            List<String> commonPasswords = Files.readAllLines(Paths.get("rockyou.txt")); 
            // IntStream.range(0, Math.min(commonPasswords.size(), 5000))
            //         .forEach(i -> System.out.println((i + 1) + " " + commonPasswords.get(i)));
        if (commonPasswords.contains(password)) {
            return "***THIS PASSWORD IS A COMMON PASSWORD. PLEASE CHOOSE A DIFFERENT or STRONG PASSWORD.***";
        } else {return "***THIS PASSWORD is NOT Common, Very Good!***";}
        
        } catch (IOException e) {
            return "Error reading common passwords file.";
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



