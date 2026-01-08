import auth.*;
import file.FileManager;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        AuthManager auth = new AuthManager();
        FileManager fm = new FileManager();

        System.out.println("1. Register\n2. Login");
        int ch = sc.nextInt(); sc.nextLine();

        System.out.print("Username: ");
        String u = sc.nextLine();
        System.out.print("Password: ");
        String p = sc.nextLine();

        User user;

        if (ch == 1) {
            System.out.print("Role (ADMIN/USER): ");
            String role = sc.nextLine();
            auth.register(u, p, role);
            user = new User(u, role);
        } else {
            user = auth.login(u, p);
            if (user == null) {
                System.out.println("Login failed");
                return;
            }
        }

        System.out.println("1. Upload\n2. Download");
        int op = sc.nextInt(); sc.nextLine();

        if (op == 1) {
            System.out.print("File path: ");
            fm.upload(user.username, sc.nextLine());
        } else {
            System.out.print("Encrypted file path: ");
            String enc = sc.nextLine();
            System.out.print("Output file path: ");
            fm.download(user.username, user.role, enc, sc.nextLine());
        }
        sc.close();
    }
}
