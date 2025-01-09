package me.m41k0n.model;

import jakarta.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CheckingAccount extends Account {
    private Double overdraftLimit;
}
