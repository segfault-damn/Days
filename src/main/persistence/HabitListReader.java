package persistence;

import model.Date;
import model.entries.Habit;
import model.entries.HabitList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HabitListReader {
    public static final String DELIMITER1 = ",";
    public static final String DELIMITER2 = ";";


    // EFFECTS: returns a list of habitlists parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<HabitList> readHabitLists(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of habitlists
    private static List<HabitList> parseContent(List<String> fileContent) {

        List<HabitList> habitLists = new ArrayList<>();

        for (String line : fileContent) {
            HabitList habitlist = new HabitList();
            ArrayList<String> lineComponents = splitHabit(line);
            for (String habit : lineComponents) {
                ArrayList<String> habitComponents = splitString(habit);
                habitlist.addHabit(parseHabit(habitComponents));
            }
            habitLists.add(habitlist);
        }

        return habitLists;
    }

    private static ArrayList<String> splitHabit(String line) {
        String[] splits = line.split(DELIMITER2);
        return new ArrayList<>(Arrays.asList(splits));
    }

    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER1);
        return new ArrayList<>(Arrays.asList(splits));
    }


    // REQUIRES: components has size 3 where element 0 represents the
    // year, element 1 represents the month, elements 2 represents the
    // day
    // EFFECTS: returns an account constructed from components
    private static Habit parseHabit(List<String> components) {
        String label = components.get(0);
        boolean isDone = Boolean.parseBoolean(components.get(1));
        Habit h = new Habit(label);
        if (isDone) {
            h.flipDone();
        }
        return h;
    }
}
