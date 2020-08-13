package persistence;

import model.Date;
import model.Day;
import model.entries.*;
import model.entries.Mood;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WriterTest {
    private static final String TESTDATE_FILE = "./data/testDate.txt";
    private static final String TESTANNI_FILE = "./data/testAnni.txt";
    private static final String TESTMOOD_FILE = "./data/testMood.txt";
    private static final String TESTDIARY_FILE = "./data/testDiary.txt";
    private static final String TESTHABITS_FILE = "./data/testHabits.txt";
    private static final String TESTEVENTS_FILE = "./data/testEvents.txt";
    private static final String TESTSETHABIT_FILE = "./data/testSetHabit.txt";

    private Writer testDateWriter;
    private Writer testAnniWriter;
    private Writer testMoodWriter;
    private Writer testHabitsWriter;
    private Writer testDiaryWriter;
    private Writer testEventsWriter;
    private Writer testSetHabitWriter;

    private Date date1;
    private Date date2;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testDateWriter = new Writer(new File(TESTDATE_FILE));
        testAnniWriter = new Writer(new File(TESTANNI_FILE));
        testMoodWriter = new Writer(new File(TESTMOOD_FILE));
        testDiaryWriter = new Writer(new File(TESTDIARY_FILE));
        testHabitsWriter = new Writer(new File(TESTHABITS_FILE));
        testEventsWriter = new Writer(new File(TESTEVENTS_FILE));
        testSetHabitWriter = new Writer(new File(TESTSETHABIT_FILE));
        date1 = new Date(2000, 06, 26);
        date2 = new Date(2000, 10, 29);
    }

    @Test
    void testWriteDate() {
        // save date to file
        testDateWriter.write(date1);
        testDateWriter.write(date2);
        testDateWriter.close();

        // read back
        try {
            List<Date> readBack = DateReader.readDates(new File(TESTDATE_FILE));
            Date readBackDate1 = readBack.get(0);
            Date readBackDate2 = readBack.get(1);
            assertEquals(2000,readBackDate1.getYear());
            assertEquals(6,readBackDate1.getMonth());
            assertEquals(26,readBackDate1.getDay());
            assertEquals(2000,readBackDate2.getYear());
            assertEquals(10,readBackDate2.getMonth());
            assertEquals(29,readBackDate2.getDay());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriteAnni() {
        Anniversary anni1 = new Anniversary(" "," ");
        anni1.setAnniversary();
        Anniversary anni2 = new Anniversary("Birthday", "Hahaha");
        // save date to file
        testAnniWriter.write(anni1);
        testAnniWriter.write(anni2);
        testAnniWriter.close();

        // read back
        try {
            List<Anniversary> readBack = AnniversaryReader.readAnniversary(new File(TESTANNI_FILE));
            Anniversary readBackAnni1 = readBack.get(0);

            Anniversary readBackAnni2 = readBack.get(1);
//            assertEquals(2000,readBackAnni1.getDate().getYear());
//            assertEquals(6,readBackAnni1.getDate().getMonth());
//            assertEquals(26,readBackAnni1.getDate().getDay());
            assertEquals(" ",readBackAnni1.getLabel());
            assertEquals(" ",readBackAnni1.getComment());
            assertTrue(readBackAnni1.getIsAnniversary());

//
            assertEquals("Birthday",readBackAnni2.getLabel());
            assertEquals("Hahaha",readBackAnni2.getComment());
            assertFalse(readBackAnni2.getIsAnniversary());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriteMood() {
        Mood mood1 = Mood.Cheerful;
        Mood mood2 = Mood.Default;
        // save date to file
        testMoodWriter.write(mood1);
        testMoodWriter.write(mood2);
        testMoodWriter.close();

        // read back
        try {
            List<Mood> readBack = MoodReader.readMood(new File(TESTMOOD_FILE));
            Mood readBackMood1 = readBack.get(0);
            Mood readBackMood2 = readBack.get(1);
            assertEquals(Mood.Cheerful,readBackMood1);
            assertEquals(Mood.Default,readBackMood2);

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriteDiary() {
        Diary diary1 = new Diary();
        diary1.setTag("Good");
        diary1.setContent("");
        Diary diary2 = new Diary();
        diary2.setContent("Lmao");
        diary2.setTag("No tag");
        // save date to file
        testDiaryWriter.write(diary1);
        testDiaryWriter.write(diary2);
        testDiaryWriter.close();

        // read back
        try {
            List<Diary> readBack = DiaryReader.readDiary(new File(TESTDIARY_FILE));
            Diary readBackDiary1 = readBack.get(0);
            Diary readBackDiary2 = readBack.get(1);
//            assertEquals(2000,readBackDiary1.getDate().getYear());
//            assertEquals(6,readBackDiary1.getDate().getMonth());
//            assertEquals(26,readBackDiary1.getDate().getDay());
            assertEquals(" ",readBackDiary1.getContent());
            assertEquals("Good",readBackDiary1.getTag());

//            assertEquals(2000,readBackDiary2.getDate().getYear());
//            assertEquals(10,readBackDiary2.getDate().getMonth());
//            assertEquals(29,readBackDiary2.getDate().getDay());
            assertEquals("Lmao",readBackDiary2.getContent());
            assertEquals("No tag",readBackDiary2.getTag());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriteHabitList() {
        Habit habit1 = new Habit("play");
        Habit habit2 = new Habit("study");
        Habit habit3 = new Habit("play");
        Habit habit4 = new Habit("study");
        HabitList dailyHabitList1 = new HabitList();
        HabitList dailyHabitList2 = new HabitList();
        dailyHabitList1.addHabit(habit1);
        dailyHabitList1.addHabit(habit2);
        dailyHabitList1.getHabit("play").flipDone();
        dailyHabitList2.addHabit(habit3);
        dailyHabitList2.addHabit(habit4);
        dailyHabitList2.getHabit("study").flipDone();

        // save date to file
        testHabitsWriter.write(dailyHabitList1);
        testHabitsWriter.write(dailyHabitList2);
        testHabitsWriter.close();

        // read back
        try {
            List<HabitList> readBack = HabitListReader.readHabitLists(new File(TESTHABITS_FILE));
            HabitList readBackHabits1 = readBack.get(0);
            HabitList readBackHabits2 = readBack.get(1);
            Habit readBackHabit1 = readBackHabits1.getHabitList().get(0);
            Habit readBackHabit2 = readBackHabits1.getHabitList().get(1);
            assertEquals("play",readBackHabit1.getLabel());
            assertTrue(readBackHabit1.getIsDone());
            assertEquals("study",readBackHabit2.getLabel());
            assertFalse(readBackHabit2.getIsDone());

            Habit readBackHabit3 = readBackHabits2.getHabitList().get(0);
            Habit readBackHabit4 = readBackHabits2.getHabitList().get(1);
            assertEquals("play",readBackHabit3.getLabel());
            assertFalse(readBackHabit3.getIsDone());
            assertEquals("study",readBackHabit4.getLabel());
            assertTrue(readBackHabit4.getIsDone());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriteSetHabitList() {
        Habit habit1 = new Habit("play");
        Habit habit2 = new Habit("study");

        // save date to file
        testSetHabitWriter.write(habit1);
        testSetHabitWriter.write(habit2);
        testSetHabitWriter.close();

        // read back
        try {
            HabitList readBack = SetHabitListReader.readHabit(new File(TESTSETHABIT_FILE));
            Habit readBackHabit1 = readBack.getHabitList().get(0);
            Habit readBackHabit2 = readBack.getHabitList().get(1);
            assertEquals("play",readBackHabit1.getLabel());
            assertFalse(readBackHabit1.getIsDone());
            assertEquals("study",readBackHabit2.getLabel());
            assertFalse(readBackHabit2.getIsDone());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriteTodoEvent() {
        TodoEvent todoEvent1 = new TodoEvent("play",17,30);
        TodoEvent todoEvent2 = new TodoEvent("study",15,30);
        TodoEvent todoEvent3 = new TodoEvent("lunch",12,50);
        List<TodoEvent> todoEventList1 = new ArrayList<>();
        List<TodoEvent> todoEventList2 = new ArrayList<>();

        todoEventList1.add(todoEvent1);
        todoEventList1.add(todoEvent2);
        todoEventList2.add(todoEvent3);

        Day day1 = new Day(date1);
        Day day2 = new Day(date2);

        day1.setTodoEvents(todoEventList1);
        day2.setTodoEvents(todoEventList2);

        // save date to file
        testEventsWriter.write(day1);
        testEventsWriter.write(day2);
        testEventsWriter.close();

        // read back
        try {
            List<List<TodoEvent>> readBack = TodoEventReader.readEvent(new File(TESTEVENTS_FILE));
            List<TodoEvent> readBackEvents1 = readBack.get(0);
            List<TodoEvent> readBackEvents2 = readBack.get(1);
            TodoEvent readBackEvent1 = readBackEvents1.get(0);
            TodoEvent readBackEvent2 = readBackEvents1.get(1);
            TodoEvent readBackEvent3 = readBackEvents2.get(0);
            assertEquals("play",readBackEvent1.getLabel());
//            assertEquals(2000,readBackEvent1.getDate().getYear());
//            assertEquals(6,readBackEvent1.getDate().getMonth());
//            assertEquals(26,readBackEvent1.getDate().getDay());
            assertEquals(17,readBackEvent1.getHour());
            assertEquals(30,readBackEvent1.getMin());

            assertEquals("study",readBackEvent2.getLabel());
//            assertEquals(2000,readBackEvent2.getDate().getYear());
//            assertEquals(6,readBackEvent2.getDate().getMonth());
//            assertEquals(26,readBackEvent2.getDate().getDay());
            assertEquals(15,readBackEvent2.getHour());
            assertEquals(30,readBackEvent2.getMin());

            assertEquals("lunch",readBackEvent3.getLabel());
//            assertEquals(2000,readBackEvent3.getDate().getYear());
//            assertEquals(10,readBackEvent3.getDate().getMonth());
//            assertEquals(29,readBackEvent3.getDate().getDay());
            assertEquals(12,readBackEvent3.getHour());
            assertEquals(50,readBackEvent3.getMin());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
