package model;

import model.entries.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static model.entries.Mood.Cheerful;
import static org.junit.jupiter.api.Assertions.*;

class DayTest {
    private Day testDay;
    private Date testDate;
    private Habit habit1; // Play LOL
    private Habit habit2; // Study for final
    private Habit habit3; // Play Dr.racket
    private Diary diary;
    private TodoEvent event1;
    private TodoEvent event2;
    private TodoEvent event3;
    private TodoEvent event4;
    private Mood testMood;

    private Anniversary testAnni;

    @BeforeEach
    private void setTest() {

        testDate = new Date(2020, 2, 13);
        testDay = new Day(testDate);

        diary = new Diary();
        diary.setContent("Love Laisen");
        testMood = Cheerful;
        habit1 = new Habit("Play LOL");
        habit2 = new Habit("Study for final");
        habit3 = new Habit("Play Dr.racket");

        event1 = new TodoEvent(testDate, "birthday party", 20, 0);
        event2 = new TodoEvent(testDate, "cpsc110 final", 18, 0);
        event3 = new TodoEvent(testDate, "chat with Gregor", 12, 20);
        event4 = new TodoEvent(testDate, "chat with Gregor", 18, 30);


        testDay.getDailyHabitList().addHabit(habit1);
        testDay.getDailyHabitList().addHabit(habit2);
        testDay.getDailyHabitList().addHabit(habit3);

        testAnni = new Anniversary(testDate,"Confession", "Love Laisen");



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
    }

    @Test
    // MODIFIER: this
    // EFFECT: remove mood
    public void testRemoveMood() {

        testDay.setMood(testMood);
        testDay.removeMood();
        assertEquals(Mood.Default,testDay.getMood());
    }

    @Test
    // MODIFIER: this
    // EFFECT: set habitlist
    public void testSetDailyHabitList() {
        HabitList testHabitList = new HabitList();
        testHabitList.addHabit(habit1);
        testHabitList.addHabit(habit2);
        testHabitList.addHabit(habit3);

        testDay.setDailyHabitList(testHabitList);
        assertEquals(testHabitList,testDay.getDailyHabitList());
    }


    @Test
    // MODIFIER: this
    // EFFECT: set anniversary with given label and comment
    public void testSetAnniversary() {

        testDay.setDayAnniversary(testAnni);
        assertEquals("Confession", testDay.getAnniversary().getLabel());
        assertEquals("Love Laisen", testDay.getAnniversary().getComment());
        assertFalse(testDay.getAnniversary().getIsAnniversary());
        testDay.getAnniversary().setAnniversary();
        assertTrue(testDay.getAnniversary().getIsAnniversary());
    }

    @Test
    // MODIFIER: this
    // EFFECT: remove anniversary
    public void testRemoveAnniversary() {
        testDay.setDayAnniversary(testAnni);
        testDay.removeDayAnniversary();
        assertEquals(testDay.getAnniversary().getLabel(),"Confession");
        assertEquals(testDay.getAnniversary().getComment(),"Love Laisen");
        assertFalse(testDay.getAnniversary().getIsAnniversary());

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
    // MODIFIER: this
    // EFFECT: set todoEvent list
    public void testSetTodoEvents() {
        List<TodoEvent> todoEvents = new ArrayList<>();
        todoEvents.add(event1);
        todoEvents.add(event2);
        todoEvents.add(event3);
        testDay.setTodoEvents(todoEvents);
        assertEquals(todoEvents,testDay.getTodoEventList());
    }

    @Test
    // REQUIRE: the event added can not have the same time as other events
    // MODIFIER: this
    // EFFECT: add a todoEvent to the list
    public void testAddEvent() {

        testDay.addEvent(event1);
        assertTrue(testDay.getTodoEventList().contains(event1));
        assertEquals(1, testDay.getTodoEventList().size());

        testDay.addEvent(event2);
        assertTrue(testDay.getTodoEventList().contains(event2));
        assertEquals(2, testDay.getTodoEventList().size());
    }

    @Test
    // MODIFIER: this
    // EFFECT: remove the given event from that list
    public void testRemoveEvent() {
        testDay.addEvent(event1);
        testDay.addEvent(event2);
        testDay.addEvent(event3);
        testDay.addEvent(event4);
        testDay.removeEvent(19,0,event1.getLabel());
        assertTrue(testDay.getTodoEventList().contains(event2));
        assertTrue(testDay.getTodoEventList().contains(event4));
        assertTrue(testDay.getTodoEventList().contains(event1));
        assertTrue(testDay.getTodoEventList().contains(event3));
        assertEquals(4, testDay.getTodoEventList().size());

        testDay.removeEvent(20,30,event1.getLabel());
        assertTrue(testDay.getTodoEventList().contains(event2));
        assertTrue(testDay.getTodoEventList().contains(event4));
        assertTrue(testDay.getTodoEventList().contains(event1));
        assertTrue(testDay.getTodoEventList().contains(event3));
        assertEquals(4, testDay.getTodoEventList().size());

        testDay.removeEvent(20,00,"oh yeah");
        assertTrue(testDay.getTodoEventList().contains(event2));
        assertTrue(testDay.getTodoEventList().contains(event4));
        assertTrue(testDay.getTodoEventList().contains(event1));
        assertTrue(testDay.getTodoEventList().contains(event3));
        assertEquals(4, testDay.getTodoEventList().size());

        testDay.removeEvent(20,0,event1.getLabel());
        assertTrue(testDay.getTodoEventList().contains(event2));
        assertTrue(testDay.getTodoEventList().contains(event4));
        assertFalse(testDay.getTodoEventList().contains(event1));
        assertTrue(testDay.getTodoEventList().contains(event3));
        assertEquals(3, testDay.getTodoEventList().size());

        testDay.removeEvent(18,0,event2.getLabel());
        assertFalse(testDay.getTodoEventList().contains(event2));
        assertTrue(testDay.getTodoEventList().contains(event4));
        assertFalse(testDay.getTodoEventList().contains(event1));
        assertTrue(testDay.getTodoEventList().contains(event3));
        assertEquals(2, testDay.getTodoEventList().size());
    }

    @Test
    // MODIFIER: this
    // EFFECT: set todoEvent list
    public void TestSetTodoEvents() {
        List<TodoEvent> todoEvents = new ArrayList<>();
        todoEvents.add(event1);
        todoEvents.add(event2);
        testDay.setTodoEvents(todoEvents);
        assertEquals(2,testDay.getTodoEventList().size());
        assertEquals(event1,testDay.getTodoEventList().get(0));
        assertEquals(event2,testDay.getTodoEventList().get(1));
    }




    @Test
    // REQUIRE: the given hour and minute should correspond to one event in the list
    // EFFECT: find the Event with given time
    public void testGetEvent() {
        testDay.addEvent(event1);
        testDay.addEvent(event2);
        assertEquals(event1, testDay.getEvent(20, 0,event1.getLabel()));


        testDay.addEvent(event3);
        assertEquals(event3, testDay.getEvent(12, 20,event3.getLabel()));

        assertNull(testDay.getEvent(12, 30,"nothing"));
        assertNull(testDay.getEvent(12, 20,"nothing"));
        assertNull(testDay.getEvent(12, 10,event3.getLabel()));
    }

    @Test
    public void testGetTodoEventList() {
        testDay.addEvent(event1);
        List<TodoEvent> testEventList = testDay.getTodoEventList();
        assertEquals(event1,testEventList.get(0));
        assertTrue(testEventList.contains(event1));
    }


}