package ru.stqa.training.selenium.litecart.model;

public class User {


    private String firstname;
    private String lastname;
    private String address;
    private String postcode;
    private String city;
    private String country;
    private String state;
    private String email;
    private String phone;
    private String password;


    public String getFirstname() {
        return firstname;
    }

    public User withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public User withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPostcode() {
        return postcode;
    }

    public User withPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public User withCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public User withCountry(String country) {
        this.country = country;
        return this;
    }

    public String getState() {
        return state;
    }

    public User withState(String state) {
        this.state = state;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User withPassword(String password) {
        this.password = password;
        return this;
    }
}
