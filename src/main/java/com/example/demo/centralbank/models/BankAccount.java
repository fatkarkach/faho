package com.example.demo.centralbank.models;

import com.example.demo.centralbank.enums.AccountStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity @Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class BankAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long accountNumber;
    private double balance;
    private LocalDate creationDate;
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "int default '0'")
    private AccountStatus status;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User customer;

}
