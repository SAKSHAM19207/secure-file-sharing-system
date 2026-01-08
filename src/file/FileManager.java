package file;

import crypto.AESEncryption;
import java.nio.file.*;

public class FileManager {

    private static final String STORAGE = "encrypted_files/";

    public void upload(String filePath) throws Exception {
        Path input = Paths.get(filePath);
        Path output = Paths.get(STORAGE + input.getFileName() + ".enc");
        AESEncryption.encryptFile(input, output);
    }

    public void download(String encPath, String outputPath) throws Exception {
        AESEncryption.decryptFile(Paths.get(encPath), Paths.get(outputPath));
    }
}
