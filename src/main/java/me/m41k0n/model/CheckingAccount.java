package me.m41k0n.model;

import jakarta.persistence.Entity;

@Entity
public class CheckingAccount extends Account {
    private Double overdraftLimit;

    @Override
    public void withdraw(Double amount) {
        if (amount > 0 && this.getBalance() + this.overdraftLimit >= amount) {
            this.setBalance(this.getBalance() - amount);
        }
    }
}