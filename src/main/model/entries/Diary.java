package model.entries;

import model.Date;
import persistence.DiaryReader;
import persistence.Saveable;

import java.io.PrintWriter;

// the class represents the diary with date, content and its tag
public class Diary implements Saveable {
    private Date date;
    private String content;
    private String tag;

    // construct diary with given date
    public Diary() {
 //       this.date = date;
        this.content = " ";
        this.tag = "No tag";
    }

    //MODIFIER: this
    //EFFECT: remove tag
    public void removeTag() {
        tag = "No tag";
    }


    // MODIFIER: this
    // EFFECT: add tag to diary
    public void setTag(String s) {
        if (s.equals("")) {
            removeTag();
        } else {
            tag = s;
        }
    }

    // MODIFIER: this
    // EFFECT: modify the diary
    public void setContent(String s) {
        if (s.equals("")) {
            content = " ";
        } else {
            content = s;
        }
    }

    // getters
    public String getTag() {
        return tag;
    }

//    public Date getDate() {
//        return date;
//    }

    public String getContent() {
        return content;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(content);
        printWriter.print(DiaryReader.DELIMITER);
        printWriter.print(tag);
        printWriter.print(DiaryReader.DELIMITER);
        printWriter.println("");
    }
}
