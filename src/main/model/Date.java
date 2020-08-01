package model;

import exceptions.DateEnterException;
import persistence.DateReader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.Objects;

// Date represents a date with year,month and day
public class Date implements Saveable {
    private int year;
    private int month;
    private int day;

    // construct a date
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

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(year);
        printWriter.print(DateReader.DELIMITER);
        printWriter.print(month);
        printWriter.print(DateReader.DELIMITER);
        printWriter.print(day);
        printWriter.println("");
    }
}
