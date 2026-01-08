package file;

import crypto.AESEncryption;
import util.Logger;
import java.io.*;
import java.nio.file.*;

public class FileManager {

    private static final String STORAGE = "encrypted_files/";
    private static final String META = "metadata.txt";

    public void upload(String user, String filePath) throws Exception {
    Path input = Paths.get(filePath);

    if (!Files.exists(input)) {
        System.out.println("❌ File not found. Please check the path.");
        return;
    }

    Path output = Paths.get(STORAGE + input.getFileName() + ".enc");

    AESEncryption.encrypt(input, output);

    FileWriter fw = new FileWriter(META, true);
    fw.write(new FileMetadata(user, output.getFileName().toString()) + "\n");
    fw.close();

    Logger.log(user + " uploaded " + output.getFileName());
    System.out.println("✅ File uploaded successfully!");
}

    public void download(String user, String role, String encFile, String out) throws Exception {
        if (!authorized(user, role, encFile)) {
            System.out.println("Access denied.");
            return;
        }
        AESEncryption.decrypt(Paths.get(encFile), Paths.get(out));
        Logger.log(user + " downloaded " + encFile);
    }

    private boolean authorized(String user, String role, String encFile) throws IOException {
        if (role.equals("ADMIN")) return true;

        BufferedReader br = new BufferedReader(new FileReader(META));
        String line;
        while ((line = br.readLine()) != null) {
            String[] p = line.split(",");
            if (p[0].equals(user) && p[1].equals(encFile)) {
                br.close();
                return true;
            }
        }
        br.close();
        return false;
    }
}
