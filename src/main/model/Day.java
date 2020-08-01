package model;


import model.entries.*;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

// Day represents each day with date, daily habitlist, todoEvents diary, anniversary
// and mood.
public class Day implements Saveable {
    private Date date;
    private HabitList dailyHabitList;
    private List<TodoEvent> todoEvents;
    private Anniversary anniversary;
    private Diary diary;
    private Mood mood;

    // Construct day with given date and initiate all fields
    public Day(Date date) {

        this.date = date;
        anniversary = new Anniversary(this.date, " ", " ");
        diary = new Diary(date);
        todoEvents = new ArrayList<>();

        mood = Mood.Default;

        dailyHabitList = new HabitList();
    }


    // MODIFIER: this
    // EFFECT: set anniversary
    public void setDayAnniversary(Anniversary anniversary) {
        this.anniversary = anniversary;
    }

    // MODIFIER: this
    // EFFECT: remove anniversary
    public void removeDayAnniversary() {
        anniversary.removeAnniversary();
    }

    // MODIFIER: this
    // EFFECT: set habitlist
    public void setDailyHabitList(HabitList hl) {
        dailyHabitList = hl;
    }

    // MODIFIER: flip one habit's status
    // EFFECT: flip the chosen habit in the habit list
    public void flipOneHabit(Habit h) {
        dailyHabitList.getHabit(h.getLabel()).flipDone();
    }


    // REQUIRE: the event added can not have the same time as other events
    // MODIFIER: this
    // EFFECT: add a todoEvent to the list
    public void addEvent(TodoEvent e) {
        todoEvents.add(e);
    }

    // REQUIRE: the time input should be one of the event
    // MODIFIER: this
    // EFFECT: remove the given event from that list
    public void removeEvent(int hour, int min) {
        todoEvents.removeIf(event -> hour == event.getHour() && min == event.getMin());
    }

    // REQUIRE: the given hour and minute should correspond to one event in the list
    // EFFECT: find the Event with given time
    public TodoEvent getEvent(int h, int m) {
        for (TodoEvent todoEvent : todoEvents) {
            if (h == todoEvent.getHour() && m == todoEvent.getMin()) {
                return todoEvent;
            }
        }
        return null;
    }

    // MODIFIER: this
    // EFFECT: set todoEvent list
    public void setTodoEvents(List<TodoEvent> todoEvents) {
        this.todoEvents = todoEvents;
    }


    // MODIFIER: this
    // EFFECT: set mood to given mood
    public void setMood(Mood m) {
        mood = m;
    }


    // MODIFIER: this
    // EFFECT: remove mood
    public void removeMood() {
        mood = Mood.Default;
    }

    // getter
    public Date getDate() {
        return date;
    }

    public Anniversary getAnniversary() {
        return anniversary;
    }

    public Mood getMood() {
        return mood;
    }

    public HabitList getDailyHabitList() {
        return dailyHabitList;
    }

    public List<TodoEvent> getTodoEventList() {
        return todoEvents;
    }

    public Diary getDiary() {
        return diary;
    }

    // MODIFIER: this
    // EFFECT: set diary with given diary
    public void setDiary(Diary d) {
        diary = d;
    }

    @Override
    public void save(PrintWriter printWriter) {
        for (TodoEvent event : todoEvents) {
            event.save(printWriter);
        }
        printWriter.println();
    }


}
