package persistence;

import model.entries.Anniversary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnniversaryReaderTest {

    @BeforeEach
    void setUp() {
        AnniversaryReader anniversaryReader = new AnniversaryReader();
    }

    @Test
    void testParseAnniFile1() {
        try {
            List<Anniversary> anniversaries = AnniversaryReader.readAnniversary(new File("./data/testAnniFile1"));
            Anniversary anniversary1 = anniversaries.get(0);
            Anniversary anniversary2 = anniversaries.get(1);
//            assertEquals(2020, anniversary1.getDate().getYear());
//            assertEquals(3, anniversary1.getDate().getMonth());
//            assertEquals(14, anniversary1.getDate().getDay());
//            assertEquals(2050, anniversary2.getDate().getYear());
//            assertEquals(2, anniversary2.getDate().getMonth());
//            assertEquals(13, anniversary2.getDate().getDay());

            assertEquals("HaHaHa", anniversary1.getLabel());
            assertEquals(" ", anniversary1.getComment());
            assertTrue(anniversary1.getIsAnniversary());
            assertEquals("I love you", anniversary2.getLabel());
            assertEquals("Laisen", anniversary2.getComment());
            assertTrue(anniversary2.getIsAnniversary());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseAnniFile2() {
        try {
            List<Anniversary> anniversaries = AnniversaryReader.readAnniversary(new File("./data/testAnniFile2"));
            Anniversary anniversary1 = anniversaries.get(0);
            Anniversary anniversary2 = anniversaries.get(1);
//            assertEquals(2020, anniversary1.getDate().getYear());
//            assertEquals(5, anniversary1.getDate().getMonth());
//            assertEquals(20, anniversary1.getDate().getDay());
//            assertEquals(2013, anniversary2.getDate().getYear());
//            assertEquals(10, anniversary2.getDate().getMonth());
//            assertEquals(1, anniversary2.getDate().getDay());

            assertEquals(" ", anniversary1.getLabel());
            assertEquals(" ", anniversary1.getComment());
            assertFalse(anniversary1.getIsAnniversary());
            assertEquals(" ", anniversary2.getLabel());
            assertEquals("wow", anniversary2.getComment());
            assertTrue(anniversary2.getIsAnniversary());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            AnniversaryReader.readAnniversary(new File("./path/does/not/exist/testAnni.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}
