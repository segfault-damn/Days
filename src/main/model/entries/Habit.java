package model.entries;

import persistence.Saveable;
import persistence.SetHabitListReader;

import java.io.PrintWriter;

// A class of habit with a name and a boolean indicates whether it is done
public class Habit implements Saveable {
    private String label;
    private boolean isDone;

    // construct a habit with given label
    public Habit(String label) {

        this.label = label;
        isDone = false;
    }

    // MODIFIER: this
    // EFFECT: mark habit as done if it's not done or mark
    //         it as undone if it's done
    public void flipDone() {
        isDone = !isDone;
    }

    // MODIFIER: this
    // EFFECT: edit habit
    public void editHabit(String s) {
        label = s;
    }


    //getters
    public String getLabel() {
        return label;
    }

    public boolean getIsDone() {
        return isDone;
    }


    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(label);
        printWriter.print(SetHabitListReader.DELIMITER);
        printWriter.print(isDone);
        printWriter.print(SetHabitListReader.DELIMITER);
        printWriter.println("");
    }
}
