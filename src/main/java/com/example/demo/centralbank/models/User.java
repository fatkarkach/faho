package com.example.demo.centralbank.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="_user") @Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String image;
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(unique = true,nullable = false)
    private String email;
    private Long phoneNumber;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String role;

    @OneToOne(mappedBy = "user")
    private Cin cin;

    @OneToOne(mappedBy = "customer")
    private BankAccount bankAccount;

}
