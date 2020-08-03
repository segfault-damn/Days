package model;

import exceptions.DateErrorException;
import persistence.DateReader;
import persistence.Saveable;

import java.io.PrintWriter;

// Date represents a date with year,month and day
public class Date implements Saveable {
    private static final int YEAR_MIN = 1900;
    private static final int YEAR_MAX = 2100;

    private int year;
    private int month;
    private int day;

    // construct a date
    // year should be restricted from 2000 to 2100
    // month should be restricted from 01 to 12
    // day should be restricted from 01 to 31
    public Date(int year, int month, int day) throws DateErrorException {
        if (year < YEAR_MIN || year > YEAR_MAX || month < 1 || month > 12 || day < 1 || day > 31) {
            throw new DateErrorException();
        } else if (month == 2 && (day == 30 || day == 31)) {
            throw new DateErrorException();
        } else if (month == 2 && day == 29 && year % 4 != 0) {
            throw new DateErrorException();
        } else if ((month == 4 || month == 6 || month == 9 || month == 11) && (day == 31)) {
            throw new DateErrorException();
        }
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
