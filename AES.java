import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;

public class AES {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Get input from user
        System.out.println("Enter your message:");
        String message = scanner.nextLine();

        // Generate a random AES key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // 128-bit key
        SecretKey secretKey = keyGenerator.generateKey();

        // Encrypt the message
        byte[] encryptedMessage = encrypt(message, secretKey.getEncoded());

        // Decrypt the encrypted message
        String decryptedMessage = decrypt(encryptedMessage, secretKey.getEncoded());

        // Print the results
        System.out.println("Original Message: " + message);
        System.out.println("Encrypted Message: " + bytesToHex(encryptedMessage));
        System.out.println("Decrypted Message: " + decryptedMessage);

        scanner.close();
    }

    public static byte[] encrypt(String message, byte[] keyBytes) throws Exception {
        SecretKey key = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(message.getBytes());
    }

    public static String decrypt(byte[] encryptedMessage, byte[] keyBytes) throws Exception {
        SecretKey key = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(encryptedMessage);
        return new String(decryptedBytes);
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }
}
