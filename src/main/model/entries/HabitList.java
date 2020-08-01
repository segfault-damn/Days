package model.entries;

import persistence.AnniversaryReader;
import persistence.HabitListReader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


// This class represents a list of habit and its method will be used
// in both day and dayset class. It's used to record user's habits.
public class HabitList implements Saveable {

    private List<Habit> habitList;

    // construct a list of habits
    public HabitList() {
        habitList = new ArrayList<>();
    }

    // REQUIRE: this habit list should not have duplicated habit
    // MODIFIER:this
    // EFFECT:add a habit to habit list
    public void addHabit(Habit habit) {
        habitList.add(habit);
    }


    // MODIFIER: this
    // EFFECT: remove the habit from the list
    public void removeHabit(Habit habit) {
        habitList.remove(habit);

    }


    //REQUIRE: l should be an existing habit's label in the list
    //EFFECT: get habit with given label
    public Habit getHabit(String l) {
        for (Habit habit : habitList) {
            if (habit.getLabel().equals(l)) {
                return habit;
            }
        }
        return null;
    }

    // REQUIRE: habit label can not pre-exist in the list and habit should exist in that list
    // MODIFIER: this
    // EFFECT: edit habit
    public void editOneHabit(Habit h, String s) {

        getHabit(h.getLabel()).editHabit(s);
    }

    //getter
    public List<Habit> getHabitList() {
        return habitList;
    }


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
