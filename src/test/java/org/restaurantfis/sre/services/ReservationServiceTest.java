package org.restaurantfis.sre.services;


import com.mongodb.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.restaurantfis.sre.exceptions.ReservationsLimitReached;
import org.restaurantfis.sre.exceptions.UsernameAlreadyExistsException;
import org.restaurantfis.sre.model.User;
import org.restaurantfis.sre.model.UserTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservationServiceTest {


    public static void addTestReservation(){
        try{
            ReservationService.addReservation(
                    "testTable",
                    "testHour",
                    0,
                    0,
                    "testName");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void deleteTestReservation(){

        DBObject query = new BasicDBObject("tableName", "testTable");
        query.put("reservationHour", "testHour");
        query.put("reservationDay", 0);
        query.put("reservationMonth", 0);
        query.put("userName", "testName");

        DBCursor cursor = ReservationService.getTablesCollection().find(query);
        if(cursor.hasNext()) ReservationService.getTablesCollection().remove(cursor.next());
    }

    @Test
    public void initializeDB(){
        ReservationService.initializeDB();
        DBCollection tablesCollection = ReservationService.getTablesCollection();
        DB database = tablesCollection.getDB();

        Assertions.assertAll(   () -> assertEquals("tablesCollection",tablesCollection.getName()),
                () ->  assertEquals("TablesDB", database.getName()));

    }

    @Test
    public void addReservation(){

        ReservationService.initializeDB();
        addTestReservation();

        DBCursor cursor = ReservationService.getTablesCollection().find();
        DBObject lastReservationAdded = cursor.one();

        while(cursor.hasNext())
        {
            lastReservationAdded = cursor.next();
        }

        DBObject finalLastReservationAdded = lastReservationAdded;

        Assertions.assertAll(
                () -> assertEquals("testTable", (String) finalLastReservationAdded.get("tableName")),
                () -> assertEquals("testHour", (String) finalLastReservationAdded.get("reservationHour")),
                () -> assertEquals(0, (int) finalLastReservationAdded.get("reservationDay")),
                () -> assertEquals(0, (int) finalLastReservationAdded.get("reservationMonth")),
                () -> assertEquals("testName", (String) finalLastReservationAdded.get("userName"))
        );

        deleteTestReservation();

    }

    @Test
    public void isReserved(){
        addTestReservation();

        assertEquals(true, ReservationService.isReserved("testTable",
                "testHour",
                0,
                0));

        deleteTestReservation();
    }

    @Test
    public void checkReservation(){
        UserService.initializeDB();
        ReservationService.initializeDB();
        UserServiceTest.addTestUser();
        UserService.validateLogin("testEmail", "testPass");
        addTestReservation();

        Assertions.assertAll(
                () -> assertEquals(true, ReservationService.checkReservation("testTable", "testHour", 0,0)),
                () -> assertEquals(false, ReservationService.checkReservation("testTable", "testHour", 0,1))
        );

        deleteTestReservation();
        UserServiceTest.removeTestUser();

    }

    @Test
    public void deleteOutdatedReservations(){
        ReservationService.initializeDB();

        DBCursor cursor = ReservationService.getTablesCollection().find();
        DBObject lastReservationAdded = cursor.one();

        while(cursor.hasNext())
        {
            lastReservationAdded = cursor.next();
        }

        DBObject finalLastReservationAdded = lastReservationAdded;

        addTestReservation();
        try{
            ReservationService.addReservation(
                    "testTable",
                    "testHour",
                    1,
                    0,
                    "testName");
        }catch(Exception e){
            System.out.println(e);
        }

        ReservationService.deleteOutdatedReservations();

        cursor = ReservationService.getTablesCollection().find();
        DBObject lastReservationAddedAfterTest = cursor.one();

        while(cursor.hasNext())
        {
            lastReservationAddedAfterTest = cursor.next();
        }

        DBObject finalLastReservationAddedAfterTest = lastReservationAddedAfterTest;

        Assertions.assertAll(
                () -> assertEquals((String)finalLastReservationAdded.get("tableName"), (String)finalLastReservationAddedAfterTest.get("tableName")),
                () -> assertEquals((String)finalLastReservationAdded.get("reservationHour"), (String)finalLastReservationAddedAfterTest.get("reservationHour")),
                () -> assertEquals((int)finalLastReservationAdded.get("reservationDay"), (int)finalLastReservationAddedAfterTest.get("reservationDay")),
                () -> assertEquals((int)finalLastReservationAdded.get("reservationMonth"), (int)finalLastReservationAddedAfterTest.get("reservationMonth")),
                () -> assertEquals((String)finalLastReservationAdded.get("userName"), (String)finalLastReservationAddedAfterTest.get("userName"))
        );

    }

    @Test
    public void deleteReservation(){
        UserService.initializeDB();
        ReservationService.initializeDB();
        UserServiceTest.addTestUser();
        UserService.validateLogin("testEmail", "testPass");
        addTestReservation();


        assertEquals(true, ReservationService.checkReservation("testTable", "testHour",0,0));

        ReservationService.deleteReservation("testTable", "testHour", 0,0);

        assertEquals(false, ReservationService.checkReservation("testTable", "testHour",0,0));

        UserServiceTest.removeTestUser();

    }

    @Test
    public void getTablesCollection(){
        DBCollection tablesCollection = ReservationService.getTablesCollection();

        assertEquals("tablesCollection", tablesCollection.getName());
    }

    @Test
    public void ReservationsLimitReached(){
        ReservationService.initializeDB();
        addTestReservation();
        assertThrows(ReservationsLimitReached.class, () -> ReservationService.addReservation("testTable", "testHour",0,0, "testName"));

        ReservationService.deleteOutdatedReservations();

    }

}
