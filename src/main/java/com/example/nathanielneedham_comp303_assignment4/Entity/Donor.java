package com.example.nathanielneedham_comp303_assignment4.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;

import java.util.Date;

@Document(collection = "Donor")
public class Donor {

    @Id
    private String id;

    @NotNull(message = "First name is required")
    private String firstName;

    @NotNull(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Dob is required")
    private Date dob;

    @NotNull(message = "Gender is required")
    private String gender;

    @NotNull(message = "Blood group is required")
    private String bloodGroup;

    @NotNull(message = "City is required")
    private String city;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phone;

    private String donationHistory;

    private boolean loggedIn = false; // Tracks login status

    private Date lastLoginDate;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotNull String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull String firstName) {
        this.firstName = firstName;
    }

    public @NotNull String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public @NotNull String getGender() {
        return gender;
    }

    public void setGender(@NotNull String gender) {
        this.gender = gender;
    }

    public @NotNull String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(@NotNull String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public @NotNull String getCity() {
        return city;
    }

    public void setCity(@NotNull String city) {
        this.city = city;
    }

    public @Pattern(regexp = "\\d{10}", message = "Invalid phone number") String getPhone() {
        return phone;
    }

    public void setPhone(@Pattern(regexp = "\\d{10}", message = "Invalid phone number") String phone) {
        this.phone = phone;
    }

    public String getDonationHistory() {
        return donationHistory;
    }

    public void setDonationHistory(String donationHistory) {
        this.donationHistory = donationHistory;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}
