package persistence;

import model.entries.Habit;
import model.entries.HabitList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// SetHabitListReader reads sethabitlist from saved file
public class SetHabitListReader {
    public static final String DELIMITER = ",";

    // construct a set habit list
    public SetHabitListReader() {
    }

    // EFFECTS: returns  a list of diaries parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static HabitList readHabit(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of diary
    private static HabitList parseContent(List<String> fileContent) {

        HabitList setHabits = new HabitList();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            if (lineComponents.size() == 2) {
                setHabits.addHabit(parseHabit(lineComponents));
            }
        }

        return setHabits;
    }

    // split the given String
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }


    // REQUIRES: components has size 1 where element 0 represents the
    // label
    // EFFECTS: returns an habit constructed from components
    private static Habit parseHabit(List<String> components) {
        String label = components.get(0);
        return new Habit(label);
    }
}
