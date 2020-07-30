package model.entriesTest;

import model.Date;
import model.entries.Diary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DiaryTest {
    private Date testDate;
    private Diary testDiary;

    @BeforeEach
    public void setUp() {
        testDate = new Date(2020, 02, 13);
        testDiary = new Diary(testDate);
    }


    @Test
    // MODIFIER: this
    // EFFECT: add tag to diary
    public void testSetTag() {
        assertEquals(" ",testDiary.getTag());
        testDiary.setTag("");
        assertEquals(" ",testDiary.getTag());
        testDiary.setTag("Daisy");
        assertEquals("Daisy", testDiary.getTag());
        testDiary.setTag("Laisen");
        assertEquals("Laisen", testDiary.getTag());
    }

    @Test
    //MODIFIER: this
    //EFFECT: remove tag
    public void testRemoveTag() {
        testDiary.setTag("Daisy");
        testDiary.removeTag();
        assertEquals(" ",testDiary.getTag());
    }

    @Test
    // MODIFIER: this
    // EFFECT: modify the diary
    public void testSetContent() {
        testDiary.setContent("Programming is tired!");
        assertEquals("Programming is tired!", testDiary.getContent());
        testDiary.setContent("");
        assertEquals(" ", testDiary.getContent());
    }

    @Test
    public void testGetDate() {
        assertEquals(testDate, testDiary.getDate());
    }


}
