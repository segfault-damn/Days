package model.entries;

import model.Date;
import persistence.Saveable;
import persistence.AnniversaryReader;

import java.io.PrintWriter;
import java.util.Objects;

// A class that
public class Anniversary implements Saveable {
    private Date date;
    private String label;
    private String comment;

    public Anniversary(Date date, String label, String comment) {
        this.date = date;
        this.label = label;
        this.comment = comment;
    }


    // MODIFIER: this
    // EFFECT: change the comment
    public void editComment(String s) {
        comment = s;

    }




    //getters

    public Date getDate() {
        return date;
    }

    public String getLabel() {
        return label;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(date.getYear());
        printWriter.print(AnniversaryReader.DELIMITER);
        printWriter.print(date.getMonth());
        printWriter.print(AnniversaryReader.DELIMITER);
        printWriter.print(date.getDay());
        printWriter.print(AnniversaryReader.DELIMITER);
        printWriter.print(label);
        printWriter.print(AnniversaryReader.DELIMITER);
        printWriter.print(comment);
        printWriter.print(AnniversaryReader.DELIMITER);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Anniversary that = (Anniversary) o;
        return date.equals(that.date)
                && label.equals(that.label)
                && comment.equals(that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, label, comment);
    }
}
