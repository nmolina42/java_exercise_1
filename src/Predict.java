package src;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Predict implements src.Command {


    @Override
    public String name() {
        return "predict";
    }

    @Override
    public boolean run(Scanner sc) {
        System.out.println("Veuillez donner le chemin du fichier à lire ?");
        String pathStr = sc.nextLine();
        Path path = Paths.get(pathStr);
        try {
            String fd = java.nio.file.Files.readString(path);
            System.out.println("Veuillez maintenant entrer un mot :");
            var word = sc.nextLine().toLowerCase();
            fd = fd.toLowerCase(Locale.ROOT);
            fd = fd.toLowerCase()
                    .replaceAll("[.!?,]", " ")
                    .replaceAll(" {2}", " ");
            if (!fd.contains(word)) {
                System.out.println("Le mot n'est dans le fichier");
                return false;
            }
            String[] listWord = fd.split(" ");
            Map<String, String> wordsFreq = new HashMap<String, String>();
            for (String words : listWord) {
                if (wordsFreq.get(words) == null) {
                    wordsFreq.put(words, getNextFreq(words, listWord));
                }
            }
            System.out.print(word);
            for (int i = 0; i < 19; i++) {
                System.out.print(" " + wordsFreq.get(word));
                word = wordsFreq.get(word);

            }
        }
        catch(IOException e) {
            System.out.println("Unreadable file: " + e.toString());
        }
        System.out.println("");
        return false;
    }
    public String getNextFreq(String word,String[] content){
        ArrayList<String> tmp = new ArrayList<>();
        for (int i = 0;i < content.length;i++){
            if (content[i].equals(word) && i != content.length - 1){
                tmp.add(content[i+1]);
             }
          }
        Map<Object, Integer> frequencyMap = tmp.stream()
                .collect(toMap(
                        s -> s, // key is the word
                        s -> 1, // value is 1
                        Integer::sum));
        List<Object> res = tmp.stream()
                .sorted(comparing(frequencyMap::get).reversed()) // sort by descending frequency
                .distinct() // take only unique values
                .limit(1)   // take only the first 1
                .collect(toList()); // put it in a returned list

          if (res.size() != 0) {
              return res.get(0).toString();
          }
         return "";
      }
}
