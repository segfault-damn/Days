package persistence;

import model.entries.Habit;
import model.entries.HabitList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// HabitListReader reads habitlists from saved file
public class HabitListReader {
    public static final String DELIMITER1 = ",";
    public static final String DELIMITER2 = ";";

    // construct a habit list reader
    public HabitListReader() {
    }

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
                if (habitComponents.size() == 2) {
                    habitlist.addHabit(parseHabit(habitComponents));
                }
            }
            habitLists.add(habitlist);
        }

        return habitLists;
    }

    // split the given String with Delimiter2
    private static ArrayList<String> splitHabit(String line) {
        String[] splits = line.split(DELIMITER2);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // split the given String with Delimiter1
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER1);
        return new ArrayList<>(Arrays.asList(splits));
    }


    // REQUIRES: components has size 2 where element 0 represents the
    // label, element 1 represents the whether it is done.
    // EFFECTS: returns an habit constructed from components
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
