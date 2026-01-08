package auth;

import crypto.HashUtil;
import java.io.*;

public class AuthManager {

    private static final String USER_FILE = "users.txt";

    public boolean register(String username, String password) throws IOException {
        String hash = HashUtil.hashPassword(password);
        FileWriter fw = new FileWriter(USER_FILE, true);
        fw.write(username + "," + hash + "\n");
        fw.close();
        return true;
    }

    public boolean login(String username, String password) throws IOException {
        String hash = HashUtil.hashPassword(password);
        BufferedReader br = new BufferedReader(new FileReader(USER_FILE));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts[0].equals(username) && parts[1].equals(hash)) {
                br.close();
                return true;
            }
        }
        br.close();
        return false;
    }
}
