package persistence;

import model.entries.Mood;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MoodReaderTest {

    @BeforeEach
    void setUp() {
        MoodReader moodReader = new MoodReader();
    }

    @Test
    void testParseMoodFile() {
        try {
            List<Mood> moods = MoodReader.readMood(new File("./data/testMoodFile"));
            Mood mood1 = moods.get(0);
            Mood mood2 = moods.get(1);
            Mood mood3 = moods.get(2);
            Mood mood4 = moods.get(3);
            Mood mood5 = moods.get(4);
            Mood mood6 = moods.get(5);
            Mood mood7 = moods.get(6);
            Mood mood8 = moods.get(7);
            assertEquals(Mood.Default, mood1);
            assertEquals(Mood.Angry,mood2);
            assertEquals(Mood.Calm,mood3);
            assertEquals(Mood.Cheerful,mood4);
            assertEquals(Mood.Energetic,mood5);
            assertEquals(Mood.Depressed,mood6);
            assertEquals(Mood.Sad,mood7);
            assertEquals(Mood.Default, mood8);
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            MoodReader.readMood(new File("./path/does/not/exist/testDate.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}
