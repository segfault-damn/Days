package model.entries;

import java.util.ArrayList;
import java.util.List;

public class HabitList {

    private final List<Habit> habitlist;

    public HabitList() {
        habitlist = new ArrayList<>();
    }

    // REQUIRE: this habit list should not have duplicated habit
    // MODIFIER:this
    // EFFECT:add a habit to habit list
    public void addHabit(Habit habit) {
        habitlist.add(habit);
    }


    // MODIFIER: this
    // EFFECT: remove the habit from the list
    public void removeHabit(Habit habit) {
        habitlist.remove(habit);

    }


    //REQUIRE: l should be an existing habit's label in the list
    //EFFECT: get habit with given label
    public Habit getHabit(String l) {
        for (Habit habit : habitlist) {
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
        return habitlist;
    }


    public List<String> getHabitLabel() {
        List<String> result = new ArrayList<>();
        for (Habit h : habitlist) {
            result.add(h.getLabel());
        }
        return result;
    }

}
