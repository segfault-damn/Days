package persistence;

import exceptions.DateErrorException;
import model.Date;
import model.entries.TodoEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TodoEvent reads list of events from saved file
public class TodoEventReader {
    public static final String DELIMITER1 = ",";
    public static final String DELIMITER2 = ";";

    // construct TodoEvent Reader
    public TodoEventReader() {
    }

    // EFFECTS: returns  a list of diaries parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<List<TodoEvent>> readEvent(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of list of todoEvent
    private static List<List<TodoEvent>> parseContent(List<String> fileContent) {

        List<List<TodoEvent>> todoEventLists = new ArrayList<>();

        for (String line : fileContent) {
            List<TodoEvent> todoEventList = new ArrayList<>();
            ArrayList<String> lineComponents = splitHabit(line);
            for (String habit : lineComponents) {
                ArrayList<String> eventComponents = splitString(habit);
                if (eventComponents.size() == 3) {
                    todoEventList.add(parseTodoEvent(eventComponents));
                }
            }
            todoEventLists.add(todoEventList);
        }

        return todoEventLists;
    }

    // split the given String with delimiter2
    private static ArrayList<String> splitHabit(String line) {
        String[] splits = line.split(DELIMITER2);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // split the given String with delimiter1
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER1);
        return new ArrayList<>(Arrays.asList(splits));
    }


    // REQUIRES: components has size 6 where element 0 represents the
    // year, element 1 represents the month, element 2 represents the day,
    // element 3 represents the label, element 4 represents the hour,
    // element 5 represents the minute.
    // EFFECTS: returns a todoEvent constructed from components
    private static TodoEvent parseTodoEvent(List<String> components) {
//        int year = Integer.parseInt(components.get(0));
//        int month = Integer.parseInt(components.get(1));
//        int day = Integer.parseInt(components.get(2));

        String label = components.get(0);
        int hour = Integer.parseInt(components.get(1));
        int min = Integer.parseInt(components.get(2));

        return new TodoEvent(label, hour, min);
    }
}
