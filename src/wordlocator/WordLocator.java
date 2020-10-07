package wordlocator;

import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordLocator implements WordLocatorInterface {
    private File root_file;
    private TreeSet<String> words = new TreeSet<>();
    private TreeSet<String> filepathes = new TreeSet<>();

    private TreeMap<String, TreeMap<String, List<Location>>> words_path_locations = new TreeMap<>();
    public WordLocator(String filepath) throws IOException {
        root_file = new File(filepath);
        readAllFiles(root_file);
    }

    private void readAllFiles(File root) throws IOException {
        if (root.isFile()) {
            String path = root.getPath();
            filepathes.add(path);

            int line = 1;
            int column = 1;

            BufferedReader reader = new BufferedReader(new FileReader(root));
            int c;
            char ch;
            String word = "";
            do {
                c = reader.read();
                ch = (char)c;
                if (ch == '\n') {
                    line++;
                    column = 1;
                }
                else if (WordLocatorInterface.isInWord(ch)) {
                    word += ch;
                }
                else {
                    if (word.length() > 0) {
                        word = word.toLowerCase();
                        if (words.contains(word) == false) {
                            words.add(word);
                            words_path_locations.put(word, new TreeMap<>());

                        }
                        if (words_path_locations.get(word).containsKey(path) == false) {
                            words_path_locations.get(word).put(path, new ArrayList<>());
                        }

                        words_path_locations.get(word).get(path).add(new Location(path, line, column - word.length() - 1));

                    }
                    word = "";
                }
                column++;
            } while (c != -1);
        }
        else if (root.isDirectory()) {
            for (File f : root.listFiles()) {
                readAllFiles(f);
            }
        }
    }

    @Override
    public TreeSet<String> getWords() {
        return words;
    }

    @Override
    public TreeSet<String> getFilepaths() {
        return filepathes;
    }

    @Override
    public int numOccurancesInAllFiles(String word) {
        int count = 0;
        for (Map.Entry<String, List<Location>> entry : words_path_locations.get(word).entrySet()) {
            count += entry.getValue().size();
        }
        return count;
    }

    @Override
    public void printOccurancesInAllFiles(String word) {
        for (Map.Entry<String, List<Location>> entry : words_path_locations.get(word).entrySet()) {
            for (Location l : entry.getValue()) {
                System.out.println(l);
            }
        }
    }

    @Override
    public int numOccurancesInFile(String filepath, String word) {
        return words_path_locations.get(word).get(filepath).size();
    }

    @Override
    public void printOccurancesInFile(String filepath, String word) {
        for (Location l : words_path_locations.get(word).get(filepath)) {
            System.out.println(l);
        }
    }
}
