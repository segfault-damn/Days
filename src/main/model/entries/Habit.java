package model.entries;


// A habit with a name and a boolean indicates whether it is done
public class Habit {
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


    //getters
    public String getLabel() {
        return label;
    }

    public boolean getIsDone() {
        return isDone;
    }
}
