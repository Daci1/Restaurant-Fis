package org.restaurantfis.sre.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTest {

    @Test
    public void setDay() {
        Date date = new Date(5,12,2021);
        date.setDay(6);
        assertEquals(6, date.getDay());
    }

    @Test
    public void setMonth() {
        Date date = new Date(5,12,2021);
        date.setMonth(1);
        assertEquals(1, date.getMonth());
    }

    @Test
    public void setYear() {
        Date date = new Date(5,12,2021);
        date.setYear(2020);
        assertEquals(2020, date.getYear());
    }
    @Test
    public void getDay() {
        Date date = new Date(5,12,2021);
        assertEquals(5, date.getDay());
    }

    @Test
    public void getMonth() {
        Date date = new Date(5,12,2021);
        assertEquals(12, date.getMonth());
    }

    @Test
    public void getYear() {
        Date date = new Date(5,12,2021);
        assertEquals(2021   , date.getYear());
    }

    @Test
    public void testEquals(){
        Date date1 = new Date(5,12,2021);
        Date date2 = new Date(6,7,2020);
        Date date3 = new Date(6,7,2020);


        Assertions.assertAll(   () -> assertEquals(date1.equals(date2), false),
                                () -> assertEquals(date2.equals(date3), true));

    }


    @Test
    public void testToString(){
        Date date1 = new Date(5,12,2021);
        assertEquals(date1.toString(), "Date{day=5, month=12, year=2021}");
    }

    }





