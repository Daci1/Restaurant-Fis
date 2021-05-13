package org.restaurantfis.sre.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    public static User createTestUser()
    {
        return new User("testName",
                "testEmail",
                "testPassword",
                "testNumber",
                "testGender",
                new Date(28,12,2000),
                "testAddress",
                false);
    }

    @Test
    public void setName(){
        User user = createTestUser();

        user.setName("testNameChanged");

        assertEquals("testNameChanged", user.getName());

    }

    @Test
    public void setEmail(){
        User user = createTestUser();

        user.setEmail("testEmailChanged");

        assertEquals("testEmailChanged", user.getEmail());
    }

    @Test
    public void setPassword(){
        User user = createTestUser();

        user.setPassword("testPasswordChanged");

        assertEquals("testPasswordChanged", user.getPassword());
    }

    @Test
    public void setMobile(){
        User user = createTestUser();

        user.setMobile("testMobileChanged");

        assertEquals("testMobileChanged", user.getMobile());
    }

    @Test
    public void setGender(){
        User user = createTestUser();

        user.setGender("testGenderChanged");

        assertEquals("testGenderChanged", user.getGender());
    }

    @Test
    public void setDOB(){
        User user = createTestUser();
        user.setDOB(new Date(3,7,2010));

        Assertions.assertAll(   () -> assertEquals(3, user.getDOB().getDay()),
                                () -> assertEquals(7, user.getDOB().getMonth()),
                                () -> assertEquals(2010, user.getDOB().getYear()));
    }

    @Test
    public void setAddress(){
        User user = createTestUser();

        user.setAddress("testAddressChanged");

        assertEquals("testAddressChanged", user.getAddress());
    }

    @Test
    public void setAdmin(){
        User user = createTestUser();

        user.setAdmin(true);

        assertEquals(true, user.isAdmin());
    }

    @Test
    public void getName(){
        User user = createTestUser();
        assertEquals("testName", user.getName());
    }

    @Test
    public void getEmail(){
        User user = createTestUser();
        assertEquals("testEmail", user.getEmail());
    }

    @Test
    public void getPassword(){
        User user = createTestUser();
        assertEquals("testPassword", user.getPassword());
    }

    @Test
    public void getMobile(){
        User user = createTestUser();
        assertEquals("testNumber", user.getMobile());
    }

    @Test
    public void getGender(){
        User user = createTestUser();
        assertEquals("testGender", user.getGender());
    }

    @Test
    public void getDOB(){
        User user = createTestUser();
        assertEquals(true, user.getDOB().equals(new Date(28,12,2000)));
    }

    @Test
    public void getAddress(){
        User user = createTestUser();
        assertEquals("testAddress", user.getAddress());
    }

    @Test
    public void isAdmin(){
        User user = createTestUser();
        assertEquals(false, user.isAdmin());

    }

    @Test
    public void testToString(){
        User user = createTestUser();
        assertEquals("User{name='testName'," +
                " email='testEmail', password='testPassword'," +
                " mobile='testNumber', gender='testGender'," +
                " DOB=Date{day=28, month=12, year=2000}" +
                ", address='testAddress'," +
                " isAdmin=false}", user.toString());
    }

    @Test
    public void testEquals(){
        User user1 = createTestUser();

        User user2 = createTestUser();
        user2.setGender("changedGender");
        user2.setEmail("changedEmail");
        user2.setName("changedName");

        User user3 = createTestUser();

        Assertions.assertAll(   () -> assertEquals(false, user1.equals(user2)),
                                () -> assertEquals(true, user1.equals(user3)));

    }

}
