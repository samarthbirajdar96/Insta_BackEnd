package com.geekster.InstaBackend.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userFirstName;
    private String userLastName;

    @Min(value = 18)
    private String userAge;
    private String userPhoneNumber;
    @Email
    private String userEmail;
    private String userPassword;




}
