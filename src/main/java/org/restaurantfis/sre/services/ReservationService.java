package org.restaurantfis.sre.services;

import com.mongodb.*;
import org.restaurantfis.sre.exceptions.ReservationsLimitReached;

import java.time.LocalDate;

public class ReservationService {

    private static MongoClient mongoClient;
    private static DB database;
    private static DBCollection tablesCollection;

    public static void initializeDB() {
        try{
            mongoClient = new MongoClient();
            database = mongoClient.getDB("TablesDB");
            database.createCollection("tablesCollection", null);
            tablesCollection = database.getCollection("tablesCollection");
            deleteOutdatedReservations();

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void addReservation(String tableName, String reservationHour, int reservationDay, int reservationMonth, String userName) throws ReservationsLimitReached {

        DBObject query = new BasicDBObject("tableName", tableName);
        query.put("reservationDay", reservationDay);
        query.put("userName", userName);

        DBCursor cursor = tablesCollection.find(query);

        if(cursor.one() != null){
            throw new ReservationsLimitReached();
        }else{
            BasicDBObject document = new BasicDBObject();

            document.put("tableName", tableName);
            document.put("reservationHour", reservationHour);
            document.put("reservationDay", reservationDay);
            document.put("reservationMonth", reservationMonth);
            document.put("userName", userName);

            tablesCollection.insert(document);
        }



    }

    public static boolean isReserved(String tableName, String reservationHour, int reservationDay, int reservationMonth){
        DBObject query = new BasicDBObject("tableName", tableName);
        query.put("reservationHour", reservationHour);
        query.put("reservationDay", reservationDay);
        query.put("reservationMonth", reservationMonth);

        DBCursor cursor = tablesCollection.find(query);
        if(cursor.one() != null) return true;

        return false;
    }

    public static boolean checkReservation(String tableName, String reservationHour, int reservationDay, int reservationMonth){
        DBObject query = new BasicDBObject("tableName", tableName);
        query.put("reservationHour", reservationHour);
        query.put("reservationDay", reservationDay);
        query.put("reservationMonth", reservationMonth);
        query.put("userName", UserService.loggedUser.getName());

        DBCursor cursor = tablesCollection.find(query);
        if(cursor.one() != null) return true;

        return false;
    }

    public static void printCollection(){
        DBCursor cursor = tablesCollection.find();
        while(cursor.hasNext())
        {
            System.out.println(cursor.next());
            System.out.println();
        }
    }

    public static void deleteOutdatedReservations() {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentDay = currentDate.getDayOfMonth();

        DBCursor cursor = tablesCollection.find();
        while(cursor.hasNext())
        {
            DBObject currentCursor = cursor.next();
            int cursorDay = (int) currentCursor.get("reservationDay");
            int cursorMonth = (int) currentCursor.get("reservationMonth");
            if(cursorMonth <= currentMonth && currentDay > cursorDay){
                ReservationService.tablesCollection.remove(currentCursor);
            }
        }

    }

    public static void deleteReservation(String tableName, String reservationHour, int reservationDay, int reservationMonth){
        DBObject query = new BasicDBObject("tableName", tableName);
        query.put("reservationHour", reservationHour);
        query.put("reservationDay", reservationDay);
        query.put("reservationMonth", reservationMonth);
        query.put("userName", UserService.loggedUser.getName());

        DBCursor cursor = tablesCollection.find(query);
        if(cursor.hasNext()) ReservationService.tablesCollection.remove(cursor.next());

    }

    public static DBCollection getTablesCollection()
    {
        return tablesCollection;
    }

    public static void dropDB() {
        tablesCollection.drop();
    }


}
