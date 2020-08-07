package model.entriesTest;

import exceptions.DateErrorException;
import model.Date;
import model.entries.Anniversary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnniversaryTest {
    private Date testDate;
    private Anniversary testAnni;

    @BeforeEach
    public void setUp() {
        testDate = new Date(2020, 02, 13);
        testAnni = new Anniversary(testDate, "confession", "Love Laisen");
    }

//    @Test
//    public void testEditComment() {
//        testAnni.editComment("Love Daisy");
//        assertEquals("Love Daisy", testAnni.getComment());
//        testAnni.editComment("");
//        assertEquals(" ", testAnni.getComment());
//    }

    @Test
    public void testSetAnniversary() {
        testAnni.setAnniversary();
        assertTrue(testAnni.getIsAnniversary());
    }

    @Test
    public void testRemoveAnniversary() {
        testAnni.removeAnniversary();
        assertFalse(testAnni.getIsAnniversary());
    }

    @Test
    public void testGetDate() {
        assertEquals(testDate, testAnni.getDate());
    }

    @Test
    public void testGetLabel() {
        assertEquals("confession", testAnni.getLabel());
    }


}
