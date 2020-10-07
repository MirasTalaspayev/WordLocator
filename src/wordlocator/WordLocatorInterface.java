package wordlocator;

import java.util.TreeSet;

public interface WordLocatorInterface {

    // Note that static methods are 'global' and not not
    // attached to any particular object.... thus they can
    // legally be defined in interfaces

    /**
     * Utility method that checks if a character should be
     * counted as belonging to a word with respect to the
     * application
     *
     * @param ch the character to check
     * @return true if ch should belong to a word
     */
    static boolean isInWord(char ch) {
        // Apostrophe and hyphen cases
        if (ch == 0x2019 || ch == 0x27 || ch == '-')
            return true;
        // Digit cases
        if ('0' <= ch && ch <= '9')
            return true;
        // Alphabetic cases
        ch = Character.toLowerCase(ch);
        return ('a' <= ch && ch <= 'z');
    }

    /**
     * Returns all of the words in all of the files that were processed.
     * Using a TreeSet allows us to go through the values in alphabetical
     * order when iterating
     *
     * @return The words in all of the files that were processed
     */
    TreeSet<String> getWords();

    /**
     * The relative paths (as strings) of the files that were processed.
     * Using a TreeSet allows us to go through the values in alphabetical
     * order when iterating
     *
     * @return The relative paths of the files that were processed
     */
    TreeSet<String> getFilepaths();

    /**
     * Returns the number of occurances of the given word in all of
     * the files that were processed
     *
     * @param word The word for which we are counting occurances
     * @return the number of occurances of the given word in all files
     */
    int numOccurancesInAllFiles(String word);

    /**
     * Prints out the location of each occurances of the given word
     * in all of the files that were processed.  The order in which
     * the locations are printed should first be alphabeticaly
     * ordered by the filepaths, and then by line number and then
     * column number in the individual files.
     *
     * @param word The word for which we are printing occurances
     */
    void printOccurancesInAllFiles(String word);

    /**
     * Returns the number of occurances of the given word in the file
     * with the given filepath
     *
     * @param word The word for which we are counting occurances
     * @return the number of occurances of the given word in the file
     */
    int numOccurancesInFile(String filepath, String word);

    /**
     * Prints out the location of each occurances of the given word
     * in the file with the given filepath.  The order in which
     * the locations are printed should be by line number and then
     * column number in the given file
     *
     * @param word The word for which we are printing occurances
     */
    void printOccurancesInFile(String filepath, String word);


}
