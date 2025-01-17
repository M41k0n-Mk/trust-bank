package me.m41k0n.model.dto;

import lombok.Getter;
import lombok.Setter;
import me.m41k0n.model.Customer;

@Getter
@Setter
public class AccountResponseDTO {
    private String type;
    private Customer customer;
}