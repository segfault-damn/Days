package model;


import exceptions.EventExistException;
import exceptions.RemoveAnniException;
import model.entries.*;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

// Day represents each day with date, daily habitlist, todoEvents diary, anniversary
// and mood.
public class Day implements Saveable, Observer {
    private Date date;
    private HabitList dailyHabitList;
    private List<TodoEvent> todoEvents;
    private Anniversary anniversary;
    private Diary diary;
    private Mood mood;

    // Construct day with given date and initiate all fields
    public Day(Date date) {

        this.date = date;
        anniversary = new Anniversary(" ", " ");
        diary = new Diary();
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
    public void removeDayAnniversary() throws RemoveAnniException {
        if (anniversary.getIsAnniversary()) {
            anniversary.removeAnniversary();
        } else {
            throw new RemoveAnniException();
        }

    }

    // MODIFIER: this
    // EFFECT: set diary with given diary
    public void setDiary(Diary d) {
        diary = d;
    }

    // MODIFIER: this
    // EFFECT: set habit list
    public void setDailyHabitList(HabitList hl) {
        dailyHabitList = hl;
    }

    // MODIFIER: flip one habit's status
    // EFFECT: flip the chosen habit in the habit list
    public void flipOneHabit(Habit h) {
        dailyHabitList.getHabit(h.getLabel()).flipDone();
    }

    // MODIFIER: this
    // EFFECT: add a todoEvent to the list
    public void addEvent(TodoEvent e) throws EventExistException {
        for (TodoEvent todoEvent : todoEvents) {
            if (todoEvent.getMin() == e.getMin() && todoEvent.getHour() == e.getHour()
                    && todoEvent.getLabel().equals(e.getLabel())) {
                throw new EventExistException();
            }
        }
        todoEvents.add(e);
    }


    // MODIFIER: this
    // EFFECT: remove the given event from that list
    public void removeEvent(int hour, int min, String name) {

        todoEvents.removeIf(event -> hour == event.getHour() && min == event.getMin() && name.equals(event.getLabel()));

    }

    // EFFECT: find the Event with given time
    public TodoEvent getEvent(int h, int m, String n) {
        for (TodoEvent todoEvent : todoEvents) {
            if (h == todoEvent.getHour() && m == todoEvent.getMin() && todoEvent.getLabel().equals(n)) {
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

    // getters
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

    @Override
    public void save(PrintWriter printWriter) {
        for (TodoEvent event : todoEvents) {
            event.save(printWriter);
        }
        printWriter.println();
    }


    @Override
    public void update(Observable o, Object arg) {

    }
}
