package model.entries;

import exceptions.HabitContainException;
import exceptions.HabitNotExistException;
import persistence.HabitListReader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


// This class represents a list of habit and its method will be used
// in both day and dayset class. It's used to record user's habits.
public class HabitList  extends Observable implements Saveable {

    private List<Habit> habitList;

    // construct a list of habits
    public HabitList() {
        habitList = new ArrayList<>();
    }


    // MODIFIER:this
    // EFFECT:add a habit to habit list
    public void addHabit(Habit habit) throws HabitContainException {
        if (this.getHabitLabel().contains(habit.getLabel())) {
            throw new HabitContainException();
        } else {
            habitList.add(habit);
        }
    }


    // MODIFIER: this
    // EFFECT: remove the habit from the list
    public void removeHabit(Habit habit) {
        habitList.remove(habit);

    }

    //EFFECT: get habit with given label
    public Habit getHabit(String l) {
        Habit resultHabit = new Habit("");
        for (Habit habit : habitList) {
            if (habit.getLabel().equals(l)) {
                resultHabit = habit;
            }
        }
        return resultHabit;
    }

    // MODIFIER: this
    // EFFECT: edit habit
    public void editOneHabit(Habit h, String s) {
        if (!getHabitLabel().contains(s)) {
            getHabit(h.getLabel()).editHabit(s);
        } else {
            throw new HabitContainException();
        }
    }

    //getter
    public List<Habit> getHabitList() {
        return habitList;
    }

    // get habit's labels
    public List<String> getHabitLabel() {
        List<String> result = new ArrayList<>();
        for (Habit h : habitList) {
            result.add(h.getLabel());
        }
        return result;
    }

    @Override
    public void save(PrintWriter printWriter) {
        for (Habit h : habitList) {
            printWriter.print(h.getLabel());
            printWriter.print(HabitListReader.DELIMITER1);
            printWriter.print(h.getIsDone());
            printWriter.print(HabitListReader.DELIMITER2);
        }
        printWriter.println("");
    }
}
