package persistence;

import model.entries.HabitList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HabitListReaderTest {

    @BeforeEach
    void setUp() {
        HabitListReader habitListReader = new HabitListReader();
    }

    @Test
    void testHabitListReaderFile1() {
        try {
            List<HabitList> habitlists = HabitListReader.readHabitLists(new File("./data/testHabitListFile1"));
            HabitList habitList1 = habitlists.get(0);


            assertEquals("do", habitList1.getHabitList().get(0).getLabel());
            assertFalse(habitList1.getHabitList().get(0).getIsDone());
            assertEquals("play", habitList1.getHabitList().get(1).getLabel());
            assertTrue(habitList1.getHabitList().get(1).getIsDone());
            assertEquals("listen", habitList1.getHabitList().get(2).getLabel());
            assertFalse(habitList1.getHabitList().get(2).getIsDone());

            HabitList habitList2 = habitlists.get(1);
            assertEquals("do", habitList2.getHabitList().get(0).getLabel());
            assertTrue(habitList2.getHabitList().get(0).getIsDone());
            assertEquals("play", habitList2.getHabitList().get(1).getLabel());
            assertFalse(habitList2.getHabitList().get(1).getIsDone());
            assertEquals("listen", habitList2.getHabitList().get(2).getLabel());
            assertFalse(habitList2.getHabitList().get(2).getIsDone());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    // Read the habitlist file when sethabitlist is empty
    @Test
    void testHabitListReaderFile2() {
        try {
            List<HabitList> habitlists = HabitListReader.readHabitLists(new File("./data/testHabitListFile2"));
            HabitList habitList1 = habitlists.get(0);


            assertTrue(habitList1.getHabitList().isEmpty());

            HabitList habitList2 = habitlists.get(1);
            assertTrue(habitList2.getHabitList().isEmpty());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            HabitListReader.readHabitLists(new File("./path/does/not/exist/testHabitLists.txt"));
        } catch (IOException e) {
            // expected
        }
    }


}
