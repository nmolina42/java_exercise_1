import java.util.Scanner;
public class Launcher {
    public static void main(String[] args) {
        System.out.println("Welcome");
        Scanner in = new Scanner(System.in);
        String msg = in.nextLine();
        while (!msg.equals("quit") && !msg.equals("fibo")) {
            System.out.println("Unknown command");
            msg = in.nextLine();
        }
        if (msg.equals("fibo")) {
            String nb = in.nextLine();
            int n = Integer.parseInt(nb);
            int f1 = 1;
            int f2 = 1;
            int fn = n;
            for (int i = 2; i < n; i++){
                fn = f1 + f2;
                f1 = f2;
                f2 = fn;
            }
            System.out.println(fn);
        }
    }
}
