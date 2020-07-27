package model;

import model.entries.Diary;
import model.entries.Habit;
import model.entries.HabitList;
import model.entries.Mood;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DaySetTest {
    private Date date1; //2020.02.13
    private Date date2; //2020.02.17
    private Date date3; //2020.02.26
    private Date date4; //2020.03.20
    private Date date5; //2021.02.28
    private Date date6;

    private Diary diary1;
    private Diary diary2;
    private Diary diary3;


    private Habit habit1;
    private Habit habit2;
    private Habit habit3;
    private HabitList testHabitList;

    private Mood mood1;
    private Mood mood2;


    private DaySet testdaySet;

    @BeforeEach
    public void setTest() {
        testHabitList = new HabitList();

        habit1 = new Habit("Play LOL");
        habit2 = new Habit("Study for final");
        habit3 = new Habit("Play Dr.racket");

        date1 = new Date(2020, 02, 13);
        date2 = new Date(2020, 02, 17);
        date3 = new Date(2020, 02, 26);
        date4 = new Date(2021, 02, 26);
        date5 = new Date(2020, 03, 26);
        date6 = new Date(2020, 02, 13);

        diary1 = new Diary(date1);
        diary2 = new Diary(date2);
        diary3 = new Diary(date3);

        diary1.setTag("Tag1");
        diary3.setTag("Tag1");


        testdaySet = new DaySet();
        testdaySet.getDay(date1);

        mood1 = Mood.Cheerful;
        mood2 = Mood.Calm;

    }

    @Test
    // EFFECT: get a day from the daylist with given date
    //         if that day does not exist, creat a new date in that given list
    public void testGetDay() {
        testdaySet.getDay(date2);

        testdaySet.getDay(date3);
        assertEquals(3, testdaySet.getDays().size());
        assertEquals(2020, testdaySet.getDay(date3).getDate().getYear());
        assertEquals(02, testdaySet.getDay(date3).getDate().getMonth());
        assertEquals(26, testdaySet.getDay(date3).getDate().getDay());
        assertEquals(testHabitList.getHabitList(), testdaySet.getDay(date3).getDailyHabitList().getHabitList());


        testdaySet.getDay(date4);
        assertEquals(4, testdaySet.getDays().size());
        assertEquals(2021, testdaySet.getDay(date4).getDate().getYear());
        assertEquals(02, testdaySet.getDay(date4).getDate().getMonth());
        assertEquals(26, testdaySet.getDay(date4).getDate().getDay());
        assertEquals(testHabitList.getHabitList(), testdaySet.getDay(date4).getDailyHabitList().getHabitList());


        testdaySet.getDay(date5);
        assertEquals(5, testdaySet.getDays().size());
        assertEquals(2020, testdaySet.getDay(date5).getDate().getYear());
        assertEquals(03, testdaySet.getDay(date5).getDate().getMonth());
        assertEquals(26, testdaySet.getDay(date5).getDate().getDay());
        assertEquals(testHabitList.getHabitList(), testdaySet.getDay(date5).getDailyHabitList().getHabitList());
        testdaySet.getDay(date6);

        assertEquals(5, testdaySet.getDays().size());
        assertEquals(2020, testdaySet.getDay(date6).getDate().getYear());
        assertEquals(02, testdaySet.getDay(date6).getDate().getMonth());
        assertEquals(13, testdaySet.getDay(date6).getDate().getDay());
        assertEquals(testHabitList.getHabitList(), testdaySet.getDay(date6).getDailyHabitList().getHabitList());
    }

    @Test
    // EFFECT: get all diary with tag "tag"
    public void testSearchByTag() {
        testdaySet.getDay(date1).setDiary(diary1);
        testdaySet.getDay(date2).setDiary(diary2);
        testdaySet.getDay(date3).setDiary(diary3);
        assertEquals(2, testdaySet.searchByTag("Tag1").size());
        assertTrue(testdaySet.searchByTag("Tag1").contains(diary1));
        assertTrue(testdaySet.searchByTag("Tag1").contains(diary3));

        testdaySet.getDay(date2).getDiary().setTag("Tag1");
        assertEquals(3, testdaySet.searchByTag("Tag1").size());
        assertTrue(testdaySet.searchByTag("Tag1").contains(diary1));
        assertTrue(testdaySet.searchByTag("Tag1").contains(diary2));
        assertTrue(testdaySet.searchByTag("Tag1").contains(diary3));

        testdaySet.getDay(date1).getDiary().setTag("Tag2");
        assertEquals(1, testdaySet.searchByTag("Tag2").size());
        assertTrue(testdaySet.searchByTag("Tag2").contains(diary1));
    }

    @Test
    // MODIFIER: day
    // EFFECT: add one habit and renew all habit in days
    public void testAddDailyHabitList() {
        testdaySet.addDailyHabitList(habit1);
        testdaySet.addDailyHabitList(habit2);
        assertEquals(2, testdaySet.getSetHabitList().getHabitList().size());
        assertTrue(testdaySet.getSetHabitList().getHabitList().contains(habit1));
        assertTrue(testdaySet.getSetHabitList().getHabitList().contains(habit2));

        assertEquals(2, testdaySet.getDay(date1).getDailyHabitList().getHabitList().size());
        assertTrue(testdaySet.getDay(date1).getDailyHabitList().getHabitLabel().contains(habit1.getLabel()));
        assertTrue(testdaySet.getDay(date1).getDailyHabitList().getHabitLabel().contains(habit2.getLabel()));
    }

    @Test
    // MODIFIER: day
    // EFFECT: add one habit and renew all habit in days
    public void testRemoveDailyHabitList() {
        testdaySet.addDailyHabitList(habit1);
        testdaySet.addDailyHabitList(habit2);
        testdaySet.removeDailyHabitList(new Habit(habit2.getLabel()));

        assertEquals(1, testdaySet.getSetHabitList().getHabitList().size());
        assertTrue(testdaySet.getSetHabitList().getHabitList().contains(habit1));
        assertFalse(testdaySet.getSetHabitList().getHabitList().contains(habit2));

        assertEquals(1, testdaySet.getDay(date1).getDailyHabitList().getHabitList().size());
        assertTrue(testdaySet.getDay(date1).getDailyHabitList().getHabitLabel().contains(habit1.getLabel()));
        assertFalse(testdaySet.getDay(date1).getDailyHabitList().getHabitLabel().contains(habit2.getLabel()));
    }

    @Test
    // MODIFIER: day
    // EFFECT: remove one habit and renew all habit in days
    public void testEditDailyHabitList() {
        testdaySet.addDailyHabitList(habit1);
        testdaySet.addDailyHabitList(habit2);
        testdaySet.editDailyHabitList(new Habit(habit2.getLabel()),"Play with Gregor");


        assertTrue(testdaySet.getDay(date4).getDailyHabitList().getHabitLabel().contains(habit2.getLabel()));
        assertFalse(testdaySet.getDay(date4).getDailyHabitList().getHabitLabel().contains("Study for final"));

        assertTrue(testdaySet.getSetHabitList().getHabitLabel().contains(habit2.getLabel()));
        assertFalse(testdaySet.getSetHabitList().getHabitLabel().contains("Study for final"));}

    @Test
    // EFFECT: count the numbers of completed habit in given month and year
    public void testCountHabit() {

        testdaySet.addDailyHabitList(habit1);
        testdaySet.addDailyHabitList(habit2);
        testdaySet.addDailyHabitList(habit3);


        testdaySet.getDay(date1).flipOneHabit(habit1);
        testdaySet.getDay(date1).flipOneHabit(habit2);


        testdaySet.getDay(date2).flipOneHabit(habit2);
        testdaySet.getDay(date2).flipOneHabit(habit3);

        testdaySet.getDay(date3).flipOneHabit(habit2);
        testdaySet.getDay(date3).flipOneHabit(habit3);

        testdaySet.getDay(date4).flipOneHabit(habit1);

        testdaySet.getDay(date5).flipOneHabit(habit2);
        testdaySet.getDay(date5).flipOneHabit(habit3);

        assertEquals(1, testdaySet.countHabit(2020, 02, habit1));
        assertEquals(2, testdaySet.countHabit(2020, 02, habit3));
        assertEquals(3, testdaySet.countHabit(2020, 02, habit2));

    }

    @Test
    // EFFECT: count mood m in the given month and year
    public void testCountMood() {

        testdaySet.getDay(date1).setMood(mood1);
        testdaySet.getDay(date2).setMood(mood1);
        testdaySet.getDay(date3).setMood(mood2);

        testdaySet.getDay(date4).setMood(mood2);
        testdaySet.getDay(date5).setMood(mood1);


        assertEquals(1, testdaySet.countMood(mood2, 2020, 02));
        assertEquals(2, testdaySet.countMood(mood1, 2020, 02));
    }
}
