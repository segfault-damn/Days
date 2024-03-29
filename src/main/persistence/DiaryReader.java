package persistence;

import exceptions.DateErrorException;
import model.Date;
import model.entries.Diary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// DiaryReader reads diaries from saved file
public class DiaryReader {
    public static final String DELIMITER = ",";

    // construct a diary reader
    public DiaryReader() {
    }

    // EFFECTS: returns  a list of diaries parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<Diary> readDiary(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of diaries
    private static List<Diary> parseContent(List<String> fileContent) {

        List<Diary> diaries = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            diaries.add(parseDiary(lineComponents));
        }

        return diaries;
    }

    // split the given String
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }


    // REQUIRES: components has size 2 where element 0 represents the
    // content, elements 1 represents the tag
    // EFFECTS: returns an diary constructed from components
    private static Diary parseDiary(List<String> components) {
        String content = components.get(0);
        String tag = components.get(1);

        Diary diary = new Diary();
        diary.setContent(content);
        diary.setTag(tag);
        return diary;
    }
}
