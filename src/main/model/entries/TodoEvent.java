package model.entries;

import model.Date;


// A Event that has time and name and date
public class TodoEvent {
    private Date date;
    private String label;
    private int hour;
    private int min;

    public TodoEvent(Date date, String label, int hour, int min) {
        this.date = date;
        this.label = label;
        this.hour = hour;
        this.min = min;
    }


    // REQUIRE: the time should not be occupied
    // MODIFIER: this
    // EFFECT: change the time of an event
    public void setTime(int hour, int min) {
        this.hour = hour;
        this.min = min;
    }
    //getters

    public String getLabel() {
        return label;
    }

    public Date getDate() {
        return date;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }
}
