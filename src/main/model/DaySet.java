package model;

import model.entries.Diary;
import model.entries.Habit;
import model.entries.HabitList;
import model.entries.Mood;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DaySet {
    private final Set<Day> days;
    private final HabitList setHabitList;

    public DaySet() {
        days = new HashSet<>();
        setHabitList = new HabitList();
    }


    // MODIFIER: this
    // EFFECT: add a day to the list and do nothing if that day is already in the list
    private void addDay(Day d) {
        for (Habit dailyh : setHabitList.getHabitList()) {
            d.getDailyHabitList().addHabit(new Habit(dailyh.getLabel()));
        }
        days.add(d);
    }


    // EFFECT: get a day from the daylist with given date
    //         if that day does not exist, creat a new date in that given list
    public Day getDay(Date d) {

        for (Day day : days) {
            if (day.getDate() == d) {
                return day;
            }
        }
        Day targetDay = new Day(d);
        addDay(targetDay);
        return targetDay;
    }

    // EFFECT: get all diary with tag "tag"
    public List<Diary> searchByTag(String tag) {
        List<Diary> diaryWithTag = new ArrayList<>();
        for (Day day : days) {
            if (tag.equals(day.getDiary().getTag())) {
                diaryWithTag.add(day.getDiary());
            }
        }
        return diaryWithTag;
    }

    // MODIFIER: day
    // EFFECT: add one habit and renew all habit in days
    public void addDailyHabitList(Habit h) {
        for (Day day : days) {
            day.getDailyHabitList().addHabit(new Habit(h.getLabel()));
        }
        setHabitList.addHabit(h);
    }


    // MODIFIER: day
    // EFFECT: add one habit and renew all habit in days
    public void removeDailyHabitList(Habit h) {
        for (Day day : days) {

            day.getDailyHabitList().getHabitList().remove(day.getDailyHabitList().getHabit(h.getLabel()));

        }
        setHabitList.removeHabit(h);
    }


    // REQUIRE: habit exist in the list
    // EFFECT: count the numbers of completed habit in given month and year
    public int countHabit(int y, int m, Habit h) {
        int result = 0;
        for (Day day : days) {
            if (day.getDate().getYear() == y && day.getDate().getMonth() == m) {
                if (day.getDailyHabitList().getHabit(h.getLabel()).getIsDone()) {
                    result++;
                }
            }
        }
        return result;
    }

    // REQUIRE: Mood m should be one of the mood
    // EFFECT: count mood m in the given month and year
    public int countMood(Mood m, int year, int month) {
        int result = 0;
        for (Day day : days) {
            if (day.getDate().getYear() == year && day.getDate().getMonth() == month) {
                if (day.getMood() == m) {
                    result++;
                }
            }
        }
        return result;
    }


    // getter


    public Set<Day> getDays() {
        return days;
    }

    public HabitList getSetHabitList() {
        return setHabitList;
    }
}
