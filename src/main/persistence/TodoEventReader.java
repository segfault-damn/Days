package persistence;

import model.Date;
import model.entries.TodoEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodoEventReader {
    public static final String DELIMITER1 = ",";
    public static final String DELIMITER2 = ";";

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
                if (eventComponents.size() == 6) {
                    todoEventList.add(parseTodoEvent(eventComponents));
                }
            }
            todoEventLists.add(todoEventList);
        }

        return todoEventLists;
    }

    private static ArrayList<String> splitHabit(String line) {
        String[] splits = line.split(DELIMITER2);
        return new ArrayList<>(Arrays.asList(splits));
    }

    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER1);
        return new ArrayList<>(Arrays.asList(splits));
    }


    // REQUIRES: components has size 2 where element 0 represents the
    // label, element 1 represents the whether it is done.
    // EFFECTS: returns an habit constructed from components
    private static TodoEvent parseTodoEvent(List<String> components) {
        int year = Integer.parseInt(components.get(0));
        int month = Integer.parseInt(components.get(1));
        int day = Integer.parseInt(components.get(2));

        String label = components.get(3);
        int hour = Integer.parseInt(components.get(4));
        int min = Integer.parseInt(components.get(5));

        Date date = new Date(year,month,day);
        return new TodoEvent(date,label,hour,min);
    }
}