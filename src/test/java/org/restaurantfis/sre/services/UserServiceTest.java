package org.restaurantfis.sre.services;


import com.mongodb.DB;
import com.mongodb.DBCollection;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.restaurantfis.sre.exceptions.UsernameAlreadyExistsException;
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

}
