package persistence;

import model.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class WriterTest {
    private static final String TEST_FILE = "./data/testDate.txt";
    private Writer testWriter;
    private Date date;

//    @BeforeEach
//    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
//        testWriter = new DateWriter(new File(TEST_FILE));
//        date = new Date(2000, 06, 27);
//    }
//
//    @Test
//    void testWriteDate() {
//        // save date to file
//        testWriter.write(date);
//        testWriter.close();
//
//        // read back
//        try {
//            Date readBack = DateReader.readDates(new File(TEST_FILE));
//            assertEquals(date,readBack);
//        } catch (IOException e) {
//            fail("IOException should not have been thrown");
//        }
//    }
}
