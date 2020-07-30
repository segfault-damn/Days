package persistence;

import model.Date;
import model.entries.Anniversary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnniversaryReader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of diary parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<Anniversary> readAnniversary(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of anniversary
    private static List<Anniversary> parseContent(List<String> fileContent) {

        List<Anniversary> anniversaries = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            anniversaries.add(parseAnni(lineComponents));
        }

        return anniversaries;
    }

    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }


    // REQUIRES: components has size 5 where element 0 represents the
    // year, element 1 represents the month, elements 2 represents the
    // day, elements 3 represent the label, elements 4 represent the comment
    // elements 5 represent the whether the anniversary is working.
    // EFFECTS: returns an anniversary constructed from components
    private static Anniversary parseAnni(List<String> components) {
        int year = Integer.parseInt(components.get(0));
        int month = Integer.parseInt(components.get(1));
        int day = Integer.parseInt(components.get(2));
        String label = components.get(3);
        String comment = components.get(4);
        boolean isAnni = Boolean.parseBoolean(components.get(5));
        Date date = new Date(year,month,day);
        Anniversary anniversary = new Anniversary(date,label,comment);
        if (isAnni) {
            anniversary.setAnniversary();
        }
        return anniversary;
    }
}
