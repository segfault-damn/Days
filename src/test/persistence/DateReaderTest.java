package persistence;

import model.Date;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DateReaderTest {
    @Test
    void testParseDateFile1() {
        try {
            List<Date> dates = DateReader.readDates(new File("./data/testDateFile1"));
            Date date1 = dates.get(0);
            Date date2 = dates.get(1);
            assertEquals(2020, date1.getYear());
            assertEquals(3, date1.getMonth());
            assertEquals(14, date1.getDay());
            assertEquals(2050, date2.getYear());
            assertEquals(2, date2.getMonth());
            assertEquals(13, date2.getDay());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseDateFile2() {
        try {
            List<Date> dates = DateReader.readDates(new File("./data/testDateFile2"));
            Date date1 = dates.get(0);
            Date date2 = dates.get(1);
            assertEquals(2000, date1.getYear());
            assertEquals(6, date1.getMonth());
            assertEquals(26, date1.getDay());
            assertEquals(2000, date2.getYear());
            assertEquals(10, date2.getMonth());
            assertEquals(29, date2.getDay());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            DateReader.readDates(new File("./path/does/not/exist/testDate.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}
