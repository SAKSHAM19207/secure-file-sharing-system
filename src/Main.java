import auth.AuthManager;
import auth.User;
import file.FileManager;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        AuthManager auth = new AuthManager();
        FileManager fm = new FileManager();

        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                // REGISTER
                System.out.print("Username: ");
                String username = sc.nextLine();

                System.out.print("Password: ");
                String password = sc.nextLine();

                System.out.print("Role (ADMIN/USER): ");
                String role = sc.nextLine();

                auth.register(username, password, role);
                System.out.println("✅ User registered successfully!");

            } else if (choice == 2) {
                // LOGIN
                System.out.print("Username: ");
                String username = sc.nextLine();

                System.out.print("Password: ");
                String password = sc.nextLine();

                User user = auth.login(username, password);

                if (user == null) {
                    System.out.println("❌ Invalid credentials.");
                    continue;
                }

                System.out.println("✅ Login successful!");

                // USER MENU
                while (true) {
                    System.out.println("\n1. Upload");
                    System.out.println("2. Download");
                    System.out.println("3. Logout");
                    System.out.print("Choose option: ");

                    int op = sc.nextInt();
                    sc.nextLine();

                    if (op == 1) {
                        System.out.print("Enter file path: ");
                        fm.upload(user.username, sc.nextLine());

                    } else if (op == 2) {
                        System.out.print("Encrypted file path: ");
                        String enc = sc.nextLine();

                        System.out.print("Output file path: ");
                        fm.download(user.username, user.role, enc, sc.nextLine());

                    } else if (op == 3) {
                        System.out.println("🔒 Logged out.");
                        break;
                    } else {
                        System.out.println("❌ Invalid option.");
                    }
                }

            } else if (choice == 3) {
                System.out.println("👋 Exiting system.");
                break;
            } else {
                System.out.println("❌ Invalid option.");
            }
        }

        sc.close();
    }
}
