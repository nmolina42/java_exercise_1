import java.util.Scanner;
public class Launcher {
    public static void main(String[] args) {
        System.out.println("Welcome");
        Scanner in = new Scanner(System.in);
        String msg = in.nextLine();
        if (!msg.equals("quit")){
            System.out.println("Unknown command");
        }
    }
}
