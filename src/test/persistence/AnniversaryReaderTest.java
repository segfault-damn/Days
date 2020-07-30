package persistence;

import model.Date;
import model.entries.Anniversary;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AnniversaryReaderTest {
    @Test
    void testParseAnniFile1() {
        try {
            Anniversary anniversary = AnniversaryReader.readAnniversary(new File("./data/testAnniFile1"));
            Date checkDate = new Date(2020,3,14);
            Anniversary checkAnni = new Anniversary(checkDate,"HaHaHa","");
            assertEquals(checkAnni,anniversary);
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseAnniFile2() {
        try {
            Anniversary anniversary = AnniversaryReader.readAnniversary(new File("./data/testAnniFile2"));
            Date checkDate = new Date(2050,2,13);
            Anniversary checkAnni = new Anniversary(checkDate,"I love you","Laisen");
            assertEquals(checkAnni,anniversary);
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
