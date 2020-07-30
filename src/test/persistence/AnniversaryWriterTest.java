package persistence;

import model.Date;
import model.entries.Anniversary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AnniversaryWriterTest {
    private static final String TEST_FILE = "./data/testAnni.txt";
    private AnniversaryWriter testWriter;
    private Date date;
    private Anniversary anniversary;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new AnniversaryWriter(new File(TEST_FILE));
        date = new Date(2000, 06, 27);
        anniversary = new Anniversary(date,"Save","Good job Sakura!");
    }

    @Test
    void testWriteAnni() {
        // save date to file
        testWriter.write(anniversary);
        testWriter.close();

        // read back
        try {
            Anniversary readBack = AnniversaryReader.readAnniversary(new File(TEST_FILE));
            assertEquals(anniversary,readBack);
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
