package persistence;

import model.Date;
import model.entries.HabitList;
import model.entries.TodoEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoEventReaderTest {

    @BeforeEach
    void setUp() {
        TodoEventReader todoEventReader = new TodoEventReader();
    }

    @Test
    void testParseTodoEventFile1() {
        try {
            List<List<TodoEvent>> todoEvents =  TodoEventReader.readEvent(new File("./data/testTodoEventListFile1"));
            List<TodoEvent> todoEvent1 = todoEvents.get(0);
            TodoEvent event1 = todoEvent1.get(0);
            TodoEvent event2 = todoEvent1.get(1);
            List<TodoEvent> todoEvent2 = todoEvents.get(1);
            TodoEvent event3 = todoEvent2.get(0);
            assertEquals(2, todoEvent1.size());
            assertEquals(1, todoEvent2.size());

            assertEquals(2021, event1.getDate().getYear());
            assertEquals(8, event1.getDate().getMonth());
            assertEquals(6, event1.getDate().getDay());
            assertEquals("gyukaku", event1.getLabel());
            assertEquals(18, event1.getHour());
            assertEquals(0, event1.getMin());

            assertEquals(2020, event2.getDate().getYear());
            assertEquals(8, event2.getDate().getMonth());
            assertEquals(7, event2.getDate().getDay());
            assertEquals("gyukaku", event1.getLabel());
            assertEquals(18, event1.getHour());
            assertEquals(0, event1.getMin());

            assertEquals(2020, event3.getDate().getYear());
            assertEquals(8, event3.getDate().getMonth());
            assertEquals(1, event3.getDate().getDay());
            assertEquals("sea", event3.getLabel());
            assertEquals(17, event3.getHour());
            assertEquals(0, event3.getMin());


        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseTodoEventFile2() {
        try {
            List<List<TodoEvent>> todoEvents =  TodoEventReader.readEvent(new File("./data/testTodoEventListFile2"));
            List<TodoEvent> todoEvent1 = todoEvents.get(0);
            assertTrue(todoEvent1.isEmpty());

            List<TodoEvent> TodoEvent2 = todoEvents.get(1);
            assertTrue(TodoEvent2.isEmpty());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            TodoEventReader.readEvent(new File("./path/does/not/exist/testTodoEventList.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}
