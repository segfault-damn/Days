package persistence;


import model.entries.Diary;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DiaryReaderTest {
    @Test
    void testParseDiaryFile1() {
        try {
            List<Diary> diaries = DiaryReader.readDiary(new File("./data/testDiaryFile"));
            Diary diary1 = diaries.get(0);
            Diary diary2 = diaries.get(1);
            Diary diary3 = diaries.get(2);

            assertEquals(2020, diary1.getDate().getYear());
            assertEquals(3,diary1.getDate().getMonth());
            assertEquals(14, diary1.getDate().getDay());
            assertEquals("I love laisen",diary1.getContent());
            assertEquals("No tag", diary1.getTag());

            assertEquals(2000, diary2.getDate().getYear());
            assertEquals(10,diary2.getDate().getMonth());
            assertEquals(29, diary2.getDate().getDay());
            assertEquals("Hahaha",diary2.getContent());
            assertEquals("testTag", diary2.getTag());

            assertEquals(2000, diary3.getDate().getYear());
            assertEquals(6,diary3.getDate().getMonth());
            assertEquals(26, diary3.getDate().getDay());
            assertEquals(" ",diary3.getContent());
            assertEquals("No tag", diary3.getTag());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
    @Test
    void testIOException() {
        try {
            DiaryReader.readDiary(new File("./path/does/not/exist/testDiary.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}
