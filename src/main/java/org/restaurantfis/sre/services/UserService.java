package org.restaurantfis.sre.services;

import com.mongodb.*;
import org.restaurantfis.sre.exceptions.UsernameAlreadyExistsException;
import org.restaurantfis.sre.model.Date;
import org.restaurantfis.sre.model.User;

import java.util.Base64;

public class UserService {
    private static MongoClient mongoClient;
    private static DB database;
    private static DBCollection usersCollection;

    private static boolean isLogged = false;
    public static User loggedUser;

    public static void initializeDB()
    {
        try{
            mongoClient = new MongoClient();
            database = mongoClient.getDB("UsersDB");
            database.createCollection("users", null);

            usersCollection = database.getCollection("users");

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void checkExistingUser(String username) throws UsernameAlreadyExistsException{
        DBObject query = new BasicDBObject("name", username);
        DBCursor cursor = usersCollection.find(query);
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

        usersCollection.insert(document);
    }

    public static void printCollection(){
        DBCursor cursor = usersCollection.find();
        while(cursor.hasNext())
        {
            System.out.println(cursor.next());
            System.out.println();
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


    public static boolean validateLogin(String email, String password){

        DBObject query = new BasicDBObject("email", email);
        query.put("password", UserService.encodePassword(password));
        DBCursor cursor = usersCollection.find(query);

        if(cursor.one() != null) {
            UserService.loggedUser = new User((String)cursor.one().get("name"),
                    (String)cursor.one().get("email"),
                    (String)cursor.one().get("password"),
                    (String)cursor.one().get("mobile"),
                    (String)cursor.one().get("gender"),
                    new Date(((int)((BasicDBObject)cursor.one().get("DOB")).get("day")),
                            ((int)((BasicDBObject)cursor.one().get("DOB")).get("month")),
                            ((int)((BasicDBObject)cursor.one().get("DOB")).get("year"))),
                    (String)cursor.one().get("address"),
                    false);
            UserService.setIsLogged(true);

            return true;
        }
        else return false;
    }

    public static void dropDB()
    {
        usersCollection.drop();
    }

    public static boolean isLogged(){
        return UserService.isLogged;
    }

    public static void setIsLogged(Boolean isLogged){
        UserService.isLogged = isLogged;
    }
}
