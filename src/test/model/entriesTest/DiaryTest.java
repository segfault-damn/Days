package model.entriesTest;

import model.entries.Diary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryTest {
    private Diary testDiary;

    @BeforeEach
    public void setUp() {

        testDiary = new Diary();
    }


    @Test
    // MODIFIER: this
    // EFFECT: add tag to diary
    public void testSetTag() {
        assertEquals("No tag",testDiary.getTag());
        testDiary.setTag("");
        assertEquals("No tag",testDiary.getTag());
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
        assertEquals("No tag",testDiary.getTag());
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
}
