package model;

import exceptions.EventExistException;
import exceptions.HabitNotExistException;
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


    // EFFECT: calculate the anniversary in given day
    public int calAnniversary(Date today, Day d) {
        int result;
        if (d.getDate().getYear() >= today.getYear()) {
            result = 0;
        } else if (d.getDate().getMonth() < today.getMonth()) {
            result = today.getYear() - d.getDate().getYear();
        } else if (d.getDate().getMonth() == today.getMonth() && d.getDate().getDay() <= today.getDay()) {
            result = today.getYear() - d.getDate().getYear();
        } else {
            result = today.getYear() - d.getDate().getYear() - 1;
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
    public Day getDay(Date d) {
        for (Day day : days) {
            if (day.getDate().getDay() == d.getDay() && day.getDate().getMonth() == d.getMonth()
                    && day.getDate().getYear() == d.getYear()) {
                return day;
            }
        }
        Day targetDay = new Day(d);
        addDay(targetDay);
        return targetDay;
    }


    //Modifies: this
    // EFFECT: edit given event to given date and time
    public void editEvent(Day d, TodoEvent e, Date newDate, int hour, int min) throws EventExistException {
        String label = e.getLabel();
        TodoEvent event = new TodoEvent(label,hour,min);

        getDay(newDate).addEvent(event);
        d.getTodoEventList().remove(d.getEvent(e.getHour(), e.getMin(), e.getLabel()));

    }

    // EFFECT: get all diary with tag "tag"
    public List<Day> searchByTag(String tag) {
        List<Day> diaryWithTag = new ArrayList<>();
        for (Day day : days) {
            if (tag.equals(day.getDiary().getTag())) {
                diaryWithTag.add(day);
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
    public void removeDailyHabitList(Habit h) throws HabitNotExistException {
        if (setHabitList.getHabitLabel().contains(h.getLabel())) {
            for (Day day : days) {

                day.getDailyHabitList().getHabitList().remove(day.getDailyHabitList().getHabit(h.getLabel()));

            }
            setHabitList.removeHabit(setHabitList.getHabit(h.getLabel()));
        } else {
            throw new HabitNotExistException();
        }
    }


    // MODIFIER: day
    // EFFECT: remove one habit and renew all habit in days
    public void editDailyHabitList(Habit h, String s) throws HabitNotExistException {
        if (setHabitList.getHabitLabel().contains(h.getLabel())) {
            for (Day day : days) {

                day.getDailyHabitList().editOneHabit(day.getDailyHabitList().getHabit(h.getLabel()), s);

            }
            setHabitList.editOneHabit(setHabitList.getHabit(h.getLabel()), s);
        } else {
            throw new HabitNotExistException();
        }
    }



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
