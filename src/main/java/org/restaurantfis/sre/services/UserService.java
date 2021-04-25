package org.restaurantfis.sre.services;

import com.mongodb.*;
import org.restaurantfis.sre.exceptions.UsernameAlreadyExistsException;
import org.restaurantfis.sre.model.Date;

import java.util.Base64;

public class UserService {
    private static MongoClient mongoClient;
    private static DB database;
    private static DBCollection users_collection;

    public static void initializeDB()
    {
        try{
            mongoClient = new MongoClient();
            database = mongoClient.getDB("UsersDB");
            database.createCollection("users", null);

            users_collection = database.getCollection("users");

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void checkExistingUser(String username) throws UsernameAlreadyExistsException{
        DBObject query = new BasicDBObject("name", username);
        DBCursor cursor = users_collection.find(query);
        if(cursor.one() != null)
        {
            throw new UsernameAlreadyExistsException(username);
        }
    }


    public static void addUser(String name, String email, String password, String mobile, String gender, Date date, String address) {
        BasicDBObject document = new BasicDBObject();

        document.put("name", name);
        document.put("email", email);
        document.put("password", UserService.encodePassword(password));
        document.put("mobile", mobile);
        document.put("gender", gender);

        BasicDBObject dateobj = new BasicDBObject();
        dateobj.put("day", date.getDay());
        dateobj.put("month", date.getMonth());
        dateobj.put("year", date.getYear());

        document.put("DOB", dateobj);
        document.put("address", address);

        users_collection.insert(document);
    }

    public static void printCollection(){
        DBCursor cursor = users_collection.find();
        while(cursor.hasNext())
        {
            System.out.println(cursor.next());
        }
    }

    public static String encodePassword(String password){

        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        return encodedPassword;
    }

    public static String decodePassword(String encodedPassword){
        byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
        String decodedPassword = new String(decodedBytes);
        return decodedPassword;
    }

    public static void dropDB()
    {
        users_collection.drop();
    }
}
