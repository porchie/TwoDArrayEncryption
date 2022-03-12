import java.util.Arrays;
import java.util.Scanner;

public class MainRunner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice = "";
        while (!choice.equals("q")) {

            System.out.println("What do you want to do[(e)ncrypt or (d)ecrypt]");
            choice = sc.nextLine();
            System.out.println("Enter your shifts and key[row column key]");
            int row = sc.nextInt();
            int col = sc.nextInt();
            String key = sc.nextLine();
            key = key.substring(1);
            TwoDArrayEncryptor cipher = new TwoDArrayEncryptor(row, col, key);

            if(choice.equals("e")) {
                System.out.println("Enter your Message you want to encrypt");
                String message = sc.nextLine();
                String encrypted = cipher.encrypt(message);
                System.out.println("This is the encrypted message:\n" +encrypted);
            }
            if(choice.equals("d")) {
                System.out.println("Enter the encrypted string");
                String encrypted = sc.nextLine();
                System.out.println("Encrpyted String:\n" + encrypted);
                System.out.println("Decrypted String:\n" + cipher.decrypt(encrypted));
            }
        }

        sc.close();
    }


}
