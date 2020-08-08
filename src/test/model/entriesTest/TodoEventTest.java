package model.entriesTest;

import model.Date;
import model.entries.TodoEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoEventTest {
    private Date date;
    private TodoEvent testEvent;

    @BeforeEach
    public void setUp() {

        date = new Date(2020, 10, 20);
        testEvent = new TodoEvent(date, "Play LOL", 20, 0);
    }

    @Test
    // REQUIRE: the time should not be occupied
    // MODIFIER: this
    // EFFECT: change the time of an event
    public void testSetTime() {
        testEvent.setTime(date,10, 0);
        assertEquals(10, testEvent.getHour());
        assertEquals(0, testEvent.getMin());
        assertEquals(date, testEvent.getDate());
    }

    @Test
    public void testGetLabel() {
        assertEquals("Play LOL", testEvent.getLabel());
    }

    @Test
    public void getDate() {
        assertEquals(date, testEvent.getDate());
    }

}
