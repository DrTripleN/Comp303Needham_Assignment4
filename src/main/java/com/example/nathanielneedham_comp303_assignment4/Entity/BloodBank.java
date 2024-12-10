package com.example.nathanielneedham_comp303_assignment4.Entity;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BloodBank")
public class BloodBank {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotNull String getBloodbankName() {
        return BloodBankName;
    }

    public void setBloodbankName(@NotNull String bloodBankName) {
        this.BloodBankName = bloodBankName;
    }

    public @NotNull String getAddress() {
        return address;
    }

    public void setAddress(@NotNull String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public @Pattern(regexp = "\\d{10}", message = "Invalid phone number") String getPhone() {
        return phone;
    }

    public void setPhone(@Pattern(regexp = "\\d{10}", message = "Invalid phone number") String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Id
    private String id;

    @NotNull
    private String BloodBankName; // Blood bank name

    @NotNull
    private String address;

    private String city;

    @Pattern(regexp = "\\d{10}", message = "Invalid phone number")
    private String phone;

    private String website;

    private String email;
}
