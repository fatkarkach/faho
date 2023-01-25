package com.example.demo.centralbank.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private Long phoneNumber;

    private CinDto cinDto;

}
