package persistence;

import model.Date;
import model.entries.Anniversary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// DateReader reads dates from saved file
public class DateReader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of dates parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<Date> readDates(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of dates
    private static List<Date> parseContent(List<String> fileContent) {

        List<Date> dates = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            dates.add(parseDate(lineComponents));
        }

        return dates;
    }

    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }


    // REQUIRES: components has size 3 where element 0 represents the
    // year, element 1 represents the month, elements 2 represents the
    // day
    // EFFECTS: returns a date constructed from components
    private static Date parseDate(List<String> components) {
        int year = Integer.parseInt(components.get(0));
        int month = Integer.parseInt(components.get(1));
        int day = Integer.parseInt(components.get(2));
        return new Date(year, month, day);
    }
}
