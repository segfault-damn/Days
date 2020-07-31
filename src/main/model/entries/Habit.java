package model.entries;


import persistence.HabitListReader;
import persistence.Saveable;
import persistence.SetHabitListReader;

import java.io.PrintWriter;
import java.util.Objects;

// A habit with a name and a boolean indicates whether it is done
public class Habit implements Saveable {
    private String label;
    private boolean isDone;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Habit habit = (Habit) o;
        return isDone == habit.isDone
                && label.equals(habit.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, isDone);
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
