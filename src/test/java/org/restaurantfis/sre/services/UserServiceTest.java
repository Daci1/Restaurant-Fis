package org.restaurantfis.sre.services;


import com.mongodb.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.restaurantfis.sre.exceptions.EmailAlreadyExistsException;
import org.restaurantfis.sre.exceptions.UsernameAlreadyExistsException;
import org.restaurantfis.sre.model.Date;
import org.restaurantfis.sre.model.User;
import org.restaurantfis.sre.model.UserTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest {

    private static User testUser = UserTest.createTestUser();



    @Test
    public void testInitializeDB(){
        UserService.initializeDB();
        DBCollection usersCollection = UserService.getUsersCollection();
        DB database = usersCollection.getDB();

        Assertions.assertAll(   () -> assertEquals("users",usersCollection.getName()),
                                () ->  assertEquals("UsersDB", database.getName()));

    }
    @Test
    public void checkExistingUser(){
        UserService.initializeDB();
        assertThrows(UsernameAlreadyExistsException.class, () -> UserService.checkExistingUser("daci"));
    }

    @Test
    public void checkExistingEmail(){
        UserService.initializeDB();
        assertThrows(EmailAlreadyExistsException.class, () -> UserService.checkExistingEmail("daci@"));
    }

    @Test
    public void addUser(){
        UserService.addUser("testName",
                "testEmail",
                "testPass",
                "testMobile",
                "testGender",
                new Date(0,0,0),
                "testAddress",
                false);
        DBCursor cursor = UserService.getUsersCollection().find();
        DBObject lastUserAdded = cursor.one();
        while(cursor.hasNext())
        {
            lastUserAdded = cursor.next();
        }

        DBObject finalLastUserAdded = lastUserAdded;
        Date lastUserAddedDate = new Date(((int)((BasicDBObject)lastUserAdded.get("DOB")).get("day")),
                                          ((int)((BasicDBObject)lastUserAdded.get("DOB")).get("month")),
                                          ((int)((BasicDBObject)lastUserAdded.get("DOB")).get("year")));
        Assertions.assertAll(
                () -> assertEquals("testName", (String) finalLastUserAdded.get("name")),
                () -> assertEquals("testEmail", (String) finalLastUserAdded.get("email")),
                () -> assertEquals(UserService.encodePassword("testPass"), (String) finalLastUserAdded.get("password")),
                () -> assertEquals("testMobile", (String) finalLastUserAdded.get("mobile")),
                () -> assertEquals("testGender", (String) finalLastUserAdded.get("gender")),
                () -> assertEquals(true,  new Date(0,0,0).equals(lastUserAddedDate)),
                () -> assertEquals("testAddress", (String) finalLastUserAdded.get("address")),
                () -> assertEquals(false, (boolean) finalLastUserAdded.get("isAdmin"))
                );

        BasicDBObject document = new BasicDBObject();

        document.put("name", "testName");
        document.put("email", "testEmail");
        document.put("password", UserService.encodePassword("testPass"));
        document.put("mobile", "testMobile");
        document.put("gender", "testGender");

        BasicDBObject dateobj = new BasicDBObject();
        dateobj.put("day", 0);
        dateobj.put("month", 0);
        dateobj.put("year", 0);

        document.put("DOB", dateobj);
        document.put("address", "testAddress");
        document.put("isAdmin", false);

        DBCursor deleteUser = UserService.getUsersCollection().find(document);
        UserService.getUsersCollection().remove(deleteUser.one());
    }

    @Test
    public void encodePassword(){

        Assertions.assertAll(
                () -> assertEquals("dGVzdFBhc3N3b3JkMQ==", UserService.encodePassword("testPassword1")),
                () -> assertEquals("dGVzdFBhc3N3b3JkMg==", UserService.encodePassword("testPassword2")),
                () -> assertEquals("dGVzdFBhc3N3b3JkMw==", UserService.encodePassword("testPassword3")),
                () -> assertEquals("dGVzdFBhc3N3b3JkNA==", UserService.encodePassword("testPassword4"))
        );
    }

}
