import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class PasswordGenerator {

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+<>?/";

    public static String generatePassword(int length, boolean useSymbols) {
        String allowedChars = UPPERCASE + LOWERCASE + NUMBERS;
        if (useSymbols) {
            allowedChars += SYMBOLS;
        }

        Random rand = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(index));
        }
        return password.toString();
    }

    public static void savePasswordToFile(String password) {
        try (FileWriter writer = new FileWriter("passwords.txt", true)) {
            writer.write(password + "\n");
            System.out.println("✅ Password saved to passwords.txt");
        } catch (IOException e) {
            System.out.println("❌ Failed to save password.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Password Generator ===");
        System.out.print("Enter desired password length: ");
        int length = sc.nextInt();
        sc.nextLine();  // clear buffer

        System.out.print("Include symbols? (yes/no): ");
        boolean useSymbols = sc.nextLine().equalsIgnoreCase("yes");

        String password = generatePassword(length, useSymbols);
        System.out.println("🔐 Generated Password: " + password);

        System.out.print("Do you want to save this password to a file? (yes/no): ");
        String save = sc.nextLine();
        if (save.equalsIgnoreCase("yes")) {
            savePasswordToFile(password);
        }

        sc.close();
    }
}