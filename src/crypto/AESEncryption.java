package crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.*;

public class AESEncryption {

    private static final String KEY = "1234567890123456";

    public static void encrypt(Path input, Path output) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(KEY.getBytes(), "AES"));
        Files.write(output, cipher.doFinal(Files.readAllBytes(input)));
    }

    public static void decrypt(Path input, Path output) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(KEY.getBytes(), "AES"));
        Files.write(output, cipher.doFinal(Files.readAllBytes(input)));
    }
}
