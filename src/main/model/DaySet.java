package model;

import model.entries.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// A set of days
public class DaySet {
    private Set<Day> days;
    private HabitList setHabitList;

    public DaySet() {
        days = new HashSet<>();
        setHabitList = new HabitList();
    }


    // REQUIRE: the anniversary must be bigger than today
    // EFFECT: calculate the anniversary
    public int calAnniversary(Date today, Anniversary a) {
        int result;
        if (a.getDate().getMonth() < today.getMonth()) {
            result = today.getYear() - a.getDate().getYear();
        } else if (a.getDate().getMonth() == today.getMonth() && a.getDate().getDay() <= today.getDay()) {
            result = today.getYear() - a.getDate().getYear();
        } else {
            result = today.getYear() - a.getDate().getYear() - 1;
        }
        return result;
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
//        if (d.getYear() < 1900 || d.getMonth() > 12 || d.getMonth() < 1
//                || d.getDay() > 31 ||d.getDay() < 1) {
//            throw new DateEnterException();
//        } else {
        for (Day day : days) {
            if (day.getDate().getDay() == d.getDay() && day.getDate().getMonth() == d.getMonth()
                    && day.getDate().getYear() == d.getYear()) {
                return day;
            }
        }
        Day targetDay = new Day(d);
        addDay(targetDay);
        return targetDay;
//        }
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
    // EFFECT: remove one habit and renew all habit in days
    public void removeDailyHabitList(Habit h) {
        for (Day day : days) {

            day.getDailyHabitList().getHabitList().remove(day.getDailyHabitList().getHabit(h.getLabel()));

        }
        setHabitList.removeHabit(setHabitList.getHabit(h.getLabel()));
    }


    // MODIFIER: day
    // EFFECT: remove one habit and renew all habit in days
    public void editDailyHabitList(Habit h, String s) {
        for (Day day : days) {

            day.getDailyHabitList().editOneHabit(day.getDailyHabitList().getHabit(h.getLabel()), s);

        }
        setHabitList.editOneHabit(setHabitList.getHabit(h.getLabel()), s);
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

    // MODIFIER: this
    // EFFECT: set SetHabitList with given list of habit
    public void setSetHabitList(HabitList hl) {
        for (Habit habit : hl.getHabitList()) {
            setHabitList.addHabit(habit);
        }
    }


    // getters
    public Set<Day> getDays() {
        return days;
    }

    public HabitList getSetHabitList() {
        return setHabitList;
    }
}
