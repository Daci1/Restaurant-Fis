package org.restaurantfis.sre.model;

import java.util.Objects;

public class User
{
    private String name;
    private String email;
    private String password;
    private String mobile;
    private String gender;
    private Date DOB;
    private String address;
    private boolean isAdmin;

    public User(String name, String email, String password, String mobile, String gender, Date DOB, String address, boolean isAdmin) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.gender = gender;
        this.DOB = DOB;
        this.address = address;
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        User user = (User) o;
        return this.name.equals(user.name) &&
                this.email.equals(user.email) &&
                this.password.equals(user.password) &&
                this.mobile.equals(user.mobile) &&
                this.gender.equals(user.gender) &&
                this.DOB.equals(user.DOB) &&
                this.address.equals(user.address) &&
                this.isAdmin == user.isAdmin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMobile() {
        return mobile;
    }

    public String getGender() {
        return gender;
    }

    public Date getDOB() {
        return DOB;
    }

    public String getAddress() {
        return address;
    }

    public boolean isAdmin() {
        return isAdmin;
    }


}
