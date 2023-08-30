package com.jwtauth.manageuser.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private Integer age;
    private Date dateOfBirth;
    private String address;
    private String country;
    private String countryCodes;

     @OneToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "user_login_id", referencedColumnName = "id")
    private UserLoginDetails userLoginDetails;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCodes() {
        return countryCodes;
    }

    public void setCountryCodes(String countryCodes) {
        this.countryCodes = countryCodes;
    }

    public UserLoginDetails getUserLoginDetails() {
        return userLoginDetails;
    }

    public void setUserLoginDetails(UserLoginDetails userLoginDetails) {
        this.userLoginDetails = userLoginDetails;
    }
}
