package model.entries;

import model.Date;

public class Anniversary {
    private final Date date;
    private final String label;
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
}
