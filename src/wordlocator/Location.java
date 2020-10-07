package wordlocator;

public class Location {

    private String filepath;
    private int line;
    private int column;

    public Location(String fpath, int ln, int col) {
        filepath = fpath;
        line = ln;
        column = col;
    }

    public String toString() {
        return "file: \"" + filepath
                + "\"; line:" + line + "; column:" + column;
    }

}
