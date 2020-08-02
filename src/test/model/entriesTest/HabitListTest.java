package model.entriesTest;

import model.entries.Habit;
import model.entries.HabitList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HabitListTest {

    private HabitList testHabitList;
    private Habit habit1; // Play LOL
    private Habit habit2; // Study for final
    private Habit habit3; // Play Dr.racket

    @BeforeEach
    public void setUp() {
        habit1 = new Habit("Play LOL");
        habit2 = new Habit("Study for final");
        habit3 = new Habit("Play Dr.racket");
        testHabitList = new HabitList();
    }

    @Test
    // REQUIRE: this habit list should not have duplicated habit
    // MODIFIER:this
    // EFFECT:add a habit to habit list
    public void testAddHabit() {
        testHabitList.addHabit(habit1);
        assertTrue(testHabitList.getHabitList().contains(habit1));
        assertEquals(1, testHabitList.getHabitList().size());
        testHabitList.addHabit(habit2);
        assertTrue(testHabitList.getHabitList().contains(habit2));
        assertTrue(testHabitList.getHabitList().contains(habit1));
        assertEquals(2, testHabitList.getHabitList().size());
    }


    @Test
    // MODIFIER: this
    // EFFECT: remove the habit from the list
    public void testRemoveHabit() {
        testHabitList.addHabit(habit1);
        testHabitList.addHabit(habit2);
        testHabitList.removeHabit(habit1);
        assertTrue(testHabitList.getHabitList().contains(habit2));
        assertFalse(testHabitList.getHabitList().contains(habit1));
        assertEquals(1, testHabitList.getHabitList().size());
    }

    @Test
    //REQUIRE: l should be an existing habit's label in the list
    //EFFECT: get habit with given label
    public void getHabit() {
        testHabitList.addHabit(habit1);
        testHabitList.addHabit(habit2);
        assertEquals("",testHabitList.getHabit("Play Dr.racket").getLabel());
        assertFalse(testHabitList.getHabit("Play Dr.racket").getIsDone());
        testHabitList.addHabit(habit3);
        assertEquals(habit3, testHabitList.getHabit("Play Dr.racket"));
        assertEquals(habit1, testHabitList.getHabit("Play LOL"));


    }

    @Test
    // REQUIRE: habit label can not pre-exist in the list
    // MODIFIER: this
    // EFFECT: edit habit
    public void testEditOneHabit() {
        testHabitList.addHabit(habit1);
        testHabitList.editOneHabit(habit1, "Play LOL with Gregor");
        assertEquals("Play LOL with Gregor", testHabitList.getHabit("Play LOL with Gregor").getLabel());
    }

    @Test
    public void testGetHabitLabel() {
        testHabitList.addHabit(habit1);
        testHabitList.addHabit(habit2);
        testHabitList.addHabit(habit3);
        assertEquals(3, testHabitList.getHabitLabel().size());
        assertTrue(testHabitList.getHabitLabel().contains("Play LOL"));
        assertTrue(testHabitList.getHabitLabel().contains("Study for final"));
        assertTrue(testHabitList.getHabitLabel().contains("Play Dr.racket"));

    }

}


