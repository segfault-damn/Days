package persistence;


import model.entries.Mood;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// MoodReader reads moods from saved file
public class MoodReader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns  a list of moods parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<Mood> readMood(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of moods
    private static List<Mood> parseContent(List<String> fileContent) {

        List<Mood> moods = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            moods.add(parseDiary(lineComponents));
        }

        return moods;
    }

    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }


    // REQUIRES: components has size 1 which represents the mood using string
    // EFFECTS: returns an mood constructed from components
    private static Mood parseDiary(List<String> components) {
        String s = components.get(0);
        Mood mood = Mood.Default;
        parseDiaryHelper(s,mood);
        return mood;
    }

    private static void parseDiaryHelper(String s, Mood mood) {
        switch (s) {
            case ("Cheerful"):
                mood = Mood.Cheerful;
                break;
            case ("Sad"):
                mood = Mood.Sad;
                break;
            case ("Angry"):
                mood = Mood.Angry;
                break;
            case ("Depressed"):
                mood = Mood.Depressed;
                break;
            case ("Calm"):
                mood = Mood.Calm;
                break;
            case ("Energetic"):
                mood = Mood.Energetic;
                break;
            case ("Default"):
                mood = Mood.Default;
                break;
        }
    }
}
