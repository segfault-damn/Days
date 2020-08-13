package model.entries;

import persistence.Saveable;
import persistence.TodoEventReader;

import java.io.PrintWriter;


// A class of Event that has time and name and date
public class TodoEvent implements Saveable {
    private String label;
    private int hour;
    private int min;

    // Construct event with given date, label, hour and minutes
    public TodoEvent(String label, int hour, int min) {
        this.label = label;
        this.hour = hour;
        this.min = min;
    }


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


    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(label);
        printWriter.print(TodoEventReader.DELIMITER1);
        printWriter.print(hour);
        printWriter.print(TodoEventReader.DELIMITER1);
        printWriter.print(min);
        printWriter.print(TodoEventReader.DELIMITER2);
    }
}
