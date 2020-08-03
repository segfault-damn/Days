package model;


import exceptions.DateErrorException;
import org.junit.jupiter.api.Test;

import java.util.zip.DataFormatException;

import static org.junit.jupiter.api.Assertions.fail;

public class DateTest {
    private Date date;

    @Test
    public void testYear(){
        try {
            date = new Date(1899, 1, 2);
            fail();
        } catch (DateErrorException e) {
            // the Date should catch the error exception
        }

        try {
            date = new Date(2101, 12, 2);
            fail();
        } catch (DateErrorException e) {
            // the Date should catch the error exception
        }

        try {
            date = new Date(1900,1,4);
        } catch (DateErrorException e) {
            fail();
        }

        try {
            date = new Date(2100,6,30);
        } catch (DateErrorException e) {
            fail();
        }

        try {
            date = new Date(2020,8,3);
        } catch (DateErrorException e) {
            fail();
        }
    }

    @Test
    public void testMonth(){
        try {
            date = new Date(2020, 0, 2);
            fail();
        } catch (DateErrorException e) {
            // the Date should catch the error exception
        }

        try {
            date = new Date(2020, 13, 2);
            fail();
        } catch (DateErrorException e) {
            // the Date should catch the error exception
        }

        try {
            date = new Date(1953,12,4);
        } catch (DateErrorException e) {
            fail();
        }

        try {
            date = new Date(2050,1,30);
        } catch (DateErrorException e) {
            fail();
        }
    }

    @Test
    public void testDate(){
        try {
            date = new Date(2020, 3, 32);
            fail();
        } catch (DateErrorException e) {
            // the Date should catch the error exception
        }

        try {
            date = new Date(2020, 10, 0);
            fail();
        } catch (DateErrorException e) {
            // the Date should catch the error exception
        }

        try {
            date = new Date(2020, 10, -1);
            fail();
        } catch (DateErrorException e) {
            // the Date should catch the error exception
        }

        try {
            date = new Date(1953,12,1);
        } catch (DateErrorException e) {
            fail();
        }

        try {
            date = new Date(2050,1,31);
        } catch (DateErrorException e) {
            fail();
        }
    }

    @Test
    public void testSpecialDate(){
        try {
            date = new Date(2020, 2, 30);
            fail();
        } catch (DateErrorException e) {
            // the Date should catch the error exception
        }

        try {
            date = new Date(2020, 2, 31);
            fail();
        } catch (DateErrorException e) {
            // the Date should catch the error exception
        }

        try {
            date = new Date(2021, 2, 29);
            fail();
        } catch (DateErrorException e) {
            // the Date should catch the error exception
        }

        try {
            date = new Date(2020, 2, 28);

        } catch (DateErrorException e) {
            fail();
        }

        try {
            date = new Date(2020, 4,31);
            fail();
        } catch (DateErrorException e) {
            // the Date should catch the error exception
        }

        try {
            date = new Date(2020, 6, 31);
            fail();
        } catch (DateErrorException e) {
            // the Date should catch the error exception
        }

        try {
            date = new Date(2020, 9, 31);
            fail();
        } catch (DateErrorException e) {
            // the Date should catch the error exception
        }

        try {
            date = new Date(2020, 11, 31);
            fail();
        } catch (DateErrorException e) {
            // the Date should catch the error exception
        }

        try {
            date = new Date(1953,8,31);
        } catch (DateErrorException e) {
            fail();
        }

        try {
            date = new Date(2050,5,31);
        } catch (DateErrorException e) {
            fail();
        }

        try {
            date = new Date(2024,2,29);
        } catch (DateErrorException e) {
            fail();
        }
    }
}
