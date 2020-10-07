import wordlocator.WordLocator;
import wordlocator.WordLocatorInterface;

import java.io.IOException;

public class Tester {
    public static void main(String[] args) throws IOException {

        WordLocatorInterface locator = new WordLocator("Input");

        System.out.println("count: " + locator.getWords().size());
        System.out.println(locator.getWords());
        System.out.println();

        System.out.println("count: " + locator.getFilepaths().size());
        System.out.println(locator.getFilepaths());
        System.out.println();

        System.out.println("count: " + locator.numOccurancesInAllFiles("and"));
        locator.printOccurancesInAllFiles("and");
        System.out.println();

        System.out.println("count: " + locator.numOccurancesInFile("Input\\Music\\Beatles\\Money.txt", "that's"));
        locator.printOccurancesInFile("Input\\Music\\Beatles\\Money.txt", "that's");

    }
}
