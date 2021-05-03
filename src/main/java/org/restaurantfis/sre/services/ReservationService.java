package org.restaurantfis.sre.services;

import com.mongodb.*;
import org.restaurantfis.sre.model.Date;

public class ReservationService {

    private static MongoClient mongoClient;
    private static DB database;
    private static DBCollection tableCollections;

    public static void initializeDB() {
        try{
            mongoClient = new MongoClient();
            database = mongoClient.getDB("TablesDB");
            database.createCollection("tablesCollection", null);
            tableCollections = database.getCollection("tablesCollection");

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void addReservation(String tableName, String reservationHour, int reservationDay, int reservationMonth, String userName) {
        BasicDBObject document = new BasicDBObject();

        document.put("tableName", tableName);
        document.put("reservationHour", reservationHour);
        document.put("reservationDay", reservationDay);
        document.put("reservationMonth", reservationMonth);
        document.put("userName", userName);

        tableCollections.insert(document);
    }

    public static boolean isReserved(String tableName, String reservationHour, int reservationDay, int reservationMonth){
        DBObject query = new BasicDBObject("tableName", tableName);
        query.put("reservationHour", reservationHour);
        query.put("reservationDay", reservationDay);
        query.put("reservationMonth", reservationMonth);

        DBCursor cursor = tableCollections.find(query);
        if(cursor.one() != null) return true;

        return false;
    }

    public static void printCollection(){
        DBCursor cursor = tableCollections.find();
        while(cursor.hasNext())
        {
            System.out.println(cursor.next());
            System.out.println();
        }
    }
    public static void dropDB() {
        tableCollections.drop();
    }

}
