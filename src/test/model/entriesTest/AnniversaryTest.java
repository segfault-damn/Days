package model.entriesTest;

import model.entries.Anniversary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnniversaryTest {
    private Anniversary testAnni;

    @BeforeEach
    public void setUp() {
        testAnni = new Anniversary("confession", "Love Laisen");
    }

    @Test
    public void testComment() {
        testAnni = new Anniversary("","");
        assertEquals(" ", testAnni.getComment());
        assertEquals("No label", testAnni.getLabel());
    }

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
    public void testGetLabel() {
        assertEquals("confession", testAnni.getLabel());
    }


}
