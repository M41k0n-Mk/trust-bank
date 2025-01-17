package me.m41k0n.model;

import jakarta.persistence.Entity;

@Entity
public class SavingsAccount extends Account {
    private Double interestRate;
}