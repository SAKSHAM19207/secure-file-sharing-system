package auth;

import crypto.HashUtil;
import java.io.*;

public class AuthManager {

    private static final String USER_FILE = "users.txt";

    public void register(String username, String password, String role) throws IOException {
        String hash = HashUtil.hashPassword(password);
        FileWriter fw = new FileWriter(USER_FILE, true);
        fw.write(username + "," + hash + "," + role + "\n");
        fw.close();
    }

    public User login(String username, String password) throws IOException {
        String hash = HashUtil.hashPassword(password);
        BufferedReader br = new BufferedReader(new FileReader(USER_FILE));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts[0].equals(username) && parts[1].equals(hash)) {
                br.close();
                return new User(parts[0], parts[2]);
            }
        }
        br.close();
        return null;
    }
}
