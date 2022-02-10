import src.Command;
import src.Fibo;
import src.Freq;
import src.Quit;
import src.Predict;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Welcome");
        Scanner in = new Scanner(System.in);
        String msg = "";
        Fibo cmdFibo = new Fibo();
        Quit cmdQuit = new Quit();
        Freq cmdFreq = new Freq();
        Predict cmdPredict = new src.Predict();
        List<Command> listeCommand;
        listeCommand = new ArrayList<>();
        listeCommand.add(cmdFibo);
        listeCommand.add(cmdQuit);
        listeCommand.add(cmdFreq);
        listeCommand.add(cmdPredict);
        Boolean bool = false;
        Boolean check = false;
        while (bool == false) {
            msg = in.nextLine();
            check = false;
            for(Command command : listeCommand){
                if (msg.equals(command.name())){
                    bool = command.run(in);
                    check = true;
                    break;
                }
            }
            if (!check)
                System.out.println("Unknown command");
        }
        in.close();
    }
}
