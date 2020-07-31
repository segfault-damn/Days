package persistence;

import model.entries.Habit;
import model.entries.Mood;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetHabitListReader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns  a list of diaries parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<Habit> readHabit(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of diary
    private static List<Habit> parseContent(List<String> fileContent) {

        List<Habit> setHabits = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            setHabits.add(parseDiary(lineComponents));
        }

        return setHabits;
    }

    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }


    // REQUIRES: components has size 3 where element 0 represents the
    // year, element 1 represents the month, elements 2 represents the
    // day, element 3 represents the content, elements 4 represents the tag
    // EFFECTS: returns an diary constructed from components
    private static Habit parseDiary(List<String> components) {
        String label = components.get(0);
        boolean isDone = Boolean.getBoolean(components.get(1));
        Habit h = new Habit(label);
        if (isDone) {
            h.flipDone();
        }
        return h;
    }
}
