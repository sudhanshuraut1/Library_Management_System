package com.example.Student_Library_Management_System.DTOs;

public class AuthorEntryDto {

    //this is just an obj used to take req from postman

    //it will contaion parameters that we want to send from postman

    private String name;
     private int age;
     private String country;
     private double rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public AuthorEntryDto() {
    }

    public AuthorEntryDto(String name, int age, String country, double rating) {
        this.name = name;
        this.age = age;
        this.country = country;
        this.rating = rating;
    }

}
