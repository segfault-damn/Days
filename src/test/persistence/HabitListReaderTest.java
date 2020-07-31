package persistence;

import model.entries.Habit;
import model.entries.HabitList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class HabitListReaderTest {
    @Test
    void testHabitListReaderFile1() {
        try {
            List<HabitList> habitlists = HabitListReader.readHabitLists(new File("./data/testHabitListFile1"));
            HabitList habitList1 = habitlists.get(0);
            Habit habit1 = new Habit("do");
            Habit habit2 = new Habit("play");
            habit2.flipDone();
            Habit habit3 = new Habit("listen");
            assertEquals(habit1, habitList1.getHabitList().get(0));
            assertEquals(habit2, habitList1.getHabitList().get(1));
            assertEquals(habit3, habitList1.getHabitList().get(2));

            HabitList habitList2 = habitlists.get(1);
            habit1 = new Habit("do");
            habit1.flipDone();
            habit2 = new Habit("play");
            habit3 = new Habit("listen");
            assertEquals(habit1, habitList2.getHabitList().get(0));
            assertEquals(habit2, habitList2.getHabitList().get(1));
            assertEquals(habit3, habitList2.getHabitList().get(2));
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }


}
