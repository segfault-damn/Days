package model.entries;

import model.Date;
import persistence.AnniversaryReader;
import persistence.Saveable;

import java.io.PrintWriter;

// the class represents a anniversary with its date,label,comment and
// whether it is an anniversary
public class Anniversary implements Saveable {
//    private Date date;
    private String label;
    private String comment;
    private boolean isAnniversary;

    // construct anniversary with given date, name, comment
    public Anniversary(String label, String comment) {
        this.label = label;
        this.comment = comment;
        this.isAnniversary = false;
        if (label.equals("")) {
            this.label = "No label";
        }

        if (comment.equals("")) {
            this.comment = " ";
        }
    }


    // MODIFIER: this
    // EFFECT: set anniversary
    public void setAnniversary() {
        isAnniversary = true;
    }

    // MODIFIER: this
    // EFFECT: remove anniversary
    public void removeAnniversary() {
        isAnniversary = false;
    }


    public String getLabel() {
        return label;
    }

    public String getComment() {
        return comment;
    }

    public boolean getIsAnniversary() {
        return isAnniversary;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(label);
        printWriter.print(AnniversaryReader.DELIMITER);
        printWriter.print(comment);
        printWriter.print(AnniversaryReader.DELIMITER);
        printWriter.print(isAnniversary);
        printWriter.print(AnniversaryReader.DELIMITER);
        printWriter.println("");
    }
}
