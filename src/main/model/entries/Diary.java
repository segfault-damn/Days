package model.entries;

import model.Date;

public class Diary {
    private final Date date;
    private String content;
    private String tag;

    public Diary(Date date) {
        this.date = date;
        this.content = "";
    }

    //MODIFIER: this
    //EFFECT: remove tag
    public void removeTag() {
        tag = null;
    }

    public Date getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    // MODIFIER: this
    // EFFECT: add tag to diary
    public void setTag(String s) {
        tag = s;
    }

    // MODIFIER: this
    // EFFECT: modify the diary
    public void setContent(String s) {
        content = s;
    }

    // getters
    public String getTag() {
        return tag;
    }


}
