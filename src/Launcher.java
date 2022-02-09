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
        while (!msg.equals("quit")) {
            msg = in.nextLine();
            if (msg.equals("fibo")) {
                System.out.println("Quel est le nombre ou le chiffre ?");
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
            else if (msg.equals("freq")){
                System.out.println("Veuillez donner le chemin du fichier à lire ?");
                String pathStr = in.nextLine();
                Path path = Paths.get(pathStr);
                try{
                    String fd = java.nio.file.Files.readString(path);
                    fd = fd.toLowerCase(Locale.ROOT);
                    String[] words = fd.split(" ");
                    List<String> listWords = Arrays.asList(words);
                    Map<Object, Integer> frequencyMap = listWords.stream()
                            .collect(Collectors.toMap(
                                    s -> s, // key is the word
                                    s -> 1, // value is 1
                                    Integer::sum));
                    List<Object> finalWords = listWords.stream()
                            .sorted(Comparator.comparing(frequencyMap::get).reversed()) // sort by descending frequency
                            .distinct() // take only unique values
                            .limit(3)   // take only the first 10
                            .collect(Collectors.toList()); // put it in a returned list
                    for (int i = 0; i < 3; i ++){
                        System.out.print(finalWords.get(i)+ " ");
                    }
                    System.out.println("");
                }
                catch (IOException e){
                    System.out.println("Unreadable file: " + e.toString());
                }
            }
            else
                System.out.println("Unknown command");
        }

    }
}
