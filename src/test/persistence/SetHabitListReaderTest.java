package persistence;

import model.entries.Habit;
import model.entries.HabitList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SetHabitListReaderTest {

    @Test
    void testParseSetHabitsFile1() {
        try {
            HabitList habits = SetHabitListReader.readHabit(new File("./data/testSetHabitFile1"));
            Habit habit1 = habits.getHabitList().get(0);
            Habit habit2 = habits.getHabitList().get(1);
            assertEquals("play", habit1.getLabel());
            assertFalse(habit1.getIsDone());
            assertEquals("do", habit2.getLabel());
            assertFalse(habit2.getIsDone());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    // check the habitlist file
    @Test
    void testParseSetHabitsFile2() {
        try {
            HabitList habits = SetHabitListReader.readHabit(new File("./data/testSetHabitFile2"));
            assertTrue(habits.getHabitList().isEmpty());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            SetHabitListReader.readHabit(new File("./path/does/not/exist/testSetHabit.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}
