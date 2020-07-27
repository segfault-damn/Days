package model;

public class Date {
    private final int year;
    private final int month;
    private final int day;

    // year should be restricted from 2000 to 2100
    // month should be restricted from 01 to 12
    // day should be restricted from 01 to 31
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    //getters
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
