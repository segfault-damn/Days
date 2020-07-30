package model.entries;

import model.Date;
import persistence.DiaryReader;
import persistence.Saveable;

import java.io.PrintWriter;

// Record diary with its date content and tag
public class Diary implements Saveable {
    private Date date;
    private String content;
    private String tag;

    public Diary(Date date) {
        this.date = date;
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
        if (s == "") {
            removeTag();
        } else {
            tag = s;
        }
    }

    // MODIFIER: this
    // EFFECT: modify the diary
    public void setContent(String s) {
        if (s == "") {
            content = " ";
        } else {
            content = s;
        }
    }

    // getters
    public String getTag() {
        return tag;
    }

    public Date getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(date.getYear());
        printWriter.print(DiaryReader.DELIMITER);
        printWriter.print(date.getMonth());
        printWriter.print(DiaryReader.DELIMITER);
        printWriter.print(date.getDay());
        printWriter.print(DiaryReader.DELIMITER);
        printWriter.print(content);
        printWriter.print(DiaryReader.DELIMITER);
        printWriter.print(tag);
        printWriter.print(DiaryReader.DELIMITER);
        printWriter.println("");
    }
}
