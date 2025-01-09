package me.m41k0n.model;

import jakarta.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Embeddable
public class PersonalInfo {
    private String name;
    private String cpf;
    private LocalDate birthdayDate;
    private String motherName;
    private String address;
    private String cep;
    private String email;
    private String phoneNumber;
    private BigDecimal monthlyIncome;
    private String profession;
}