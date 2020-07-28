package model;


import model.entries.*;

import java.util.ArrayList;
import java.util.List;

// One day contains all information in it
public class Day {


    private Date date;
    private HabitList dailyHabitList;
    private List<TodoEvent> todoEvents;
    private Anniversary anniversary;
    private Diary diary;
    private Mood mood;


    public Day(Date date) {

        this.date = date;
        anniversary = null;
        diary = new Diary(date);
        todoEvents = new ArrayList<>();

        mood = null;

        dailyHabitList = new HabitList();
    }

    // MODIFIER: this
    // EFFECT: remove mood
    public void removeMood() {
        mood = null;
    }

    // MODIFIER: this
    // EFFECT: set anniversary with given label and comment
    public void setAnniversary(String l, String c) {
        anniversary = new Anniversary(date,l, c);
    }

    // MODIFIER: this
    // EFFECT: remove anniversary
    public void removeAnniversary() {
        anniversary = null;
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

    // MODIFIER: this
    // EFFECT: set mood to given mood
    public void setMood(Mood m) {
        mood = m;
    }

    public HabitList getDailyHabitList() {
        return dailyHabitList;
    }

    public List<TodoEvent> getTodoEvents() {
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


}
