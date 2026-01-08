import auth.AuthManager;
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

        boolean ok = (ch == 1) ? auth.register(u, p) : auth.login(u, p);
        if (!ok) return;

        System.out.println("1. Upload\n2. Download");
        int op = sc.nextInt(); sc.nextLine();

        if (op == 1) {
            fm.upload(sc.nextLine());
        } else {
            fm.download(sc.nextLine(), sc.nextLine());
        }
        sc.close();
    }
}
