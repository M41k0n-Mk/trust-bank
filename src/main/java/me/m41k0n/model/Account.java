package me.m41k0n.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CheckingAccount.class, name = "checking"),
        @JsonSubTypes.Type(value = SavingsAccount.class, name = "savings"),
        @JsonSubTypes.Type(value = DigitalAccount.class, name = "digital")
})
@Getter
@Setter(AccessLevel.NONE)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Long id;
    private String branch;
    private String number;
    @Setter
    private Double balance;
    @Setter
    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Account() {
        this.balance = 0.0;
    }

    @PrePersist
    private void ensureBranchAndNumber() {
        if (this.branch == null) {
            this.branch = generateBranch();
        }
        if (this.number == null) {
            this.number = generateNumber();
        }
    }

    private String generateBranch() {
        Random random = new Random();
        int branchNumber = random.nextInt(9000) + 1000;
        return String.valueOf(branchNumber);
    }

    private String generateNumber() {
        Random random = new Random();
        long accountNumber = random.nextLong(9000000000L) + 1000000000L;
        return String.valueOf(accountNumber);
    }

    public void deposit(Double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    public void withdraw(Double amount) {
        if (amount > 0 && this.getBalance() >= amount) {
            this.setBalance(this.getBalance() - amount);
        }
    }
}