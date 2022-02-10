package src;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;
import java.util.*;
import java.util.stream.Collectors;

public class Predict implements src.Command {


    private static class Word {
        private final String word;
        private final Map<String, Integer> occurences = new HashMap<>();

        public Word(String word) {
            this.word = word;
        }

        public void putFollower(String w) {
            this.occurences.put(w, this.occurences.getOrDefault(w, 0) + 1);
        }

        public String predict() {
            if (occurences.isEmpty())
                return null;

            var i = Collections.max(occurences.values());

            var list = this.occurences.keySet().stream().filter(k -> occurences.get(k).equals(i)).toList();

            return list.get(0);
        }
    }

    @Override
    public String name() {
        return "predict";
    }

    @Override
    public boolean run(Scanner sc) {
        System.out.println("Veuillez donner le chemin du fichier à lire ?");
        String pathStr = sc.nextLine();
        Path path = Paths.get(pathStr);
        String fd;
        try {
            fd = java.nio.file.Files.readString(path);
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
            Map<String, Word> words = new HashMap<>();

            var lastWord = Arrays.stream(fd.split(" "))
                    .filter(s -> !s.isBlank())
                    .reduce("", (prev, next) -> {
                        if (!prev.isBlank()) {
                            words.putIfAbsent(prev, new Word(prev));
                            words.get(prev).putFollower(next);
                        }
                        return next;
                    });

            words.putIfAbsent(lastWord, new Word(lastWord));

            var sentence = new ArrayList<>(List.of(word));
            while (sentence.size() < 20) {
                var nextWord = words.get(sentence.get(0)).predict();
                if (nextWord == null)
                    break;
                sentence.add(nextWord);
            }
            System.out.println(String.join(" ", sentence));
            return false;
        } catch (Exception e) {
            System.err.println("Unreadable file: " + e.getMessage());
            return false;
        }
    }
}