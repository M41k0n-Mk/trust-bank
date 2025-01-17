package me.m41k0n.model;

import jakarta.persistence.Entity;

@Entity
public class DigitalAccount extends Account {
    private String digitalWalletId;
}