package modelTest;

import model.Date;
import model.Day;
import model.entries.Diary;
import model.entries.Habit;
import model.entries.Mood;
import model.entries.TodoEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.entries.Mood.Cheerful;
import static org.junit.jupiter.api.Assertions.*;

class DayTest {
    private Day testDay;
    private Date testDate;
    private Habit habit1; // Play LOL
    private Habit habit2; // Study for final
    private Habit habit3; // Play Dr.racket
    private Diary diary;
    private Mood testMood;
    private TodoEvent event1;
    private TodoEvent event2;
    private TodoEvent event3;
    private TodoEvent event4;

    @BeforeEach
    private void setTest() {
        testDay = new Day(testDate);
        event1 = new TodoEvent(testDate, "birthday party", 20, 00);
        event2 = new TodoEvent(testDate, "cpsc110 final", 18, 00);
        event3 = new TodoEvent(testDate, "chat with Gregor", 12, 20);
        event4 = new TodoEvent(testDate, "chat with Gregor", 18, 30);
        diary = new Diary(testDate);
        diary.setContent("Love Laisen");
        testMood = Cheerful;
        habit1 = new Habit("Play LOL");
        habit2 = new Habit("Study for final");
        habit3 = new Habit("Play Dr.racket");
        testDate = new Date(2020, 2, 13);
        testDay.getDailyHabitList().addHabit(habit1);
        testDay.getDailyHabitList().addHabit(habit2);
        testDay.getDailyHabitList().addHabit(habit3);


    }

    @Test
    // MODIFIER: this
    // EFFECT: set mood to given mood
    public void testSetMood() {
        testDay.setMood(testMood);
        assertEquals(Cheerful, testDay.getMood());
    }

    @Test
    // MODIFIER: this
    // EFFECT: set diary with given diary
    public void testSetDiary() {
        testDay.setDiary(diary);
        assertEquals(diary, testDay.getDiary());
        assertEquals(diary.getDate(),testDay.getDate());
    }

    @Test
    // MODIFIER: this
    // EFFECT: remove mood
    public void testRemoveMood() {

        testDay.setMood(testMood);
        testDay.removeMood();
        assertNull(testDay.getMood());
    }

    @Test
    // MODIFIER: this
    // EFFECT: set anniversary with given label and comment
    public void testSetAnniversary() {
        testDay.setAnniversary("Confession", "Love Laisen");
        assertEquals("Confession", testDay.getAnniversary().getLabel());
        assertEquals("Love Laisen", testDay.getAnniversary().getComment());

        testDay.setAnniversary("Confession to a man", "Love Gregor");
        assertEquals("Confession to a man", testDay.getAnniversary().getLabel());
        assertEquals("Love Gregor", testDay.getAnniversary().getComment());
    }

    @Test
    // MODIFIER: this
    // EFFECT: remove anniversary
    public void testRemoveAnniversary() {
        testDay.setAnniversary("Confession", "Love Laisen");
        testDay.removeAnniversary();
        assertNull(testDay.getAnniversary());

    }

    @Test
    // MODIFIER: flip one habit's status
    // EFFECT: flip the chosen habit in the habit list
    public void testFlipOneHabit() {
        testDay.flipOneHabit(habit1);
        assertTrue(testDay.getDailyHabitList().getHabit("Play LOL").getIsDone());
        assertFalse(testDay.getDailyHabitList().getHabit("Study for final").getIsDone());
        assertFalse(testDay.getDailyHabitList().getHabit("Play Dr.racket").getIsDone());
        testDay.flipOneHabit(habit2);
        assertTrue(testDay.getDailyHabitList().getHabit("Play LOL").getIsDone());
        assertTrue(testDay.getDailyHabitList().getHabit("Study for final").getIsDone());
        assertFalse(testDay.getDailyHabitList().getHabit("Play Dr.racket").getIsDone());

        testDay.flipOneHabit(habit1);
        testDay.flipOneHabit(habit3);
        assertFalse(testDay.getDailyHabitList().getHabit("Play LOL").getIsDone());
        assertTrue(testDay.getDailyHabitList().getHabit("Study for final").getIsDone());
        assertTrue(testDay.getDailyHabitList().getHabit("Play Dr.racket").getIsDone());

    }

    @Test
    // REQUIRE: the event added can not have the same time as other events
    // MODIFIER: this
    // EFFECT: add a todoEvent to the list
    public void testAddEvent() {

        testDay.addEvent(event1);
        assertTrue(testDay.getTodoEvents().contains(event1));
        assertEquals(1, testDay.getTodoEvents().size());

        testDay.addEvent(event2);
        assertTrue(testDay.getTodoEvents().contains(event2));
        assertEquals(2, testDay.getTodoEvents().size());
    }

    @Test
    // MODIFIER: this
    // EFFECT: remove the given event from that list
    public void testRemoveEvent() {
        testDay.addEvent(event1);
        testDay.addEvent(event2);
        testDay.addEvent(event3);
        testDay.addEvent(event4);
        testDay.removeEvent(20,00);
        assertTrue(testDay.getTodoEvents().contains(event2));
        assertTrue(testDay.getTodoEvents().contains(event4));
        assertFalse(testDay.getTodoEvents().contains(event1));
        assertTrue(testDay.getTodoEvents().contains(event3));
        assertEquals(3, testDay.getTodoEvents().size());

        testDay.removeEvent(18,00);
        assertFalse(testDay.getTodoEvents().contains(event2));
        assertTrue(testDay.getTodoEvents().contains(event4));
        assertFalse(testDay.getTodoEvents().contains(event1));
        assertTrue(testDay.getTodoEvents().contains(event3));
        assertEquals(2, testDay.getTodoEvents().size());
    }

    @Test
    // REQUIRE: the given hour and minute should correspond to one event in the list
    // EFFECT: find the Event with given time
    public void testGetEvent() {
        testDay.addEvent(event1);
        testDay.addEvent(event2);
        assertEquals(event1, testDay.getEvent(20, 00));


        testDay.addEvent(event3);
        assertEquals(event3, testDay.getEvent(12, 20));

        assertNull(testDay.getEvent(12, 30));
    }
}