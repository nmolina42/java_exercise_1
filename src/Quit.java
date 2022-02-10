package src;
import java.util.Scanner;
public class Quit implements src.Command {
    @Override
    public String name() {
        return "quit";
    }

    @Override
    public boolean run(Scanner sc) {
        return true;
    }

}
