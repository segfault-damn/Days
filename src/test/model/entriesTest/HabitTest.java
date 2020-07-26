package model.entriesTest;

import model.entries.Habit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class HabitTest {
    private Habit testHabit;


    @BeforeEach
    public void setUp() {

        testHabit = new Habit("Play LOL");
    }


    @Test
    // MODIFIER: this
    // EFFECT: mark habit as done if it's not done or mark
    //         it as undone if it's done
    public void testFlipDone() {
        assertFalse(testHabit.getIsDone());
        testHabit.flipDone();
        assertTrue(testHabit.getIsDone());
        testHabit.flipDone();
        assertFalse(testHabit.getIsDone());

    }

    @Test
    // MODIFIER: this
    // EFFECT: edit habit
    public void testEditHabit() {
        assertEquals("Play LOL", testHabit.getLabel());
        testHabit.editHabit("Study for final");
        assertEquals("Study for final", testHabit.getLabel());
    }
}
